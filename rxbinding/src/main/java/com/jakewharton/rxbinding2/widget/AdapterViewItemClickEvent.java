package com.jakewharton.rxbinding2.widget;

import androidx.annotation.CheckResult;
import androidx.annotation.NonNull;
import android.view.View;
import android.widget.AdapterView;
import com.google.auto.value.AutoValue;

@AutoValue
public abstract class AdapterViewItemClickEvent {
  @CheckResult @NonNull
  public static AdapterViewItemClickEvent create(@NonNull AdapterView<?> view,
      @NonNull View clickedView, int position, long id) {
    return new AutoValue_AdapterViewItemClickEvent(view, clickedView, position, id);
  }

  AdapterViewItemClickEvent() {
  }

  /** The view from which this event occurred. */
  @NonNull public abstract AdapterView<?> view();
  @NonNull public abstract View clickedView();
  public abstract int position();
  public abstract long id();
}
