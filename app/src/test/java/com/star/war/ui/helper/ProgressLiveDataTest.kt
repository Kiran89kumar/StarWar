package com.star.war.ui.helper

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import com.star.war.ui.base.BaseFragment
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import junitparams.JUnitParamsRunner
import junitparams.Parameters
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(JUnitParamsRunner::class)
class ProgressLiveDataTest : BaseTest() {

    @RelaxedMockK
    private lateinit var fragment: BaseFragment

    private val liveData = ProgressLiveData<String>()

    override fun setUp() {
        super.setUp()
        every { fragment.viewLifecycleOwner } returns TestLifecycleOwner()
    }

    @Parameters(method = "testArguments")
    @Test
    fun showLoading_inSpecificLifecycleStates_appropriateLoadingMethodsShouldBeCalled(
        state: Lifecycle.State,
        shouldCall: Boolean
    ) {
        every { fragment.lifecycle.currentState } returns state

        liveData.observeWithFragment(fragment, Observer { })
        liveData.setValue(String())

        verify(exactly = if (shouldCall) -1 else 0) {
            fragment.showLoading()
            fragment.hideLoading()
        }
    }

    private fun testArguments(): Array<Array<Any>> {
        return arrayOf(
            arrayOf(Lifecycle.State.CREATED, true),
            arrayOf(Lifecycle.State.RESUMED, true),
            arrayOf(Lifecycle.State.DESTROYED, false),
            arrayOf(Lifecycle.State.INITIALIZED, false),
            arrayOf(Lifecycle.State.STARTED, false)
        )
    }
}