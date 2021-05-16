package com.star.war.ui.helper

import com.star.war.repo.network.StarWarsApi
import com.star.war.utils.TestTaskExecutorRule
import io.mockk.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Before
import org.junit.Rule

open class BaseTest {

    @get:Rule
    val executorRule = TestTaskExecutorRule()

    private val apiMock = lazy { mockk<StarWarsApi>(relaxed = true, relaxUnitFun = true) }

    val api
        get() = apiMock.value

    @Before
    open fun setUp() {
        MockKAnnotations.init(this)

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