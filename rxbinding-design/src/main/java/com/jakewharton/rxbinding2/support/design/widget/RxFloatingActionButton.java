package com.jakewharton.rxbinding2.support.design.widget;

import androidx.annotation.CheckResult;
import androidx.annotation.NonNull;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import io.reactivex.functions.Consumer;

import static com.jakewharton.rxbinding2.internal.Preconditions.checkNotNull;

public final class RxFloatingActionButton {
  /**
   * An action which sets the visibility of {@code view}.
   * <p>
   * <em>Warning:</em> The created observable keeps a strong reference to {@code view}. Unsubscribe
   * to free this reference.
   */
  @CheckResult @NonNull
  public static Consumer<? super Boolean> visibility(@NonNull FloatingActionButton view) {
    checkNotNull(view, "view == null");
    return value -> {
      if (value) {
        view.show();
      } else {
        view.hide();
      }
    };
  }

  private RxFloatingActionButton() {
    throw new AssertionError("No instances.");
  }
}
