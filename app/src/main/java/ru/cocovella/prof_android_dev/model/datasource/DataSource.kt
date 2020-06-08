package ru.cocovella.prof_android_dev.model.datasource

import io.reactivex.Observable

interface DataSource<T> {
    fun getData(word: String): Observable<T>
}
