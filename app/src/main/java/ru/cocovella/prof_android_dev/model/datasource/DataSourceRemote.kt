package ru.cocovella.prof_android_dev.model.datasource

import io.reactivex.Observable
import ru.cocovella.prof_android_dev.model.data.SearchResult

class DataSourceRemote(private val remoteProvider: RetrofitImplementation = RetrofitImplementation()) :
    DataSource<List<SearchResult>> {

    override fun getData(word: String): Observable<List<SearchResult>> =
        remoteProvider.getData(word)
}
