package com.kotlin.rxexercise.ui.rx

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun RxScreen(rxViewModel: RxViewModel) {

  Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
      Text(text = "Rx Exercise")
      // Button(
      //     onClick = { rxViewModel.sendIntent(RxIntent.FetchData) },
      //     modifier = Modifier.padding(16.dp)
      // ) { Text(text = "Fetch Data") }
      // LaunchedEffect(Unit) {
      //   rxViewModel.stateFlow.receiveAsFlow().collect { state ->
      //     when (state) {
      //       is RxState.Loading -> {
      //         // show loading
      //       }
      //       is RxState.Success -> {
      //         // show success
      //       }
      //       is RxState.Error -> {
      //         // show error
      //       }
      //     }
      //   }
      // }
    }
  }
}
