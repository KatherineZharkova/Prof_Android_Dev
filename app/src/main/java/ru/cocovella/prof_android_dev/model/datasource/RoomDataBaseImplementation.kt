package ru.cocovella.prof_android_dev.model.datasource

import io.reactivex.Observable
import ru.cocovella.prof_android_dev.model.data.SearchResult

class RoomDataBaseImplementation : DataSource<List<SearchResult>> {

    override fun getData(word: String): Observable<List<SearchResult>> {
        TODO("not implemented")
    }
}
