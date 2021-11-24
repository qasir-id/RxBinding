package com.jakewharton.rxbinding2.support.design.widget;

import androidx.annotation.NonNull;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayout.Tab;

public abstract class TabLayoutSelectionEvent {
  TabLayoutSelectionEvent() {
  }

  /** The view from which this event occurred. */
  @NonNull public abstract TabLayout view();
  @NonNull public abstract Tab tab();
}
