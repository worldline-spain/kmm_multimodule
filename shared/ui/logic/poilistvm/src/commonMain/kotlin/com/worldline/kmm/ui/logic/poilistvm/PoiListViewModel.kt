package com.worldline.kmm.ui.logic.poilistvm

import com.worldline.kmm.core.Poi
import com.worldline.kmm.feature.poi.PoiRepository
import com.worldline.kmm.ui.logic.poilistvm.executor.Executor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PoiListViewModel(private val executor: Executor, private val poiRepository: PoiRepository) {

    private val job = SupervisorJob()

    private val scope = CoroutineScope(job + executor.main)
    private val _uiState = MutableStateFlow<PoiListState>(PoiListState.InProgress)

    val state: StateFlow<PoiListState> = _uiState

    fun attach() {
        scope.launch {
            _uiState.value = PoiListState.InProgress

            poiRepository.getAll(false).fold(
                error = { _uiState.value = PoiListState.Error(it) },
                success = { _uiState.value = PoiListState.Success(it) }
            )
        }
    }

    fun detach() {
        scope.cancel()
    }
}

sealed class PoiListState {
    object InProgress : PoiListState()
    class Error(val error: com.worldline.kmm.core.Error) : PoiListState()
    class Success(val pois: List<Poi>) : PoiListState()
}