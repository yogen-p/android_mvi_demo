package com.yogenp.mvidemo.repository

import com.yogenp.mvidemo.model.Blog
import com.yogenp.mvidemo.retrofit.BlogRetrofit
import com.yogenp.mvidemo.retrofit.NetworkMapper
import com.yogenp.mvidemo.room.BlogDao
import com.yogenp.mvidemo.room.CacheMapper
import com.yogenp.mvidemo.util.DataState
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.Flow
import java.lang.Exception

class MainRepository
constructor(
    private val blogDao: BlogDao,
    private val blogRetrofit: BlogRetrofit,
    private val cacheMapper: CacheMapper,
    private val networkMapper: NetworkMapper
){

    suspend fun getBlog(): Flow<DataState<List<Blog>>> = flow{
        emit(DataState.Loading)
        kotlinx.coroutines.delay(1000)
        try {
            val networkBlog = blogRetrofit.get()
            val blogs = networkMapper.mapFromEntityList(networkBlog)
            for (blog in blogs){
                blogDao.insert(cacheMapper.mapToEntity(blog))
            }
            val cachedBlog = blogDao.get()
            emit(DataState.Success(cacheMapper.mapFromEntityList(cachedBlog)))
        }catch (e: Exception){
            emit(DataState.Error(e))
        }
    }
}
