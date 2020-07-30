package ru.cocovella.history_screen.history

import ru.cocovella.prof_android_dev.utils.mapSearchResultToResult
import ru.cocovella.prof_android_dev.viewmodel.Interactor
import ru.cocovella.repo.Repository
import ru.cocovella.repo.RepositoryLocal
import ru.cocovella.repo.model.data.AppState
import ru.cocovella.repo.model.data.dto.SearchResultDto

class HistoryInteractor(
    private val repositoryRemote: Repository<List<SearchResultDto>>,
    private val repositoryLocal: RepositoryLocal<List<SearchResultDto>>
) : Interactor<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        return AppState.Success(
            mapSearchResultToResult(
                if (fromRemoteSource) {
                    repositoryRemote
                } else {
                    repositoryLocal
                }.getData(word)
            )
        )
    }
}
