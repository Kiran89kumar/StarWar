package com.star.war.repo.network

import com.star.war.repo.network.exceptions.ServerException
import com.star.war.ui.helper.BaseTest
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import org.junit.Test
import retrofit2.CallAdapter
import retrofit2.HttpException
import retrofit2.Retrofit
import java.lang.reflect.Type

@Suppress("UNCHECKED_CAST")
class RxMainObservableCallAdapterFactoryTest : BaseTest() {

    private companion object {
        const val EMITTED_OBJECT = "EMITTED_OBJECT"
        const val ERROR_MESSAGE =
            "{\"code\":\"92001\",\"errorType\":\"Bad request\", \"message\":\"Error message\"}"
    }

    @MockK
    private lateinit var type: Type

    @MockK
    private lateinit var retrofit: Retrofit

    @MockK
    private lateinit var nextAdapter: CallAdapter<Any, Any>

    private val arrayAnnotation: Array<Annotation> = emptyArray()

    private val rxCallAdapterFactory: RxMainObservableCallAdapterFactory = RxMainObservableCallAdapterFactory()

    override fun setUp() {
        super.setUp()
        every { retrofit.nextCallAdapter(any(), any(), any()) } returns nextAdapter
    }

    @Test
    fun get_onSingle_changesObservableThread() {
        every { nextAdapter.adapt(any()) } returns Single.just(EMITTED_OBJECT)
        val single = getAdapter().adapt(mockk())
        (single as Single<String>).test().assertValue(EMITTED_OBJECT)
    }

    @Test
    fun get_onSingleError_getsSameError() {
        every { nextAdapter.adapt(any()) } returns Single.error<String>(ArithmeticException())
        val single = getAdapter().adapt(mockk())
        (single as Single<String>).test().assertError(RuntimeException::class.java)
    }

    @Test
    fun get_onSingleHttpExceptionResponseIsNull_getsSameError() {
        val response = mockk<HttpException>(relaxed = true)
        every { response.response() } returns null
        every { nextAdapter.adapt(any()) } returns Single.error<String>(response)
        val single = getAdapter().adapt(mockk())
        (single as Single<String>).test().assertError(ServerException::class.java)
    }

    @Test
    fun get_onSingleHttpExceptionErrorBodyIsNull_getsSameError() {
        val response = mockk<HttpException>(relaxed = true)
        every { response.response()?.errorBody() } returns null
        every { nextAdapter.adapt(any()) } returns Single.error<String>(response)
        val single = getAdapter().adapt(mockk())
        (single as Single<String>).test().assertError(ServerException::class.java)
    }

    @Test
    fun get_onCompletableSuccess_completesChain() {
        every { nextAdapter.adapt(any()) } returns Completable.complete()
        val completable = getAdapter().adapt(mockk())
        (completable as Completable).test().assertComplete()
    }

    @Test
    fun get_onObservableSuccess_completesChain() {
        every { nextAdapter.adapt(any()) } returns Observable.just<String>(
            EMITTED_OBJECT,
            ERROR_MESSAGE
        )
        val observable = getAdapter().adapt(mockk())
        (observable as Observable<String>).test().assertValues(EMITTED_OBJECT, ERROR_MESSAGE)
    }

    private fun getAdapter() = (rxCallAdapterFactory.get(
        type,
        arrayAnnotation,
        retrofit
    ) as RxMainObservableCallAdapterFactory.RxMainObservableCallAdapter)
}