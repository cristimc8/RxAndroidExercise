package com.kotlin.rxexercise.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kotlin.rxexercise.createObservable
import com.kotlin.rxexercise.createSingleObservable
import com.kotlin.rxexercise.filterOperator
import com.kotlin.rxexercise.getObserver
import com.kotlin.rxexercise.getSingleObserver
import com.kotlin.rxexercise.intervalOperator
import com.kotlin.rxexercise.lastOperator
import com.kotlin.rxexercise.ui.rx.RxScreen
import com.kotlin.rxexercise.ui.rx.RxViewModel
import com.kotlin.rxexercise.ui.theme.RxExerciseTheme
import com.kotlin.rxexercise.userListObservable
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val compositeDisposable = CompositeDisposable()

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val insetsController = WindowCompat.getInsetsController(window, window.decorView)

        insetsController.apply {
            hide(WindowInsetsCompat.Type.statusBars())
            hide(WindowInsetsCompat.Type.navigationBars())
            systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }

//        userListObservable()
//            .groupBy { it.age % 10 }
//            .flatMapSingle { it.toList() }
//            .subscribe(
//            {
//                Log.d(TAG, "onNext: $it")
//            },
//            {
//                Log.d(TAG, "onError: ${it.message}")
//            },
//            {
//                Log.d(TAG, "onComplete: ")
//            },
//        )

//        createSingleObservable().subscribe(getSingleObserver())


        compositeDisposable.add(
            intervalOperator()
                .subscribeOn(Schedulers.io())
                .subscribe() {
                    Log.d(TAG, "onNext: $it")
                }
        )

        setContent { RxExerciseTheme { RxApp() } }
    }

    companion object {
        private const val TAG = "[CRS]"
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}

@Composable
fun RxApp() {
    val rxViewModel: RxViewModel = viewModel()

    RxScreen(rxViewModel)
}
