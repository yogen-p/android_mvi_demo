package com.yogenp.mvidemo.di

import com.yogenp.mvidemo.repository.MainRepository
import com.yogenp.mvidemo.retrofit.BlogRetrofit
import com.yogenp.mvidemo.retrofit.NetworkMapper
import com.yogenp.mvidemo.room.BlogDao
import com.yogenp.mvidemo.room.CacheMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMainRepository(
        blogDao: BlogDao,
        blogRetrofit: BlogRetrofit,
        cacheMapper: CacheMapper,
        networkMapper: NetworkMapper
    ): MainRepository{
        return MainRepository(blogDao, blogRetrofit, cacheMapper, networkMapper)
    }
}