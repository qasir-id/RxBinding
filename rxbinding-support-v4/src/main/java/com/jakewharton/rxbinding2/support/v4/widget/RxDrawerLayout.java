package com.jakewharton.rxbinding2.support.v4.widget;

import androidx.annotation.CheckResult;
import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;
import com.jakewharton.rxbinding2.InitialValueObservable;
import io.reactivex.functions.Consumer;

import static com.jakewharton.rxbinding2.internal.Preconditions.checkNotNull;

public final class RxDrawerLayout {
  /**
   * Create an observable of the open state of the drawer of {@code view}.
   * <p>
   * <em>Warning:</em> The created observable keeps a strong reference to {@code view}. Unsubscribe
   * to free this reference.
   * <p>
   * <em>Note:</em> A value will be emitted immediately on subscribe.
   */
  @CheckResult @NonNull public static InitialValueObservable<Boolean> drawerOpen(
      @NonNull DrawerLayout view, int gravity) {
    checkNotNull(view, "view == null");
    return new DrawerLayoutDrawerOpenedObservable(view, gravity);
  }

  /**
   * An action which sets whether the drawer with {@code gravity} of {@code view} is open.
   * <p>
   * <em>Warning:</em> The created observable keeps a strong reference to {@code view}. Unsubscribe
   * to free this reference.
   */
  @CheckResult @NonNull public static Consumer<? super Boolean> open(
      @NonNull DrawerLayout view, int gravity) {
    checkNotNull(view, "view == null");
    return value -> {
      if (value) {
        view.openDrawer(gravity);
      } else {
        view.closeDrawer(gravity);
      }
    };
  }

  private RxDrawerLayout() {
    throw new AssertionError("No instances.");
  }
}
