package ru.cocovella.repo.model.data

import ru.cocovella.repo.model.data.userdata.DataModel

sealed class AppState {
    data class Success(val data: List<DataModel>?) : AppState()
    data class Error(val error: Throwable) : AppState()
    data class Loading(val progress: Int?) : AppState()
}
