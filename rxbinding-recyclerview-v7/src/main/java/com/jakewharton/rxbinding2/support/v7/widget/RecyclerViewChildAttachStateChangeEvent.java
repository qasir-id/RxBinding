package com.jakewharton.rxbinding2.support.v7.widget;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

public abstract class RecyclerViewChildAttachStateChangeEvent {
  RecyclerViewChildAttachStateChangeEvent() {
  }

  /** The view from which this event occurred. */
  @NonNull public abstract RecyclerView view();
  /** The child from which this event occurred. */
  @NonNull public abstract View child();
}
