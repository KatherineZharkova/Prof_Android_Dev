package ru.cocovella.prof_android_dev.model.datasource

import ru.cocovella.prof_android_dev.model.data.DataModel

class RoomDataBaseImplementation : DataSource<List<DataModel>> {
    override suspend fun getData(word: String): List<DataModel> { TODO("not implemented") }
}
