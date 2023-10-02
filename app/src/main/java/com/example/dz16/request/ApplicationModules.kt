package com.example.dz16.request

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class ApplicationModules {

    @Provides
    @Singleton
    fun getApiClient(): ApiClient {
        return ApiClient()
    }

    @Provides
    @Singleton
    fun getRepository(apiClient: ApiClient): Repository {
        return Repository(client = apiClient)
    }

}