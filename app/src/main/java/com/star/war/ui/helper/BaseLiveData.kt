package com.star.war.ui.helper

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.star.war.ui.base.BaseFragment
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseLiveData<T> : MutableLiveData<T>() {

    private val compositeDisposable = CompositeDisposable()

    fun addDisposable(disposable: () -> Disposable) = compositeDisposable.add(disposable.invoke())

    fun clearAllDisposables() = compositeDisposable.dispose()

    fun stopAllDisposables() = compositeDisposable.clear()

    abstract fun observeWithFragment(fragment: BaseFragment, observer: Observer<in T>, flag: Boolean = true)

    var isShowLoading: Boolean = true
}