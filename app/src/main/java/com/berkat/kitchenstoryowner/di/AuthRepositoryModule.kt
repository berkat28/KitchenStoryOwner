package com.berkat.kitchenstoryowner.di

import com.berkat.kitchenstoryowner.network.AuthApi
import com.berkat.kitchenstoryowner.repository.register.DefaultRegisterRepository
import com.berkat.kitchenstoryowner.repository.register.RegisterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object AuthRepositoryModule {

    @ViewModelScoped
    @Provides
    fun provideSignUpRepository(api: AuthApi): RegisterRepository = DefaultRegisterRepository(api)

}