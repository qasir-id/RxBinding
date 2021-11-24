package com.jakewharton.rxbinding2.view;

import androidx.annotation.RequiresApi;
import android.view.View;
import android.view.ViewTreeObserver.OnDrawListener;
import com.jakewharton.rxbinding2.internal.Notification;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.MainThreadDisposable;

import static com.jakewharton.rxbinding2.internal.Preconditions.checkMainThread;

@RequiresApi(16)
final class ViewTreeObserverDrawObservable extends Observable<Object> {
  private final View view;

  ViewTreeObserverDrawObservable(View view) {
    this.view = view;
  }

  @Override protected void subscribeActual(Observer<? super Object> observer) {
    if (!checkMainThread(observer)) {
      return;
    }
    Listener listener = new Listener(view, observer);
    observer.onSubscribe(listener);
    view.getViewTreeObserver()
        .addOnDrawListener(listener);
  }

  static final class Listener extends MainThreadDisposable implements OnDrawListener {
    private final View view;
    private final Observer<? super Object> observer;

    Listener(View view, Observer<? super Object> observer) {
      this.view = view;
      this.observer = observer;
    }

    @Override public void onDraw() {
      if (!isDisposed()) {
        observer.onNext(Notification.INSTANCE);
      }
    }

    @Override protected void onDispose() {
      view.getViewTreeObserver().removeOnDrawListener(this);
    }
  }
}
