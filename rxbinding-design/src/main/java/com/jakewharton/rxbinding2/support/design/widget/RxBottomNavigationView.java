package com.jakewharton.rxbinding2.support.design.widget;

import androidx.annotation.CheckResult;
import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import android.view.MenuItem;
import io.reactivex.Observable;

import static com.jakewharton.rxbinding2.internal.Preconditions.checkNotNull;

/**
 * Static factory methods for creating {@linkplain Observable observables} for
 * {@link BottomNavigationView}.
 */
public final class RxBottomNavigationView {

  /**
   * Create an observable which emits the selected item in {@code view}.
   * <p>
   * <em>Warning:</em> The created observable keeps a strong reference to {@code view}. Unsubscribe
   * to free this reference.
   * <p>
   * <em>Note:</em> If an item is already selected, it will be emitted immediately on subscribe.
   */
  @CheckResult @NonNull public static Observable<MenuItem> itemSelections(
      @NonNull BottomNavigationView view) {
    checkNotNull(view, "view == null");
    return new BottomNavigationViewItemSelectionsObservable(view);
  }

  private RxBottomNavigationView() {
    throw new AssertionError("No instances.");
  }
}
