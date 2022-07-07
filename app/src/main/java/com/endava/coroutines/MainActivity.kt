package com.endava.coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val job = Job()
        job.invokeOnCompletion {
            println("TestCoroutine - MainActivity - coroutineContext - I'm dead = $it")
        }
        val viewModel = DummyViewModel(DelayUseCase(coroutineContext = job))
        viewModel.dummyCall()
        lifecycleScope.launch {
            println("TestCoroutine - MainActivity - launch cancel = 1")
            delay(1000)
            println("TestCoroutine - MainActivity - launch cancel = 2")
            job.cancel()
        }
    }

}