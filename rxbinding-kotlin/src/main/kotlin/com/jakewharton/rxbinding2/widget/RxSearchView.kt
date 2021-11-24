@file:Suppress("NOTHING_TO_INLINE")

package com.jakewharton.rxbinding2.widget

import androidx.annotation.CheckResult
import android.widget.SearchView
import com.jakewharton.rxbinding2.InitialValueObservable
import io.reactivex.functions.Consumer
import kotlin.Boolean
import kotlin.Suppress

/**
 * Create an observable of {@linkplain SearchViewQueryTextEvent query text events} on {@code
 * view}.
 *
 * *Warning:* The created observable keeps a strong reference to `view`. Unsubscribe
 * to free this reference.
 *
 * *Note:* A value will be emitted immediately on subscribe.
 */
@CheckResult
inline fun SearchView.queryTextChangeEvents(): InitialValueObservable<SearchViewQueryTextEvent> = RxSearchView.queryTextChangeEvents(this)

/**
 * Create an observable of character sequences for query text changes on `view`.
 *
 * *Warning:* The created observable keeps a strong reference to `view`. Unsubscribe
 * to free this reference.
 *
 * *Note:* A value will be emitted immediately on subscribe.
 */
@CheckResult
inline fun SearchView.queryTextChanges(): InitialValueObservable<CharSequence> = RxSearchView.queryTextChanges(this)

/**
 * An action which sets the query property of `view` with character sequences.
 *
 * *Warning:* The created observable keeps a strong reference to `view`. Unsubscribe
 * to free this reference.
 *
 * @param submit whether to submit query right after updating query text
 */
@CheckResult
inline fun SearchView.query(submit: Boolean): Consumer<in CharSequence> = RxSearchView.query(this, submit)
