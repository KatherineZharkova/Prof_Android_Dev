package ru.cocovella.prof_android_dev.view.main

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import ru.cocovella.prof_android_dev.model.data.DataModel
import ru.cocovella.prof_android_dev.model.datasource.DataSourceLocal
import ru.cocovella.prof_android_dev.model.datasource.DataSourceRemote
import ru.cocovella.prof_android_dev.model.repository.RepositoryImplementation
import ru.cocovella.prof_android_dev.presenter.Presenter
import ru.cocovella.prof_android_dev.rx.SchedulerProvider
import ru.cocovella.prof_android_dev.view.base.View

open class MainPresenterImpl<T : DataModel, V : View>(
    private val interactor: MainInteractor = MainInteractor(
        RepositoryImplementation(DataSourceRemote()),
        RepositoryImplementation(DataSourceLocal())
    ),
    private val compositeDisposable: CompositeDisposable = CompositeDisposable(),
    private val schedulerProvider: SchedulerProvider = SchedulerProvider()
) : Presenter<T, V> {

    private var currentView: V? = null

    override fun attachView(view: V) {
        if (view != currentView) {
            currentView = view
        }
    }

    override fun detachView(view: V) {
        compositeDisposable.clear()
        if (view == currentView) {
            currentView = null
        }
    }

    override fun getData(word: String, isOnline: Boolean) {
        compositeDisposable.add(
            interactor.getData(word, isOnline)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .doOnSubscribe(doOnSubscribe())
                .subscribeWith(getObserver())
        )
    }

    private fun doOnSubscribe(): (Disposable) -> Unit =
        { currentView?.renderData(DataModel.Loading(null)) }

    private fun getObserver(): DisposableObserver<DataModel> {
        return object : DisposableObserver<DataModel>() {

            override fun onNext(data: DataModel) {
                currentView?.renderData(data)
            }

            override fun onError(e: Throwable) {
                currentView?.renderData(DataModel.Error(e))
            }

            override fun onComplete() {
            }
        }
    }
}
