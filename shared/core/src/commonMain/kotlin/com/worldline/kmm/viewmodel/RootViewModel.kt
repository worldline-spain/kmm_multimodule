package com.worldline.kmm.viewmodel

import com.worldline.kmm.core.Either
import com.worldline.kmm.core.Error
import com.worldline.kmm.flow.CFlow
import com.worldline.kmm.flow.CStateFlow
import com.worldline.kmm.flow.cFlow
import com.worldline.kmm.flow.cStateFlow
import com.worldline.kmm.viewmodel.executor.Executor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

abstract class RootViewModel<S, E, A>(initialState: S) : KoinComponent, PlatformViewModel() {

    private val job = SupervisorJob()

    private val executor: Executor by inject()

    protected val vmScope: CoroutineScope get() = CoroutineScope(job + executor.main)

    protected val _uiState = MutableStateFlow(initialState)
    val state: CStateFlow<S> = _uiState.cStateFlow()

    protected val _actions = Channel<A>(Channel.BUFFERED)
    val actions: CFlow<A> = _actions.receiveAsFlow().cFlow()

    abstract fun attach(): RootViewModel<S, E, A>

    override fun detach() {
        super.detach()
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