package com.jakewharton.rxbinding2.support.v7.widget;

import androidx.test.annotation.UiThreadTest;
import androidx.test.rule.ActivityTestRule;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import com.jakewharton.rxbinding2.RecordingObserver;
import com.jakewharton.rxbinding2.support.v7.appcompat.test.R;
import io.reactivex.android.schedulers.AndroidSchedulers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static com.jakewharton.rxbinding2.support.v7.widget.RxToolbarTestActivity.NAVIGATION_CONTENT_DESCRIPTION;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

public final class RxToolbarTest {
  @Rule public final ActivityTestRule<RxToolbarTestActivity> activityRule =
      new ActivityTestRule<>(RxToolbarTestActivity.class);

  private Toolbar view;

  @Before public void setUp() {
    RxToolbarTestActivity activity = activityRule.getActivity();
    view = activity.toolbar;
  }

  @Test @UiThreadTest public void itemClicks() {
    Menu menu = view.getMenu();
    MenuItem item1 = menu.add(0, 1, 0, "Hi");
    MenuItem item2 = menu.add(0, 2, 0, "Hey");

    RecordingObserver<MenuItem> o = new RecordingObserver<>();
    RxToolbar.itemClicks(view).subscribe(o);
    o.assertNoMoreEvents();

    menu.performIdentifierAction(2, 0);
    assertSame(item2, o.takeNext());

    menu.performIdentifierAction(1, 0);
    assertSame(item1, o.takeNext());

    o.dispose();

    menu.performIdentifierAction(2, 0);
    o.assertNoMoreEvents();
  }

  @Test public void navigationClicks() {
    RecordingObserver<Object> o = new RecordingObserver<>();
    RxToolbar.navigationClicks(view)
        .subscribeOn(AndroidSchedulers.mainThread())
        .subscribe(o);
    o.assertNoMoreEvents(); // No initial value.

    onView(withContentDescription(NAVIGATION_CONTENT_DESCRIPTION)).perform(click());
    assertNotNull(o.takeNext());

    onView(withContentDescription(NAVIGATION_CONTENT_DESCRIPTION)).perform(click());
    assertNotNull(o.takeNext());

    o.dispose();

    onView(withContentDescription(NAVIGATION_CONTENT_DESCRIPTION)).perform(click());
    o.assertNoMoreEvents();
  }

  @Test @UiThreadTest public void title() throws Exception {
    RxToolbar.title(view).accept("Hey");
    assertEquals("Hey", view.getTitle().toString());
  }

  @Test @UiThreadTest public void titleRes() throws Exception {
    RxToolbar.titleRes(view).accept(R.string.hey);
    assertEquals("Hey", view.getTitle().toString());
  }

  @Test @UiThreadTest public void subtitle() throws Exception {
    RxToolbar.subtitle(view).accept("Hey");
    assertEquals("Hey", view.getSubtitle().toString());
  }

  @Test @UiThreadTest public void subtitleRes() throws Exception {
    RxToolbar.subtitleRes(view).accept(R.string.hey);
    assertEquals("Hey", view.getSubtitle().toString());
  }
}
