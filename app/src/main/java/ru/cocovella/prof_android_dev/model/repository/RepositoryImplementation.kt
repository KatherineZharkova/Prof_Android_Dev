package ru.cocovella.prof_android_dev.model.repository

import io.reactivex.Observable
import ru.cocovella.prof_android_dev.model.data.DataModel
import ru.cocovella.prof_android_dev.model.datasource.DataSource

class RepositoryImplementation(private val dataSource: DataSource<List<DataModel>>) : Repository<List<DataModel>> {
    override fun getData(word: String): Observable<List<DataModel>> = dataSource.getData(word)
}
