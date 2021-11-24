package com.jakewharton.rxbinding2.support.design.widget;

import androidx.annotation.CheckResult;
import androidx.annotation.NonNull;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayout.Tab;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

import static com.jakewharton.rxbinding2.internal.Preconditions.checkNotNull;

/**
 * Static factory methods for creating {@linkplain Observable observables} and {@linkplain Consumer
 * actions} for {@link TabLayout}.
 */
public final class RxTabLayout {
  /**
   * Create an observable which emits the selected tab in {@code view}.
   * <p>
   * <em>Warning:</em> The created observable keeps a strong reference to {@code view}. Unsubscribe
   * to free this reference.
   * <p>
   * <em>Note:</em> If a tab is already selected, it will be emitted immediately on subscribe.
   */
  @CheckResult @NonNull
  public static Observable<Tab> selections(@NonNull TabLayout view) {
    checkNotNull(view, "view == null");
    return new TabLayoutSelectionsObservable(view);
  }

  /**
   * Create an observable which emits selection, reselection, and unselection events for the tabs
   * in {@code view}.
   * <p>
   * <em>Warning:</em> The created observable keeps a strong reference to {@code view}. Unsubscribe
   * to free this reference.
   * <p>
   * <em>Note:</em> If a tab is already selected, an event will be emitted immediately on subscribe.
   */
  @CheckResult @NonNull
  public static Observable<TabLayoutSelectionEvent> selectionEvents(@NonNull TabLayout view) {
    checkNotNull(view, "view == null");
    return new TabLayoutSelectionEventObservable(view);
  }

  /**
   * An action which sets the selected tab of {@code view}.
   * <p>
   * <em>Warning:</em> The created observable keeps a strong reference to {@code view}. Unsubscribe
   * to free this reference.
   */
  @CheckResult @NonNull
  public static Consumer<? super Integer> select(@NonNull TabLayout view) {
    checkNotNull(view, "view == null");
    return index -> {
      if (index < 0 || index >= view.getTabCount()) {
        throw new IllegalArgumentException("No tab for index " + index);
      }
      //noinspection ConstantConditions
      view.getTabAt(index).select();
    };
  }

  private RxTabLayout() {
    throw new AssertionError("No instances.");
  }
}
