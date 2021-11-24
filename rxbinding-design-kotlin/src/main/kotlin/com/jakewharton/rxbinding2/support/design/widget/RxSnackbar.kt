@file:Suppress("NOTHING_TO_INLINE")

package com.jakewharton.rxbinding2.support.design.widget

import androidx.annotation.CheckResult
import com.google.android.material.snackbar.Snackbar
import io.reactivex.Observable
import kotlin.Int
import kotlin.Suppress

/**
 * Create an observable which emits the dismiss events from `view`.
 *
 * *Warning:* The created observable keeps a strong reference to `view`. Unsubscribe
 * to free this reference.
 */
@CheckResult
inline fun Snackbar.dismisses(): Observable<Int> = RxSnackbar.dismisses(this)
