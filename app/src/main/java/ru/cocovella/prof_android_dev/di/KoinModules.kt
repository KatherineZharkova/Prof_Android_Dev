package ru.cocovella.prof_android_dev.di

import androidx.room.Room
import org.koin.dsl.module
import ru.cocovella.prof_android_dev.model.data.DataModel
import ru.cocovella.prof_android_dev.model.datasource.RetrofitImplementation
import ru.cocovella.prof_android_dev.model.datasource.RoomDataBaseImplementation
import ru.cocovella.prof_android_dev.model.repository.Repository
import ru.cocovella.prof_android_dev.model.repository.RepositoryImplementation
import ru.cocovella.prof_android_dev.model.repository.RepositoryImplementationLocal
import ru.cocovella.prof_android_dev.model.repository.RepositoryLocal
import ru.cocovella.prof_android_dev.room.HistoryDataBase
import ru.cocovella.prof_android_dev.view.history.HistoryInteractor
import ru.cocovella.prof_android_dev.view.history.HistoryViewModel
import ru.cocovella.prof_android_dev.view.main.MainInteractor
import ru.cocovella.prof_android_dev.view.main.MainViewModel

val application = module {
    single { Room.databaseBuilder(get(), HistoryDataBase::class.java, "HistoryDBase").build() }
    single { get<HistoryDataBase>().historyDao() }
    single<Repository<List<DataModel>>> { RepositoryImplementation(RetrofitImplementation()) }
    single<RepositoryLocal<List<DataModel>>> { RepositoryImplementationLocal(RoomDataBaseImplementation(get()))
    }
}

val mainScreen = module {
    factory { MainViewModel(get()) }
    factory { MainInteractor(get(), get()) }
}

val historyScreen = module {
    factory { HistoryViewModel(get()) }
    factory { HistoryInteractor(get(), get()) }
}
