package com.kotlin.rxexercise.ui.rx

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RxViewModel @Inject constructor() : ViewModel() {

  fun sendIntent(intent: Any) {
    // handle intent
  }

  val stateFlow = {}
}
