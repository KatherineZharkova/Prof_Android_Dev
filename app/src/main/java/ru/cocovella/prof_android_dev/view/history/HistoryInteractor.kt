package ru.cocovella.prof_android_dev.view.history

import ru.cocovella.prof_android_dev.viewmodel.Interactor
import ru.cocovella.repo.Repository
import ru.cocovella.repo.RepositoryLocal
import ru.cocovella.repo.model.data.AppState
import ru.cocovella.repo.model.data.DataModel

class HistoryInteractor(
    private val repositoryRemote: Repository<List<DataModel>>,
    private val repositoryLocal: RepositoryLocal<List<DataModel>>
) : Interactor<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        return AppState.Success(
            if (fromRemoteSource) {
                repositoryRemote
            } else {
                repositoryLocal
            }.getData(word)
        )
    }
}
