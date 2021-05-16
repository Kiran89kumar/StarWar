package com.star.war.utils

import androidx.annotation.MainThread
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Schedules {@link Single} to execute in {@code io} and observe it on {@code mainThread}
 */
@MainThread
fun <T> Single<T>.async() =
    subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

/**
 * Schedules {@link Observable} to execute in {@code io} and observe it on {@code mainThread}
 */
@MainThread
fun <T> Observable<T>.async() =
    subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

/**
 * Schedules {@link Completable} to execute in {@code io} and observe it on {@code mainThread}
 */
@MainThread
fun Completable.async() =
    subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())