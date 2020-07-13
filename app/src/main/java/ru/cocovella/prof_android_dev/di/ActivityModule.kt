package ru.cocovella.prof_android_dev.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.cocovella.prof_android_dev.view.main.MainActivity

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}
