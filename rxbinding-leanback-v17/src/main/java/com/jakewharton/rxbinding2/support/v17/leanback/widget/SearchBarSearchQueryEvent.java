package com.jakewharton.rxbinding2.support.v17.leanback.widget;

import androidx.annotation.NonNull;
import androidx.leanback.widget.SearchBar;

public abstract class SearchBarSearchQueryEvent {
  SearchBarSearchQueryEvent() {
  }

  /** The view from which this event occurred. */
  @NonNull public abstract SearchBar view();
  @NonNull public abstract String searchQuery();
}
