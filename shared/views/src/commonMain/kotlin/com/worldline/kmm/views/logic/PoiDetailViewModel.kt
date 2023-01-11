package com.worldline.kmm.views.logic

import com.worldline.kmm.core.Poi
import com.worldline.kmm.data.repository.logic.Repository
import com.worldline.kmm.viewmodel.RootViewModel
import com.worldline.kmm.viewmodel.ViewState
import kotlinx.coroutines.launch
import org.koin.core.component.inject

class PoiDetailViewModel(private val poiId: Long) :
    RootViewModel<PoiDetailState, PoiDetailEvent, PoiDetailAction>(PoiDetailState.InProgress) {

    private val repository: Repository by inject()

    override fun attach(): PoiDetailViewModel = apply {
        vmScope.launch {
            _uiState.value = PoiDetailState.InProgress

            execute { repository.getPOIById(poiId) }.fold(
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

sealed class PoiDetailAction {

}