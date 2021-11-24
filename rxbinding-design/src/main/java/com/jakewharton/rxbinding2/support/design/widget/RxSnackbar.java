package com.jakewharton.rxbinding2.support.design.widget;

import androidx.annotation.CheckResult;
import androidx.annotation.NonNull;
import com.google.android.material.snackbar.Snackbar;
import io.reactivex.Observable;

import static com.jakewharton.rxbinding2.internal.Preconditions.checkNotNull;

/**
 * Static factory methods for creating {@linkplain Observable observables} for {@link Snackbar}.
 */
public final class RxSnackbar {
  /**
   * Create an observable which emits the dismiss events from {@code view}.
   * <p>
   * <em>Warning:</em> The created observable keeps a strong reference to {@code view}. Unsubscribe
   * to free this reference.
   */
  @CheckResult @NonNull
  public static Observable<Integer> dismisses(@NonNull Snackbar view) {
    checkNotNull(view, "view == null");
    return new SnackbarDismissesObservable(view);
  }

  private RxSnackbar() {
    throw new AssertionError("No instances.");
  }
}
