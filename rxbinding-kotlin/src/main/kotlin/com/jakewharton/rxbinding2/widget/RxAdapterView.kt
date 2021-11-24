@file:Suppress("NOTHING_TO_INLINE")

package com.jakewharton.rxbinding2.widget

import androidx.annotation.CheckResult
import android.widget.Adapter
import android.widget.AdapterView
import com.jakewharton.rxbinding2.InitialValueObservable
import io.reactivex.Observable
import io.reactivex.functions.Consumer
import io.reactivex.functions.Predicate
import java.util.concurrent.Callable
import kotlin.Deprecated
import kotlin.Int
import kotlin.Suppress

/**
 * Create an observable of the selected position of `view`. If nothing is selected,
 * [AdapterView.INVALID_POSITION] will be emitted.
 *
 * *Warning:* The created observable keeps a strong reference to `view`. Unsubscribe
 * to free this reference.
 *
 * *Note:* A value will be emitted immediately on subscribe.
 */
@CheckResult
inline fun <T : Adapter> AdapterView<T>.itemSelections(): InitialValueObservable<Int> = RxAdapterView.itemSelections(this)

/**
 * Create an observable of selection events for `view`.
 *
 * *Warning:* The created observable keeps a strong reference to `view`. Unsubscribe
 * to free this reference.
 *
 * *Note:* A value will be emitted immediately on subscribe.
 */
@CheckResult
inline fun <T : Adapter> AdapterView<T>.selectionEvents(): InitialValueObservable<AdapterViewSelectionEvent> = RxAdapterView.selectionEvents(this)

/**
 * Create an observable of the position of item clicks for `view`.
 *
 * *Warning:* The created observable keeps a strong reference to `view`. Unsubscribe
 * to free this reference.
 */
@CheckResult
inline fun <T : Adapter> AdapterView<T>.itemClicks(): Observable<Int> = RxAdapterView.itemClicks(this)

/**
 * Create an observable of the item click events for `view`.
 *
 * *Warning:* The created observable keeps a strong reference to `view`. Unsubscribe
 * to free this reference.
 */
@CheckResult
inline fun <T : Adapter> AdapterView<T>.itemClickEvents(): Observable<AdapterViewItemClickEvent> = RxAdapterView.itemClickEvents(this)

/**
 * Create an observable of the position of item long-clicks for `view`.
 *
 * *Warning:* The created observable keeps a strong reference to `view`. Unsubscribe
 * to free this reference.
 */
@CheckResult
inline fun <T : Adapter> AdapterView<T>.itemLongClicks(): Observable<Int> = RxAdapterView.itemLongClicks(this)

/**
 * Create an observable of the position of item long-clicks for `view`.
 *
 * *Warning:* The created observable keeps a strong reference to `view`. Unsubscribe
 * to free this reference.
 *
 * @param handled Function invoked each occurrence to determine the return value of the
 * underlying [AdapterView.OnItemLongClickListener].
 */
@CheckResult
inline fun <T : Adapter> AdapterView<T>.itemLongClicks(handled: Callable<Boolean>): Observable<Int> = RxAdapterView.itemLongClicks(this, handled)

/**
 * Create an observable of the item long-click events for `view`.
 *
 * *Warning:* The created observable keeps a strong reference to `view`. Unsubscribe
 * to free this reference.
 */
@CheckResult
inline fun <T : Adapter> AdapterView<T>.itemLongClickEvents(): Observable<AdapterViewItemLongClickEvent> = RxAdapterView.itemLongClickEvents(this)

/**
 * Create an observable of the item long-click events for `view`.
 *
 * *Warning:* The created observable keeps a strong reference to `view`. Unsubscribe
 * to free this reference.
 *
 * @param handled Function invoked with each value to determine the return value of the
 * underlying [AdapterView.OnItemLongClickListener].
 */
@CheckResult
inline fun <T : Adapter> AdapterView<T>.itemLongClickEvents(handled: Predicate<in AdapterViewItemLongClickEvent>): Observable<AdapterViewItemLongClickEvent> = RxAdapterView.itemLongClickEvents(this, handled)

/**
 * An action which sets the selected position of `view`.
 *
 * *Warning:* The created observable keeps a strong reference to `view`. Unsubscribe
 * to free this reference.
 */
@Deprecated("Use view::setSelection method reference.")
@CheckResult
inline fun <T : Adapter> AdapterView<T>.selection(): Consumer<in Int> = RxAdapterView.selection(this)
