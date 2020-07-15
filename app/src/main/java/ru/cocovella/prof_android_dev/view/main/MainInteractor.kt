package ru.cocovella.prof_android_dev.view.main

import ru.cocovella.prof_android_dev.model.data.AppState
import ru.cocovella.prof_android_dev.model.data.DataModel
import ru.cocovella.prof_android_dev.model.repository.Repository
import ru.cocovella.prof_android_dev.viewmodel.Interactor

class MainInteractor (
    private val repositoryRemote: Repository<List<DataModel>>,
    private val repositoryLocal: Repository<List<DataModel>>
) : Interactor<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean) =
        AppState.Success(
            if (fromRemoteSource) {
                repositoryRemote
            } else {
                repositoryLocal
            }.getData(word)
        )

}
