package com.star.war.utils

import androidx.lifecycle.Observer
import com.star.war.ui.helper.BaseLiveData
import com.star.war.ui.helper.ObservableData
import com.star.war.ui.helper.TestLifecycleOwner

fun <T> BaseLiveData<ObservableData<T, Throwable>>.testObserve(): BaseLiveData<ObservableData<T, Throwable>> {
    observe(TestLifecycleOwner(), Observer {

    })
    return this
}

val <T> BaseLiveData<ObservableData<T, Throwable>>.observedValue
    get() = value?.data

val <T> BaseLiveData<ObservableData<T, Throwable>>.observedError
    get() = value?.error