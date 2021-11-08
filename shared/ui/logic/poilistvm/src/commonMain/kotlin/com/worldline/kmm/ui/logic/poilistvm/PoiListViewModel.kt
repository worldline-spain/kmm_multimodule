package com.worldline.kmm.ui.logic.poilistvm

import com.worldline.kmm.core.Poi
import com.worldline.kmm.feature.poi.PoiRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class PoiListViewModel : RootViewModel(), KoinComponent {

    private val poiRepository: PoiRepository by inject()

    private val _uiState = MutableStateFlow<PoiListState>(PoiListState.InProgress)

    override val state: StateFlow<PoiListState> = _uiState

    override fun attach() {
        vmScope.launch {
            _uiState.value = PoiListState.InProgress

            poiRepository.getAll(false).fold(
                error = { _uiState.value = PoiListState.Error(it) },
                success = { _uiState.value = PoiListState.Success(it) }
            )
        }
    }
}

sealed class PoiListState : ViewState() {
    object InProgress : PoiListState()
    class Error(val error: com.worldline.kmm.core.Error) : PoiListState()
    class Success(val pois: List<Poi>) : PoiListState()
}