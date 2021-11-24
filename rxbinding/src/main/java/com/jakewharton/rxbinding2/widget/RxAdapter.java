package com.jakewharton.rxbinding2.widget;

import androidx.annotation.CheckResult;
import androidx.annotation.NonNull;
import android.widget.Adapter;
import com.jakewharton.rxbinding2.InitialValueObservable;
import io.reactivex.Observable;

import static com.jakewharton.rxbinding2.internal.Preconditions.checkNotNull;

/**
 * Static factory methods for creating {@linkplain Observable observables} for {@link Adapter}.
 */
public final class RxAdapter {
  /**
   * Create an observable of data change events for {@code adapter}.
   * <p>
   * <em>Note:</em> A value will be emitted immediately on subscribe.
   */
  @CheckResult @NonNull
  public static <T extends Adapter> InitialValueObservable<T> dataChanges(@NonNull T adapter) {
    checkNotNull(adapter, "adapter == null");
    return new AdapterDataChangeObservable<>(adapter);
  }

  private RxAdapter() {
    throw new AssertionError("No instances.");
  }
}
