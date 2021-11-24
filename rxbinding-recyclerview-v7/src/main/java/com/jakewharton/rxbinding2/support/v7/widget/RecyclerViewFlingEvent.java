package com.jakewharton.rxbinding2.support.v7.widget;

import androidx.annotation.CheckResult;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class RecyclerViewFlingEvent {
  @CheckResult @NonNull
  public static RecyclerViewFlingEvent create(@NonNull RecyclerView recyclerView, int velocityX,
      int velocityY) {
    return new AutoValue_RecyclerViewFlingEvent(recyclerView, velocityX, velocityY);
  }

  RecyclerViewFlingEvent() {
  }

  /** The view from which this event occurred. */
  @NonNull public abstract RecyclerView view();
  public abstract int velocityX();
  public abstract int velocityY();
}
