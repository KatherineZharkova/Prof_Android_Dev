package ru.cocovella.prof_android_dev.rx

import io.reactivex.Scheduler

interface ISchedulerProvider {
    fun ui(): Scheduler
    fun io(): Scheduler
}
