package ru.cocovella.history_screen.history

import androidx.lifecycle.LiveData
import kotlinx.coroutines.launch
import ru.cocovella.history_screen.parseLocalSearchResults
import ru.cocovella.prof_android_dev.viewmodel.BaseViewModel
import ru.cocovella.repo.model.data.AppState

class HistoryViewModel(private val interactor: HistoryInteractor) :
    BaseViewModel<AppState>() {

    private val liveDataForViewToObserve: LiveData<AppState> = mutableLiveData

    fun subscribe(): LiveData<AppState> = liveDataForViewToObserve

    override fun getData(word: String, isOnline: Boolean) {
        mutableLiveData.value = AppState.Loading(null)
        cancelJob()
        viewModelCoroutineScope.launch { startInteractor(word, isOnline) }
    }

    private suspend fun startInteractor(word: String, isOnline: Boolean) {
        mutableLiveData.postValue(parseLocalSearchResults(interactor.getData(word, isOnline)))
    }

    override fun handleError(error: Throwable) {
        mutableLiveData.postValue(AppState.Error(error))
    }

    override fun onCleared() {
        mutableLiveData.value = AppState.Success(null)
        super.onCleared()
    }
}
