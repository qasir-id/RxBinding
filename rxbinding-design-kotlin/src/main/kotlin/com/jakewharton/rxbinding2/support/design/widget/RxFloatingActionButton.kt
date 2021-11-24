@file:Suppress("NOTHING_TO_INLINE")

package com.jakewharton.rxbinding2.support.design.widget

import androidx.annotation.CheckResult
import com.google.android.material.floatingactionbutton.FloatingActionButton
import io.reactivex.functions.Consumer
import kotlin.Suppress

/**
 * An action which sets the visibility of `view`.
 *
 * *Warning:* The created observable keeps a strong reference to `view`. Unsubscribe
 * to free this reference.
 */
@CheckResult
inline fun FloatingActionButton.visibility(): Consumer<in Boolean> = RxFloatingActionButton.visibility(this)
