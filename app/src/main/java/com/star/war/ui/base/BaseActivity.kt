package com.star.war.ui.base

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.star.war.R
import com.star.war.repo.network.exceptions.ServerException
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.layout_base.*

abstract class BaseActivity : DaggerAppCompatActivity() {

    var binding: ViewDataBinding? = null

    private var dialog: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    override fun onDestroy() {
        super.onDestroy()
        hideDialog()
    }

    private fun init() {
        if (layoutResourceId != -1) {
            binding =
                DataBindingUtil.inflate(
                    LayoutInflater.from(this),
                    R.layout.layout_base,
                    null,
                    false
                )
            setContentView(binding?.root)
            layoutInflater.inflate(layoutResourceId, fl_container)
        }
    }

    fun handleApiError(error: Throwable) {
        when (error) {
            is ServerException -> {
                if(error.serverError != null ){
                    //Handling Error - Received from Server
                    showDialog(getString(R.string.server_error), error.message?: "")
                } else {
                    val msgArray = when(error.kind){
                        ServerException.Kind.NETWORK ->
                            listOf(getString(R.string.no_connection), getString(R.string.no_connection_desc))
                        ServerException.Kind.HTTP ->
                            listOf(getString(R.string.network_error),getString(R.string.network_error_msg))
                        else ->
                            listOf(getString(R.string.unexpected_error), getString(R.string.unexpected_error_msg))
                    }
                    showDialog(msgArray[0], msgArray[1])
                }
            }
            else -> showAppError()
        }
    }

    private fun showAppError(title: String? = null, msg: String? = null) {
        showDialog(title = title, msg = msg)
    }

    private fun showDialog(
        title: String? = null,
        msg: String? = null,
        negativeText: String? = null,
        positiveText: String? = "Ok",
        negativeCallback: () -> Unit = {},
        positiveCallback: () -> Unit = {},
        isCancellable: Boolean = false
    ) {
        val builder = AlertDialog.Builder(this)

        val aTitle = if (title.isNullOrEmpty()) getString(
            R.string.err_dialog_title
        ) else title

        builder.setTitle(aTitle)

        val aMessage = if (msg.isNullOrEmpty()) getString(
            R.string.err_dialog_msg
        ) else msg

        builder.setMessage(aMessage)

        negativeText?.let {
            builder.setNegativeButton(it) { _: DialogInterface, _: Int ->
                negativeCallback.invoke()
                hideDialog()
            }
        }
        positiveText.let {
            builder.setPositiveButton(it) { _: DialogInterface, _: Int ->
                positiveCallback.invoke()
                hideDialog()
            }
        }
        builder.setCancelable(isCancellable)

        hideDialog()
        dialog = builder.create()
        dialog?.show()
    }

    fun showLoading() {
        ll_progress.visibility = View.VISIBLE
    }

    fun hideLoading() {
        ll_progress.visibility = View.GONE
    }

    private fun hideDialog() {
        dialog?.run {
            if (isShowing && !isFinishing) dismiss()
        }
    }

    fun closeKeyboard() {
        val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        var view = currentFocus
        if (view == null) {
            view = View(this)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    @get:LayoutRes
    abstract val layoutResourceId: Int
}