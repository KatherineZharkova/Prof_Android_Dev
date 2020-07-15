package ru.cocovella.prof_android_dev.di

import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.cocovella.prof_android_dev.model.data.DataModel
import ru.cocovella.prof_android_dev.model.datasource.RetrofitImplementation
import ru.cocovella.prof_android_dev.model.datasource.RoomDataBaseImplementation
import ru.cocovella.prof_android_dev.model.repository.Repository
import ru.cocovella.prof_android_dev.model.repository.RepositoryImplementation
import ru.cocovella.prof_android_dev.view.main.MainInteractor
import ru.cocovella.prof_android_dev.view.main.MainViewModel

val application = module {
    single<Repository<List<DataModel>>>(named(NAME_REMOTE)) {
        RepositoryImplementation(RetrofitImplementation())
    }
    single<Repository<List<DataModel>>>(named(NAME_LOCAL)) {
        RepositoryImplementation(RoomDataBaseImplementation())
    }
}

val mainScreen = module {
    factory { MainInteractor(get(named(NAME_REMOTE)), get(named(NAME_LOCAL))) }
    factory { MainViewModel(get()) }
}
