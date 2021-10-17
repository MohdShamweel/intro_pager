package com.shamweel.trimaplus.di

import android.content.Context
import com.shamweel.trimaplus.data.network.ApiInterface
import com.shamweel.trimaplus.data.network.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideApi(
        @ApplicationContext context: Context,
        remoteDataSource: RemoteDataSource
    ) : ApiInterface {
        return remoteDataSource.buildApi(ApiInterface::class.java, context)
    }
}