package com.jakewharton.rxbinding2.support.design.widget;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener;
import com.google.android.material.tabs.TabLayout.Tab;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.MainThreadDisposable;

import static com.jakewharton.rxbinding2.internal.Preconditions.checkMainThread;

final class TabLayoutSelectionsObservable extends Observable<Tab> {
  private final TabLayout view;

  TabLayoutSelectionsObservable(TabLayout view) {
    this.view = view;
  }

  @Override protected void subscribeActual(Observer<? super Tab> observer) {
    if (!checkMainThread(observer)) {
      return;
    }
    Listener listener = new Listener(view, observer);
    observer.onSubscribe(listener);
    view.addOnTabSelectedListener(listener);
    int index = view.getSelectedTabPosition();
    if (index != -1) {
      observer.onNext(view.getTabAt(index));
    }
  }

  static final class Listener extends MainThreadDisposable implements OnTabSelectedListener {
    private final TabLayout tabLayout;
    private final Observer<? super Tab> observer;

    Listener(TabLayout tabLayout, Observer<? super Tab> observer) {
      this.tabLayout = tabLayout;
      this.observer = observer;
    }

    @Override protected void onDispose() {
      tabLayout.removeOnTabSelectedListener(this);
    }

    @Override public void onTabSelected(Tab tab) {
      if (!isDisposed()) {
        observer.onNext(tab);
      }
    }

    @Override public void onTabUnselected(Tab tab) {
    }

    @Override public void onTabReselected(Tab tab) {
    }
  }
}
