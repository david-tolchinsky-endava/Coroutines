package com.endava.coroutines

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DummyViewModel(private val delayUseCase: DelayUseCase) : ViewModel() {

    fun dummyCall() {

        Job()
        val delayCoroutine = viewModelScope.launch {
            val result = delayUseCase()
            result.toShort()
            println("TestCoroutine - dummyCall - result FINAL = $result")
        }
        viewModelScope.launch {
            while (true) {
                delay(10000)
                val delayJobState = if (delayCoroutine.isActive) {
                    "alive"
                } else {
                    "dead"
                }
                println(
                    "TestCoroutine - dummyCall - I'm another coroutine validating the delayJob," +
                            " and the state is = $delayJobState"
                )
            }
        }
    }
}
