package com.kotlin.rxexercise

import android.util.Log
import com.kotlin.rxexercise.data.User
import com.kotlin.rxexercise.ui.MainActivity
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableOnSubscribe
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.observables.GroupedObservable
import java.util.Arrays
import java.util.concurrent.TimeUnit

private const val TAG = "[CRS] Operators"

val mList1 = mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11)
val mArray1 = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11)
val mArray2 = arrayOf(1, 50, 2, 40, 3, 60, 4, 5, 6, 7, 8, 9, 10, 11)

val mUserList = mutableListOf<User>(
    User(1, "John", 25),
    User(2, "Jane", 30),
    User(3, "Doe", 35),
    User(4, "Smith", 40),
    User(5, "Doe", 45),
    User(6, "John", 50),
)

fun justOperator() {
    val observable = Observable.just(mList1)

    val observer = object : Observer<List<Int>> {
        override fun onSubscribe(d: Disposable) {
            Log.d(TAG, "onSubscribe: ")
        }

        override fun onError(e: Throwable) {
            Log.d(TAG, "onError: ${e.message}")
        }

        override fun onComplete() {
            Log.d(TAG, "onComplete: ")
        }

        override fun onNext(t: List<Int>) {
            Log.d(TAG, "onNext: $t")
        }
    }

    observable.subscribe(observer)
}

fun fromArrayOperator() {
    val observable = Observable.fromArray(mArray1, mArray2)

    val observer = object : Observer<Array<Int>> {
        override fun onSubscribe(d: Disposable) {
            Log.d(TAG, "onSubscribe: ")
        }

        override fun onError(e: Throwable) {
            Log.d(TAG, "onError: ${e.message}")
        }

        override fun onComplete() {
            Log.d(TAG, "onComplete: ")
        }

        override fun onNext(t: Array<Int>) {
            Log.d(TAG, "onNext: ${t.contentToString()}")
        }
    }

    observable.subscribe(observer)
}

fun fromIterableOperator() {
    val observable = Observable.fromIterable(mList1)

    val observer = object : Observer<Int> {
        override fun onSubscribe(d: Disposable) {
            Log.d(TAG, "onSubscribe: ")
        }

        override fun onError(e: Throwable) {
            Log.d(TAG, "onError: ${e.message}")
        }

        override fun onComplete() {
            Log.d(TAG, "onComplete: ")
        }

        override fun onNext(t: Int) {
            Log.d(TAG, "onNext: $t")
        }
    }

    observable.subscribe(observer)
}

fun rangeOperator(): Observable<Int> {
    return Observable.range(1, 10)
}

fun repeatOperator(): Observable<Int> {
    return Observable.range(1, 10).repeat(2)
}

fun intervalOperator(): Observable<Long> {
    return Observable.interval(2, TimeUnit.SECONDS)
}

fun timerOperator(): Observable<Long> {
    return Observable.timer(3L, TimeUnit.SECONDS)
}

fun createOperator(): Observable<Int> {
    return Observable.create(ObservableOnSubscribe {
        try {
            for (i in 1..10) {
                it.onNext(i * 5)
            }
            it.onComplete()
        } catch (e: Exception) {
            it.onError(e)
        }
    })
}

fun filterOperator(): Observable<User> {
    return Observable.fromIterable(mUserList)
        .filter { it.age > 30 }
}

fun lastOperator(): Single<User> {
    return Observable.fromIterable(mutableListOf<User>())
        .last(User(0, "Default", 0))
}

fun userListObservable(): Observable<User> {
    return Observable.fromIterable(mUserList)
}