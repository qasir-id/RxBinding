@file:Suppress("NOTHING_TO_INLINE")

package com.jakewharton.rxbinding2.support.v7.widget

import androidx.annotation.CheckResult
import androidx.appcompat.widget.ActionMenuView
import android.view.MenuItem
import io.reactivex.Observable
import kotlin.Suppress

/**
 * Create an observable which emits the clicked menu item in `view`.
 *
 * *Warning:* The created observable keeps a strong reference to `view`.
 * Unsubscribe to free this reference.
 */
@CheckResult
inline fun ActionMenuView.itemClicks(): Observable<MenuItem> = RxActionMenuView.itemClicks(this)
