@file:Suppress("NOTHING_TO_INLINE")

package com.jakewharton.rxbinding2.support.design.widget

import androidx.annotation.CheckResult
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.view.MenuItem
import io.reactivex.Observable
import kotlin.Suppress

/**
 * Create an observable which emits the selected item in `view`.
 *
 * *Warning:* The created observable keeps a strong reference to `view`. Unsubscribe
 * to free this reference.
 *
 * *Note:* If an item is already selected, it will be emitted immediately on subscribe.
 */
@CheckResult
inline fun BottomNavigationView.itemSelections(): Observable<MenuItem> = RxBottomNavigationView.itemSelections(this)
