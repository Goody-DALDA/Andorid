package com.goody.dalda.data.model

import com.oyj.domain.model.BlogEntity

data class BlogUIModel(
    val link: String,
    val title: String,
    val description: String,
    val bloggerName: String,
    val bloggerLink: String,
    val postdate: String,
)

fun BlogEntity.toAppModel(): BlogUIModel {
    return BlogUIModel(
        link = this.link,
        title = this.title,
        description = this.description,
        bloggerName = this.bloggerName,
        bloggerLink = this.bloggerLink,
        postdate = this.postdate
    )
}

fun List<BlogEntity>.toAppModelList(): List<BlogUIModel> {
    return this.map { it.toAppModel() }
}
