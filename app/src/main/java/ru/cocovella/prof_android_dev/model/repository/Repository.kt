package ru.cocovella.prof_android_dev.model.repository

import io.reactivex.Observable

interface Repository<T> {
    fun getData(word: String): Observable<T>
}
