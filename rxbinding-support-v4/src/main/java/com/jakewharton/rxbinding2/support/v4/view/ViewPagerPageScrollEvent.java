package com.jakewharton.rxbinding2.support.v4.view;

import androidx.annotation.CheckResult;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class ViewPagerPageScrollEvent {
  @CheckResult @NonNull
  public static ViewPagerPageScrollEvent create(ViewPager viewPager, int position,
      float positionOffset, int positionOffsetPixels) {
    return new AutoValue_ViewPagerPageScrollEvent(viewPager, position, positionOffset,
        positionOffsetPixels);
  }

  ViewPagerPageScrollEvent() {
  }

  /**
   * The view from which this event occurred.
   */
  @NonNull public abstract ViewPager viewPager();
  public abstract int position();
  public abstract float positionOffset();
  public abstract int positionOffsetPixels();
}
