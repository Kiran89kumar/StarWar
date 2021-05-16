package com.star.war.utils

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.runner.Description

class TestTaskExecutorRule : InstantTaskExecutorRule() {

    override fun starting(description: Description?) {
        super.starting(description)
        RxJavaPlugins.setComputationSchedulerHandler {
            Schedulers.trampoline()
        }

        RxJavaPlugins.setIoSchedulerHandler {
            Schedulers.trampoline()
        }

        RxJavaPlugins.setNewThreadSchedulerHandler {
            Schedulers.trampoline()
        }

        RxAndroidPlugins.setInitMainThreadSchedulerHandler {
            Schedulers.trampoline()
        }
    }

    override fun finished(description: Description?) {
        super.finished(description)
        RxJavaPlugins.reset()
        RxAndroidPlugins.reset()
    }
}