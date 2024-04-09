package com.example.catass.di

import com.example.catass.network.CatsApi
import com.example.catass.repository.CatsRepository
import com.example.catass.repository.CatsRepositoryImp
import com.example.catass.utils.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCatsApi(): CatsApi = Retrofit.Builder()
        .baseUrl(Constant.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(CatsApi::class.java)

    @Provides
    @Singleton
    fun provideCatsRepository(catsApi: CatsApi): CatsRepository = CatsRepositoryImp(catsApi)
}