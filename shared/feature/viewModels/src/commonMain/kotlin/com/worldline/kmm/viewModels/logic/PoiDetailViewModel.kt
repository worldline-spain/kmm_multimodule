package com.worldline.kmm.feature.viewModels.logic

import com.worldline.kmm.core.Poi
import com.worldline.kmm.feature.poi.PoiRepository
import com.worldline.kmm.viewmodel.RootViewModel
import com.worldline.kmm.viewmodel.ViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.inject

class PoiDetailViewModel(private val poiId: Long) : RootViewModel<PoiDetailState>() {

    private val poiRepository: PoiRepository by inject()

    private val _uiState = MutableStateFlow<PoiDetailState>(PoiDetailState.InProgress)

    override val state: StateFlow<PoiDetailState> = _uiState

    override fun attach() {
        vmScope.launch {
            _uiState.value = PoiDetailState.InProgress

            execute { poiRepository.getById(poiId) }.fold(
                error = { _uiState.value = PoiDetailState.Error(it) },
                success = { _uiState.value = PoiDetailState.Success(it) }
            )
        }
    }

    fun onEvent(event: PoiDetailEvent) {
        when (event) {
            PoiDetailEvent.Attach -> attach()
        }
    }
}

sealed class PoiDetailState : ViewState() {
    object InProgress : PoiDetailState()
    class Error(val error: com.worldline.kmm.core.Error) : PoiDetailState()
    class Success(val poi: Poi) : PoiDetailState()
}

sealed class PoiDetailEvent {
    object Attach : PoiDetailEvent()
}