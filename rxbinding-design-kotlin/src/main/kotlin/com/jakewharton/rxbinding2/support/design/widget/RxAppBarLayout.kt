@file:Suppress("NOTHING_TO_INLINE")

package com.jakewharton.rxbinding2.support.design.widget

import androidx.annotation.CheckResult
import com.google.android.material.appbar.AppBarLayout
import io.reactivex.Observable
import kotlin.Int
import kotlin.Suppress

/**
 * Create an observable which emits the offset change in `view`.
 *
 * *Warning:* The created observable keeps a strong reference to `view`. Unsubscribe
 * to free this reference.
 */
@CheckResult
inline fun AppBarLayout.offsetChanges(): Observable<Int> = RxAppBarLayout.offsetChanges(this)
