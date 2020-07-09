package ru.cocovella.prof_android_dev.view.main

import android.util.Log
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
            remoteRepository.getData(word).map { list: List<SearchResult> ->
                list.forEach {
                    Log.d("RESULT", "${it.text} ~ " +
                            "${it.meanings?.get(0)?.translation?.text} ~ " +
                            "${it.meanings?.get(0)?.imageUrl}")
                }
                DataModel.Success(list)
            }
        } else {
            localRepository.getData(word).map {
                Log.d("RESULT", "${it[0].text}")
                DataModel.Success(it) }
        }
    }
}
