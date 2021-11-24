package com.jakewharton.rxbinding2.widget;

import androidx.annotation.NonNull;
import android.widget.SeekBar;

public abstract class SeekBarChangeEvent {
  SeekBarChangeEvent() {
  }

  /** The view from which this event occurred. */
  @NonNull public abstract SeekBar view();
}
