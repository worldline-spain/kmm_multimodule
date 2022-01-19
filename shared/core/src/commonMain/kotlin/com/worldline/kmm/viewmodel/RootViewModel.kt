package com.worldline.kmm.viewmodel

import com.worldline.kmm.core.Either
import com.worldline.kmm.core.Error
import com.worldline.kmm.viewmodel.executor.Executor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

abstract class RootViewModel : KoinComponent {

    private val job = SupervisorJob()

    private val executor: Executor by inject()

    protected val vmScope: CoroutineScope get() = CoroutineScope(job + executor.main)

    abstract val state: StateFlow<ViewState>

    abstract fun attach()

    open fun detach() {
        job.cancelChildren()
    }

    protected suspend fun <T> execute(f: suspend () -> Either<Error, T>): Either<Error, T> =
        withContext(executor.bg) { f() }

    fun <T> Flow<T>.observe(onChange: ((T) -> Unit)) {
        onEach {
            onChange(it)
        }.launchIn(
            vmScope
        )
    }
}

open class ViewState