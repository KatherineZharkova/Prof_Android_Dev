package ru.cocovella.prof_android_dev.di

import androidx.room.Room
import org.koin.dsl.module
import ru.cocovella.prof_android_dev.view.history.HistoryInteractor
import ru.cocovella.prof_android_dev.view.history.HistoryViewModel
import ru.cocovella.prof_android_dev.view.main.MainInteractor
import ru.cocovella.prof_android_dev.view.main.MainViewModel
import ru.cocovella.repo.*
import ru.cocovella.repo.model.data.DataModel
import ru.cocovella.repo.room.HistoryDataBase

val application = module {
    single { Room.databaseBuilder(get(), HistoryDataBase::class.java, "HistoryDBase").build() }
    single { get<HistoryDataBase>().historyDao() }
    single<Repository<List<DataModel>>> {
        RepositoryImplementation(
            RetrofitImplementation()
        )
    }
    single<RepositoryLocal<List<DataModel>>> {
        RepositoryImplementationLocal(
            RoomDataBaseImplementation(get())
        )
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
