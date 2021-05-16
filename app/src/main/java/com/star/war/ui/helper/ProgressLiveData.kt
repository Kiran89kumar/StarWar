package com.star.war.ui.helper

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import com.star.war.ui.base.BaseFragment
import java.lang.ref.WeakReference

/**
 * Used to handle the Owner and Loading/Progressbar.
 */
open class ProgressLiveData<T> : BaseLiveData<T>() {

    private val owners: MutableSet<WeakReference<BaseFragment>> = mutableSetOf()

    override fun observeWithFragment(
        fragment: BaseFragment,
        observer: Observer<in T>,
        flag: Boolean
    ) {
        isShowLoading = flag
        owners.add(WeakReference(fragment))
        showLoading(fragment)
        observe(fragment.viewLifecycleOwner, observer)
    }

    override fun setValue(value: T) {
        hideLoading()
        super.setValue(value)
    }

    private fun showLoading(owner: BaseFragment) {
        if (owner.lifecycle.currentState.let
            { state ->
                state == Lifecycle.State.RESUMED || state == Lifecycle.State.CREATED
            }
        ) {
            if(isShowLoading)
                owner.showLoading()
        }
    }

    protected fun hideLoading() {
        owners.forEach {
            it.get()?.let { owner ->
                if (owner.lifecycle.currentState.let
                    { state ->
                        state == Lifecycle.State.RESUMED || state == Lifecycle.State.CREATED
                    }
                ) {
                    if(isShowLoading)
                        owner.hideLoading()
                }
            }
        }
    }
}