package ru.cocovella.history_screen

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.cocovella.history_screen.history.HistoryActivity
import ru.cocovella.history_screen.history.HistoryInteractor
import ru.cocovella.history_screen.history.HistoryViewModel

fun loadModules() = loadFeature

private val loadFeature by lazy {
    loadKoinModules(listOf(historyScreen))
}

val historyScreen = module {
    scope(named<HistoryActivity>()) {
        scoped { HistoryInteractor(get(), get()) }
        viewModel { HistoryViewModel(get()) }
    }
}
