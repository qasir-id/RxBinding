package com.jakewharton.rxbinding2.support.v4.widget;

import android.app.Instrumentation;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.contrib.CountingIdlingResource;
import androidx.test.espresso.matcher.BoundedMatcher;
import androidx.test.rule.ActivityTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.slidingpanelayout.widget.SlidingPaneLayout;
import android.view.View;
import com.jakewharton.rxbinding2.RecordingObserver;
import com.jakewharton.rxbinding2.UnsafeRunnable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class) public class RxSlidingPaneLayoutTest {
  @Rule public final ActivityTestRule<RxSlidingPaneLayoutTestActivity> activityRule =
      new ActivityTestRule<>(RxSlidingPaneLayoutTestActivity.class);

  private final Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();

  SlidingPaneLayout view;

  CountingIdlingResource idler;

  @Before public void setUp() {
    RxSlidingPaneLayoutTestActivity activity = activityRule.getActivity();
    view = activity.slidingPaneLayout;

    idler = new CountingIdlingResource("counting idler");
    Espresso.registerIdlingResources(idler);
  }

  @After public void teardown() {
    Espresso.unregisterIdlingResources(idler);
  }

  @Test public void paneOpen() {
    RecordingObserver<Boolean> o = new RecordingObserver<>();
    RxSlidingPaneLayout.panelOpens(view)
        .subscribeOn(AndroidSchedulers.mainThread())
        .subscribe(o);
    assertFalse(o.takeNext());

    instrumentation.runOnMainSync(() -> view.openPane());
    assertTrue(o.takeNext());

    instrumentation.runOnMainSync(() -> view.closePane());
    assertFalse(o.takeNext());

    o.dispose();

    instrumentation.runOnMainSync(() -> view.openPane());
    o.assertNoMoreEvents();
  }

  @Test public void slides() {
    RecordingObserver<Float> o1 = new RecordingObserver<>();
    RxSlidingPaneLayout.panelSlides(view)
        .subscribeOn(AndroidSchedulers.mainThread())
        .subscribe(o1);
    o1.assertNoMoreEvents();

    instrumentation.runOnMainSync(() -> view.openPane());
    instrumentation.waitForIdleSync();
    assertTrue(o1.takeNext() > 0f);

    o1.dispose();
    o1.assertNoMoreEvents();

    RecordingObserver<Float> o2 = new RecordingObserver<>();
    RxSlidingPaneLayout.panelSlides(view)
        .subscribeOn(AndroidSchedulers.mainThread())
        .subscribe(o2);
    o2.assertNoMoreEvents();

    instrumentation.runOnMainSync(() -> view.closePane());
    instrumentation.waitForIdleSync();
    assertTrue(o2.takeNext() < 1f);

    o2.dispose();
    o2.assertNoMoreEvents();
  }

  @Test public void open() {
    final Consumer<? super Boolean> open = RxSlidingPaneLayout.open(view);

    view.setPanelSlideListener(new SlidingPaneLayout.SimplePanelSlideListener() {
      @Override public void onPanelOpened(View panel) {
        idler.decrement();
      }

      @Override public void onPanelClosed(View panel) {
        idler.decrement();
      }
    });

    idler.increment();
    instrumentation.runOnMainSync(new UnsafeRunnable() {
      @Override protected void unsafeRun() throws Exception {
        open.accept(true);
      }
    });
    instrumentation.waitForIdleSync();
    onView(withId(view.getId())).check(matches(isOpen()));

    idler.increment();
    instrumentation.runOnMainSync(new UnsafeRunnable() {
      @Override protected void unsafeRun() throws Exception {
        open.accept(false);
      }
    });
    instrumentation.waitForIdleSync();
    onView(withId(view.getId())).check(matches(isClosed()));

    view.setPanelSlideListener(null);
  }

  private static Matcher<View> isOpen() {
    return new BoundedMatcher<View, SlidingPaneLayout>(SlidingPaneLayout.class) {
      @Override public void describeTo(Description description) {
        description.appendText("is pane open");
      }

      @Override public boolean matchesSafely(SlidingPaneLayout slidingPaneLayout) {
        return slidingPaneLayout.isOpen();
      }
    };
  }

  private static Matcher<View> isClosed() {
    return new BoundedMatcher<View, SlidingPaneLayout>(SlidingPaneLayout.class) {
      @Override public void describeTo(Description description) {
        description.appendText("is pane closed");
      }

      @Override public boolean matchesSafely(SlidingPaneLayout slidingPaneLayout) {
        return !slidingPaneLayout.isOpen();
      }
    };
  }
}
