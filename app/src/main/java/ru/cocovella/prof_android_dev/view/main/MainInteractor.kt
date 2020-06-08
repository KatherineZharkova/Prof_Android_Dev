package ru.cocovella.prof_android_dev.view.main

import io.reactivex.Observable
import ru.cocovella.prof_android_dev.model.data.DataModel
import ru.cocovella.prof_android_dev.model.data.SearchResult
import ru.cocovella.prof_android_dev.model.repository.Repository
import ru.cocovella.prof_android_dev.presenter.Interactor

class MainInteractor(
    private val remoteRepository: Repository<List<SearchResult>>,
    private val localRepository: Repository<List<SearchResult>>
) : Interactor<DataModel> {

    override fun getData(word: String, fromRemoteSource: Boolean): Observable<DataModel> {
        return if (fromRemoteSource) {
            remoteRepository.getData(word).map { DataModel.Success(it) }
        } else {
            localRepository.getData(word).map { DataModel.Success(it) }
        }
    }
}
