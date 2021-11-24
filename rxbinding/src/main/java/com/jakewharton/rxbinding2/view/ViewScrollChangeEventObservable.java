package com.jakewharton.rxbinding2.view;

import androidx.annotation.RequiresApi;
import android.view.View;
import android.view.View.OnScrollChangeListener;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.MainThreadDisposable;

import static com.jakewharton.rxbinding2.internal.Preconditions.checkMainThread;

@RequiresApi(23)
final class ViewScrollChangeEventObservable extends Observable<ViewScrollChangeEvent> {
  private final View view;

  ViewScrollChangeEventObservable(View view) {
    this.view = view;
  }

  @Override protected void subscribeActual(Observer<? super ViewScrollChangeEvent> observer) {
    if (!checkMainThread(observer)) {
      return;
    }
    Listener listener = new Listener(view, observer);
    observer.onSubscribe(listener);
    view.setOnScrollChangeListener(listener);
  }

  static final class Listener extends MainThreadDisposable implements OnScrollChangeListener {
    private final View view;
    private final Observer<? super ViewScrollChangeEvent> observer;

    Listener(View view, Observer<? super ViewScrollChangeEvent> observer) {
      this.view = view;
      this.observer = observer;
    }

    @Override
    public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
      if (!isDisposed()) {
        observer.onNext(ViewScrollChangeEvent.create(v, scrollX, scrollY, oldScrollX, oldScrollY));
      }
    }

    @Override protected void onDispose() {
      view.setOnScrollChangeListener(null);
    }
  }
}
