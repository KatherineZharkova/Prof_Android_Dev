package ru.cocovella.prof_android_dev.model.repository

import io.reactivex.Observable
import ru.cocovella.prof_android_dev.model.data.SearchResult
import ru.cocovella.prof_android_dev.model.datasource.DataSource

class RepositoryImplementation(private val dataSource: DataSource<List<SearchResult>>) :
    Repository<List<SearchResult>> {
    override fun getData(word: String): Observable<List<SearchResult>> = dataSource.getData(word)
}
