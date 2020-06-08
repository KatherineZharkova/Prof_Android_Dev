package ru.cocovella.prof_android_dev.presenter

import io.reactivex.Observable

interface Interactor<T> {
    fun getData(word: String, fromRemoteSource: Boolean): Observable<T>
}