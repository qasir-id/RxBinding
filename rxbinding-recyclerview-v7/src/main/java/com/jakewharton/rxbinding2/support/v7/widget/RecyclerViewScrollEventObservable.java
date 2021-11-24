package com.jakewharton.rxbinding2.support.v7.widget;

import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.MainThreadDisposable;

import static com.jakewharton.rxbinding2.internal.Preconditions.checkMainThread;

final class RecyclerViewScrollEventObservable extends Observable<RecyclerViewScrollEvent> {
  private final RecyclerView view;

  RecyclerViewScrollEventObservable(RecyclerView view) {
    this.view = view;
  }

  @Override protected void subscribeActual(Observer<? super RecyclerViewScrollEvent> observer) {
    if (!checkMainThread(observer)) {
      return;
    }
    Listener listener = new Listener(view, observer);
    observer.onSubscribe(listener);
    view.addOnScrollListener(listener.scrollListener);
  }

  final class Listener extends MainThreadDisposable {
    private final RecyclerView recyclerView;
    final RecyclerView.OnScrollListener scrollListener;

    Listener(RecyclerView recyclerView, final Observer<? super RecyclerViewScrollEvent> observer) {
      this.recyclerView = recyclerView;
      this.scrollListener = new RecyclerView.OnScrollListener() {
        @Override public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
          if (!isDisposed()) {
            observer.onNext(RecyclerViewScrollEvent.create(recyclerView, dx, dy));
          }
        }
      };
    }

    @Override protected void onDispose() {
      recyclerView.removeOnScrollListener(scrollListener);
    }
  }
}
