package com.worldline.kmm.ui.logic.poilistvm

import com.worldline.kmm.core.Either
import com.worldline.kmm.core.Error
import com.worldline.kmm.ui.logic.poilistvm.executor.Executor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

actual abstract class RootViewModel : KoinComponent {

    private val job = SupervisorJob()

    private val executor: Executor by inject()

    protected actual val vmScope: CoroutineScope get() = CoroutineScope(job + executor.main)

    actual abstract fun attach()

    actual open fun detach() {
        job.cancelChildren()
    }

    protected suspend fun <T> execute(f: suspend () -> Either<Error, T>): Either<Error, T> =
        withContext(executor.bg) { f() }

    actual abstract val state: StateFlow<ViewState>
}