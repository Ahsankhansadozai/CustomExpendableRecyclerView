/**
 *Sardar Ahsan Khan
 * 3/8/22
 */

package com.example.expendedrecycler.app

import android.app.Application
import com.example.expendedrecycler.BuildConfig
import timber.log.Timber

class App : Application() {

    private val context: App
        get() = instance!!.context

    val hTag = "AhsanTimberTags %s"

    companion object {
        var instance: App? = null
            private set

    }

    override fun onCreate() {
        super.onCreate()
        hInitTimber()
    }

    private fun hInitTimber() {
        if (
            BuildConfig.DEBUG) {
            Timber.plant(object : Timber.DebugTree() {
                override fun log(
                    priority: Int,
                    tag: String?,
                    message: String,
                    t: Throwable?
                ) {
                    super.log(priority, String.format(hTag, tag), message, t)
                }
            })
        }
    }
}