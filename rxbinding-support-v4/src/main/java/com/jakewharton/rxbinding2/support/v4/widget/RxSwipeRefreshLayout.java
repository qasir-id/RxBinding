package com.jakewharton.rxbinding2.support.v4.widget;

import androidx.annotation.CheckResult;
import androidx.annotation.NonNull;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

import static com.jakewharton.rxbinding2.internal.Preconditions.checkNotNull;

public final class RxSwipeRefreshLayout {
  /**
   * Create an observable of refresh events on {@code view}.
   * <p>
   * <em>Warning:</em> The created observable keeps a strong reference to {@code view}. Unsubscribe
   * to free this reference.
   */
  @CheckResult @NonNull public static Observable<Object> refreshes(
      @NonNull SwipeRefreshLayout view) {
    checkNotNull(view, "view == null");
    return new SwipeRefreshLayoutRefreshObservable(view);
  }

  /**
   * An action which sets whether the layout is showing the refreshing indicator.
   * <p>
   * <em>Warning:</em> The created observable keeps a strong reference to {@code view}. Unsubscribe
   * to free this reference.
   *
   * @deprecated Use view::setRefreshing method reference.
   */
  @Deprecated
  @CheckResult @NonNull public static Consumer<? super Boolean> refreshing(
      @NonNull SwipeRefreshLayout view) {
    checkNotNull(view, "view == null");
    return view::setRefreshing;
  }

  private RxSwipeRefreshLayout() {
    throw new AssertionError("No instances.");
  }
}
