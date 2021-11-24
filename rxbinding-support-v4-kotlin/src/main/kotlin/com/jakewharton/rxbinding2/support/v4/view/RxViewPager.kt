@file:Suppress("NOTHING_TO_INLINE")

package com.jakewharton.rxbinding2.support.v4.view

import androidx.annotation.CheckResult
import androidx.viewpager.widget.ViewPager
import com.jakewharton.rxbinding2.InitialValueObservable
import io.reactivex.Observable
import io.reactivex.functions.Consumer
import kotlin.Deprecated
import kotlin.Int
import kotlin.Suppress

/**
 * Create an observable of page scroll events on `view`.
 *
 * *Warning:* The created observable keeps a strong reference to `view`. Unsubscribe
 * to free this reference.
 */
@CheckResult
inline fun ViewPager.pageScrollEvents(): Observable<ViewPagerPageScrollEvent> = RxViewPager.pageScrollEvents(this)

/**
 * Create an observable of scroll state change events on `view`.
 *
 * *Warning:* The created observable keeps a strong reference to `view`. Unsubscribe
 * to free this reference.
 */
@CheckResult
inline fun ViewPager.pageScrollStateChanges(): Observable<Int> = RxViewPager.pageScrollStateChanges(this)

/**
 * Create an observable of page selected events on `view`.
 *
 * *Warning:* The created observable keeps a strong reference to `view`. Unsubscribe
 * to free this reference.
 *
 * *Note:* A value will be emitted immediately on subscribe.
 */
@CheckResult
inline fun ViewPager.pageSelections(): InitialValueObservable<Int> = RxViewPager.pageSelections(this)

/**
 * An action which sets the current item of `view`.
 *
 * *Warning:* The created observable keeps a strong reference to `view`. Unsubscribe
 * to free this reference.
 */
@Deprecated("Use view::setCurrentItem method reference.")
@CheckResult
inline fun ViewPager.currentItem(): Consumer<in Int> = RxViewPager.currentItem(this)
