package com.kotlin.rxexercise

import android.util.Log
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable

fun createObservable(): Observable<Int> {
    return Observable.create { emitter ->
        try {
            if (!emitter.isDisposed) {
                for (i in 0..100) {
                    emitter.onNext(i)
                }
                emitter.onComplete()
            }
        } catch (e: Exception) {
            emitter.onError(e)
        }
    }
}

fun createSingleObservable(): Single<Int> {
    return Single.create { emitter ->
        try {
            if (!emitter.isDisposed) {
                emitter.onSuccess(1)
            }
        } catch (e: Exception) {
            emitter.onError(e)
        }

    }
}

fun getSingleObserver(): SingleObserver<Int> {
    return object : SingleObserver<Int> {
        override fun onSubscribe(d: Disposable) {
            Log.d(TAG, "onSubscribe: ")
        }

        override fun onError(e: Throwable) {
            Log.d(TAG, "onError: ${e.message}")
        }

        override fun onSuccess(t: Int) {
            Log.d(TAG, "onSuccess: $t")
        }

    }
}

fun getObserver(): Observer<Int> {
    return object : Observer<Int> {
        override fun onSubscribe(d: Disposable) {
            Log.d(TAG, "onSubscribe: ")
        }

        override fun onNext(t: Int) {
            Log.d(TAG, "onNext: $t")
        }

        override fun onError(e: Throwable) {
            Log.d(TAG, "onError: ${e.message}")
        }

        override fun onComplete() {
            Log.d(TAG, "onComplete: ")
        }
    }
}

private const val TAG = "[CRS]"