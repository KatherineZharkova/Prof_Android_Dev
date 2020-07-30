package ru.cocovella.prof_android_dev.di

import androidx.room.Room
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.cocovella.prof_android_dev.view.main.MainActivity
import ru.cocovella.prof_android_dev.view.main.MainInteractor
import ru.cocovella.prof_android_dev.view.main.MainViewModel
import ru.cocovella.repo.*
import ru.cocovella.repo.model.data.DataModel
import ru.cocovella.repo.room.HistoryDataBase

fun loadModules() = loadModules

private val loadModules by lazy { loadKoinModules(listOf(application, mainScreen)) }

val application = module {
    single { Room.databaseBuilder(get(), HistoryDataBase::class.java, "HistoryDBase").build() }
    single { get<HistoryDataBase>().historyDao() }
    single<Repository<List<DataModel>>> { RepositoryImplementation(RetrofitImplementation()) }
    single<RepositoryLocal<List<DataModel>>> { RepositoryImplementationLocal(RoomDataBaseImplementation(get())) }
}

val mainScreen = module {
    scope(named<MainActivity>()) {
        viewModel { MainViewModel(get()) }
        scoped { MainInteractor(get(), get()) }
    }

}
