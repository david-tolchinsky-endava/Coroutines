package com.endava.coroutines

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class DelayUseCase(
    private val dispatcher: CoroutineDispatcher = IO,
    private val coroutineContext: CoroutineContext
) {

    suspend operator fun invoke(): Int {
        println("TestCoroutine - DelayUseCase - invoke = 1")
        return withContext(dispatcher + coroutineContext) {
            println("TestCoroutine - DelayUseCase - invoke = 2")
            delay(5000)
            1
        }
    }
}
