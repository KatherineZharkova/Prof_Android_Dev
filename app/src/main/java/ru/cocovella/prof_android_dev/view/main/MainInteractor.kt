package ru.cocovella.prof_android_dev.view.main

import ru.cocovella.prof_android_dev.model.data.AppState
import ru.cocovella.prof_android_dev.model.data.DataModel
import ru.cocovella.prof_android_dev.model.repository.Repository
import ru.cocovella.prof_android_dev.model.repository.RepositoryLocal
import ru.cocovella.prof_android_dev.viewmodel.Interactor

class MainInteractor (
    private val repositoryRemote: Repository<List<DataModel>>,
    private val repositoryLocal: RepositoryLocal<List<DataModel>>
) : Interactor<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        val appState: AppState
        if (fromRemoteSource) {
            appState = AppState.Success(repositoryRemote.getData(word))
            repositoryLocal.saveToDB(appState)
        } else {
            appState = AppState.Success(repositoryLocal.getData(word))
        }
        return appState
    }
}
