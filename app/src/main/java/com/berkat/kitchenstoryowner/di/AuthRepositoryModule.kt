package com.berkat.kitchenstoryowner.di

import com.berkat.kitchenstoryowner.network.AuthApi
import com.berkat.kitchenstoryowner.network.KitchenApi
import com.berkat.kitchenstoryowner.repository.datausaha.DataUsahaRepository
import com.berkat.kitchenstoryowner.repository.datausaha.DefaultDataUsahaRepository
import com.berkat.kitchenstoryowner.repository.identitaspemilik.DefaultIndentitasPemilikRepository
import com.berkat.kitchenstoryowner.repository.identitaspemilik.IdentitasPemilikRepository
import com.berkat.kitchenstoryowner.repository.informasirekening.DefaultInformasiRekeningRepository
import com.berkat.kitchenstoryowner.repository.informasirekening.InformasiRekeningRepository
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

    @ViewModelScoped
    @Provides
    fun provideDataUsahaRepository(api: KitchenApi): DataUsahaRepository =
        DefaultDataUsahaRepository(api)

    @ViewModelScoped
    @Provides
    fun provideInformasiRekeningRepository(api: KitchenApi): InformasiRekeningRepository =
        DefaultInformasiRekeningRepository(api)

    @ViewModelScoped
    @Provides
    fun provideIdentitasPemilik(api: KitchenApi): IdentitasPemilikRepository =
        DefaultIndentitasPemilikRepository(api)

}