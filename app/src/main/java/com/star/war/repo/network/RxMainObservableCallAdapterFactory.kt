package com.star.war.repo.network

import com.star.war.repo.network.exceptions.ServerException
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.HttpException
import retrofit2.Retrofit
import java.io.IOException
import java.lang.reflect.Type

@Suppress("UNCHECKED_CAST")
class RxMainObservableCallAdapterFactory : CallAdapter.Factory() {

    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? = RxMainObservableCallAdapter(
        retrofit,
        retrofit.nextCallAdapter(
            this,
            returnType,
            annotations
        ) as CallAdapter<Any, Any>
    )

    class RxMainObservableCallAdapter(
        private val retrofit: Retrofit,
        private val nextAdapter: CallAdapter<Any, Any>
    ) :
        CallAdapter<Any, Any> {

        override fun adapt(call: Call<Any>): Any {
            val adaptedCall = nextAdapter.adapt(call)
            if (adaptedCall is Completable) {
                return adaptedCall.onErrorResumeNext { throwable ->
                    Completable.error(asRetrofitException(throwable))
                }
            }
            if (adaptedCall is Single<*>) {
                return adaptedCall.onErrorResumeNext { throwable ->
                    Single.error(asRetrofitException(throwable))
                }
            }
            if (adaptedCall is Observable<*>) {
                return adaptedCall.onErrorResumeNext { throwable: Throwable ->
                    Observable.error(asRetrofitException(throwable))
                }
            }
            if (adaptedCall is Maybe<*>) {
                return adaptedCall.onErrorResumeNext { throwable: Throwable ->
                    Maybe.error(asRetrofitException(throwable))
                }
            }
            throw RuntimeException("Observable Type not supported")
        }


        private fun asRetrofitException(throwable: Throwable): ServerException {
            // We had non-200 http error
            if (throwable is HttpException) {
                val response = throwable.response()
                if (response != null) {
                    return ServerException.httpError(
                        response.raw().request.url.toString(),
                        response, retrofit
                    )
                }
            }

            // A network error happened
            return if (throwable is IOException) {
                ServerException.networkError(throwable)
            } else ServerException.unexpectedError(throwable)

            // We don't know what happened. We need to simply convert to an unknown error
        }

        override fun responseType(): Type = nextAdapter.responseType()
    }
}