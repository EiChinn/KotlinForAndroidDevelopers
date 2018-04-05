package com.grgt.learnkotlin.ui

import android.app.Application
import com.grgt.learnkotlin.extensions.DelegatesExt
import kotlin.properties.Delegates

/**
 * Created by JDRJ on 2017/12/14.
 */
class App : Application() {
    companion object {
        var instance: App by DelegatesExt.notNullSingleValue()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}