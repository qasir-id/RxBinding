package com.jakewharton.rxbinding2.support.v4.widget;

import androidx.test.annotation.UiThreadTest;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.action.GeneralLocation;
import androidx.test.espresso.action.GeneralSwipeAction;
import androidx.test.espresso.action.Press;
import androidx.test.espresso.action.Swipe;
import androidx.test.rule.ActivityTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.jakewharton.rxbinding2.support.v4.test.R;
import com.jakewharton.rxbinding2.RecordingObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class) public final class RxSwipeRefreshLayoutTest {
  @Rule public final ActivityTestRule<RxSwipeRefreshLayoutTestActivity> activityRule =
      new ActivityTestRule<>(RxSwipeRefreshLayoutTestActivity.class);

  private SwipeRefreshLayout view;

  @Before public void setUp() {
    RxSwipeRefreshLayoutTestActivity activity = activityRule.getActivity();
    view = activity.swipeRefreshLayout;
  }

  @Test public void refreshes() throws InterruptedException {
    RecordingObserver<Object> o = new RecordingObserver<>();
    RxSwipeRefreshLayout.refreshes(view)
        .subscribeOn(AndroidSchedulers.mainThread())
        .subscribe(o);
    o.assertNoMoreEvents();

    onView(withId(R.id.swipe_refresh_layout)).perform(swipeDown());
    o.takeNext();

    o.dispose();
    onView(withId(R.id.swipe_refresh_layout)).perform(swipeDown());
    o.assertNoMoreEvents();
  }

  @Test @UiThreadTest public void refreshing() throws Exception {
    Consumer<? super Boolean> action = RxSwipeRefreshLayout.refreshing(view);
    assertFalse(view.isRefreshing());

    action.accept(true);
    assertTrue(view.isRefreshing());

    action.accept(false);
    assertFalse(view.isRefreshing());
  }

  private static ViewAction swipeDown() {
    return new GeneralSwipeAction(Swipe.SLOW, GeneralLocation.TOP_CENTER,
        GeneralLocation.BOTTOM_CENTER, Press.FINGER);
  }
}
