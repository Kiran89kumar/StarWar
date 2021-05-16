package com.star.war.ui.helper

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import io.reactivex.Single

/**
 * Live data class used for Performing the action and set Value to callback
 */
open class ActionableLiveData<T>(
    var actionBlock: (() -> Single<T>)? = null
) : ProgressLiveData<ObservableData<T, Throwable>>() {

    override fun observe(
        owner: LifecycleOwner,
        observer: Observer<in ObservableData<T, Throwable>>
    ) {
        super.observe(owner, observer)
        performAction()
    }

    protected open fun performAction() {
        actionBlock?.let { aBlock ->
            addDisposable {
                aBlock.invoke()
                    .subscribe({
                        setValue(ObservableData(it, null))
                    }, {
                        setValue(ObservableData(null, it))
                    })
            }
        }
    }
}