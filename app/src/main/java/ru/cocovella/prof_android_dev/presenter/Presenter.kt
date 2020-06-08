package ru.cocovella.prof_android_dev.presenter

import ru.cocovella.prof_android_dev.model.data.DataModel
import ru.cocovella.prof_android_dev.view.base.View

interface Presenter<T : DataModel, V : View> {
    fun attachView(view: V)
    fun detachView(view: V)
    fun getData(word: String, isOnline: Boolean)
}
