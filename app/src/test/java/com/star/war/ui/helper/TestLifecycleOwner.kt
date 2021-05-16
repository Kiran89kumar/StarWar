package com.star.war.ui.helper

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry

class TestLifecycleOwner : LifecycleOwner {
    private val lifecycle = LifecycleRegistry(this)

    override fun getLifecycle(): Lifecycle = lifecycle
}