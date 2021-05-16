package com.star.war.ui.helper

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import java.lang.IllegalStateException

class ObservableDataTest {

    @Test
    fun hasError_whenHasError_returnsTrue() {
        val data = ObservableData<String, Throwable>(null, IllegalStateException())

        assertThat(data.hasError()).isTrue()
        assertThat(data.hasData()).isFalse()
    }

    @Test
    fun hasError_whenHasNoError_returnsFalse() {
        val data = ObservableData<String, Throwable>(String(), null)

        assertThat(data.hasError()).isFalse()
        assertThat(data.hasData()).isTrue()
    }
}