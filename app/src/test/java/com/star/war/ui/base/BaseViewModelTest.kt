package com.star.war.ui.base

import android.os.Looper
import com.star.war.utils.TestTaskExecutorRule
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockkStatic
import io.mockk.unmockkAll
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Before
import org.junit.Rule

abstract class BaseViewModelTest<VM : BaseViewModel, UC : Any> {

    @get:Rule
    val executorRule = TestTaskExecutorRule()

    @MockK
    lateinit var mainLooper: Looper

    @MockK
    lateinit var useCase: UC

    abstract val viewModel: VM

    init {
        MockKAnnotations.init(this)
    }

    @Before
    open fun setUp() {
        mockkStatic(Looper::class)

        every { Looper.getMainLooper() } returns mainLooper
        every { mainLooper.thread } returns Thread.currentThread()

        // Set Main Thread Schedulers
        mockkStatic(AndroidSchedulers::class)

        every {
            AndroidSchedulers.mainThread()
        } returns Schedulers.trampoline()
    }

    @After
    open fun tearDown() {
        unmockkAll()
    }
}