package com.star.war.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_search.*

abstract class BaseFragment : DaggerFragment() {

    var binding: ViewDataBinding? = null

    @get:LayoutRes
    abstract val fragmentLayoutResId: Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, fragmentLayoutResId, container, false)
        return binding?.root
    }

    fun showLoading() {
        if(pb_progress!=null) {
            pb_progress.visibility = View.VISIBLE
        } else {
            when (activity) {
                is BaseActivity -> (activity as BaseActivity).showLoading()
            }
        }
    }

    fun hideLoading() {
        if(pb_progress!=null) {
            pb_progress.visibility = View.GONE
        } else {
            when (activity) {
                is BaseActivity -> (activity as BaseActivity).hideLoading()
            }
        }
    }

    fun handleApiError(error: Throwable?) {
        error?.let { error ->
            (activity as? BaseActivity)?.handleApiError(error)
        }
    }

    fun closeKeyboard() {
        (activity as BaseActivity).closeKeyboard()
    }

    override fun onPause() {
        super.onPause()
        hideLoading()
        closeKeyboard()
    }
}