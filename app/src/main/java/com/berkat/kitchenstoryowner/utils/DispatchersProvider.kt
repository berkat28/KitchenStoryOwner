package com.berkat.kitchenstoryowner.utils

import kotlinx.coroutines.CoroutineDispatcher

interface DispatchersProvider {

    val main: CoroutineDispatcher
    val io: CoroutineDispatcher
    val default: CoroutineDispatcher
    val unconfined: CoroutineDispatcher
}