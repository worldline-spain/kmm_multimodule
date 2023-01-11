package com.worldline.kmm.views.logic

import com.worldline.kmm.core.Poi
import com.worldline.kmm.data.repository.logic.Repository
import com.worldline.kmm.viewmodel.RootViewModel
import com.worldline.kmm.viewmodel.ViewState
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class PoiListViewModel() :
    RootViewModel<PoiListState, PoiListEvent, PoiListAction>(PoiListState.InProgress),
    KoinComponent {

    private val repository: Repository by inject()

    override fun attach(): PoiListViewModel = apply {
        vmScope.launch {
            _uiState.value = PoiListState.InProgress

            execute { repository.getAllPOIs(true) }.fold(
                error = { _uiState.value = PoiListState.Error(it) },
                success = { _uiState.value = PoiListState.Success(it) }
            )
        }
    }

    fun onEvent(event: PoiListEvent) = when (event) {
        PoiListEvent.Attach -> attach()
        is PoiListEvent.OnItemClick -> _actions.trySend(PoiListAction.NavigateToDetail(id = event.poi.id))
    }
}

sealed class PoiListState : ViewState() {
    object InProgress : PoiListState()
    class Error(val error: com.worldline.kmm.core.Error) : PoiListState()
    class Success(val pois: List<Poi>) : PoiListState()
}

sealed class PoiListEvent {
    object Attach : PoiListEvent()
    data class OnItemClick(val poi: Poi) : PoiListEvent()
}

sealed class PoiListAction {
    class NavigateToDetail(val id: Long) : PoiListAction()
}