package ru.cocovella.prof_android_dev.view.main

import io.reactivex.Observable
import ru.cocovella.prof_android_dev.di.NAME_LOCAL
import ru.cocovella.prof_android_dev.di.NAME_REMOTE
import ru.cocovella.prof_android_dev.model.data.AppState
import ru.cocovella.prof_android_dev.model.data.DataModel
import ru.cocovella.prof_android_dev.model.repository.Repository
import ru.cocovella.prof_android_dev.viewmodel.Interactor
import javax.inject.Inject
import javax.inject.Named

class MainInteractor @Inject constructor(
    @Named(NAME_REMOTE) val repositoryRemote: Repository<List<DataModel>>,
    @Named(NAME_LOCAL) val repositoryLocal: Repository<List<DataModel>>
) : Interactor<AppState> {

    override fun getData(word: String, fromRemoteSource: Boolean): Observable<AppState> =
        if (fromRemoteSource) {
            repositoryRemote
        } else {
            repositoryLocal
        }.getData(word).map { AppState.Success(it) }
}
