package com.yogenp.mvidemo.room

import com.yogenp.mvidemo.model.Blog
import com.yogenp.mvidemo.retrofit.BlogNetworkEntity
import com.yogenp.mvidemo.util.EntityMapper
import javax.inject.Inject

class CacheMapper
@Inject
constructor(): EntityMapper<BlogCacheEntity, Blog>{
    override fun mapFromEntity(entity: BlogCacheEntity): Blog {
        return Blog(
            id = entity.id,
            title = entity.title,
            body = entity.body,
            img = entity.img,
            category = entity.category
        )
    }

    override fun mapToEntity(domainModel: Blog): BlogCacheEntity {
        return BlogCacheEntity(
            id = domainModel.id,
            title = domainModel.title,
            body = domainModel.body,
            img = domainModel.img,
            category = domainModel.category
        )
    }

    fun mapFromEntityList(entities: List<BlogCacheEntity>): List<Blog>{
        return entities.map { mapFromEntity(it) }
    }
}