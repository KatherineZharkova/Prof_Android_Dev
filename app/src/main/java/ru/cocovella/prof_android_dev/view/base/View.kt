package ru.cocovella.prof_android_dev.view.base

import ru.cocovella.prof_android_dev.model.data.DataModel

interface View {
    fun renderData(dataModel: DataModel)
}
