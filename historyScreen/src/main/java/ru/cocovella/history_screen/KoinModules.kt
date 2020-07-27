package ru.cocovella.history_screen

import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import ru.cocovella.history_screen.history.HistoryInteractor
import ru.cocovella.history_screen.history.HistoryViewModel

fun loadModules() = loadFeature

private val loadFeature by lazy {
    loadKoinModules(listOf(historyScreen))
}

val historyScreen = module {
    factory { HistoryViewModel(get()) }
    factory { HistoryInteractor(get(), get()) }
}
