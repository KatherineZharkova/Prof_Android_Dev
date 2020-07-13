package ru.cocovella.prof_android_dev.model.datasource

import io.reactivex.Observable
import ru.cocovella.prof_android_dev.model.data.DataModel

class RoomDataBaseImplementation : DataSource<List<DataModel>> {
    override fun getData(word: String): Observable<List<DataModel>> { TODO("not implemented") }
}
