package com.jakewharton.rxbinding2.support.v17.leanback.widget;

import androidx.leanback.widget.SearchEditText;
import androidx.leanback.widget.SearchEditText.OnKeyboardDismissListener;
import com.jakewharton.rxbinding2.internal.Notification;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.MainThreadDisposable;

import static com.jakewharton.rxbinding2.internal.Preconditions.checkMainThread;

final class SearchEditTextKeyboardDismissOnSubscribe extends Observable<Object> {
  final SearchEditText view;

  SearchEditTextKeyboardDismissOnSubscribe(SearchEditText view) {
    this.view = view;
  }

  @Override protected void subscribeActual(final Observer<? super Object> observer) {
    if (!checkMainThread(observer)) {
      return;
    }
    Listener listener = new Listener(view, observer);
    observer.onSubscribe(listener);
    view.setOnKeyboardDismissListener(listener);
  }

  static class Listener extends MainThreadDisposable implements OnKeyboardDismissListener {
    final SearchEditText view;
    final Observer<? super Object> observer;

    Listener(SearchEditText view, Observer<? super Object> observer) {
      this.view = view;
      this.observer = observer;
    }

    @Override public void onKeyboardDismiss() {
      if (!isDisposed()) {
        observer.onNext(Notification.INSTANCE);
      }
    }

    @Override protected void onDispose() {
      view.setOnKeyboardDismissListener(null);
    }
  }
}
