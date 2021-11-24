@file:Suppress("NOTHING_TO_INLINE")

package com.jakewharton.rxbinding2.support.v7.widget

import androidx.annotation.CheckResult
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.Observable
import kotlin.Int
import kotlin.Suppress

/**
 * Create an observable of child attach state change events on `recyclerView`.
 *
 * *Warning:* The created observable keeps a strong reference to `recyclerView`.
 * Unsubscribe to free this reference.
 */
@CheckResult
inline fun RecyclerView.childAttachStateChangeEvents(): Observable<RecyclerViewChildAttachStateChangeEvent> = RxRecyclerView.childAttachStateChangeEvents(this)

/**
 * Create an observable of scroll events on `recyclerView`.
 *
 * *Warning:* The created observable keeps a strong reference to `recyclerView`.
 * Unsubscribe to free this reference.
 */
@CheckResult
inline fun RecyclerView.scrollEvents(): Observable<RecyclerViewScrollEvent> = RxRecyclerView.scrollEvents(this)

/**
 * Create an observable of scroll state changes on `recyclerView`.
 *
 * *Warning:* The created observable keeps a strong reference to `recyclerView`.
 * Unsubscribe to free this reference.
 */
@CheckResult
inline fun RecyclerView.scrollStateChanges(): Observable<Int> = RxRecyclerView.scrollStateChanges(this)

/**
 * Create an observable of fling events on `recyclerView`.
 *
 * *Warning:* The created observable keeps a strong reference to `recyclerView`.
 * Unsubscribe to free this reference.
 */
@CheckResult
inline fun RecyclerView.flingEvents(): Observable<RecyclerViewFlingEvent> = RxRecyclerView.flingEvents(this)
