package com.yogenp.mvidemo.retrofit

import com.yogenp.mvidemo.model.Blog
import com.yogenp.mvidemo.util.EntityMapper
import javax.inject.Inject

class NetworkMapper
@Inject
constructor() : EntityMapper<BlogNetworkEntity, Blog>{
    override fun mapFromEntity(entity: BlogNetworkEntity): Blog {
        return Blog(
            id = entity.id,
            title = entity.title,
            body = entity.body,
            img = entity.img,
            category = entity.category
        )
    }

    override fun mapToEntity(domainModel: Blog): BlogNetworkEntity {
        return BlogNetworkEntity(
            id = domainModel.id,
            title = domainModel.title,
            body = domainModel.body,
            img = domainModel.img,
            category = domainModel.category
        )
    }

    fun mapFromEntityList(entities: List<BlogNetworkEntity>): List<Blog>{
        return entities.map { mapFromEntity(it) }
    }
}