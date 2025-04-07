package com.goody.dalda.data

import com.oyj.domain.model.BlogEntity

data class BlogData(
    val link: String,
    val title: String,
    val description: String,
    val bloggerName: String,
    val bloggerLink: String,
    val postdate: String,
)

fun BlogEntity.toAppModel(): BlogData {
    return BlogData(
        link = this.link,
        title = this.title,
        description = this.description,
        bloggerName = this.bloggerName,
        bloggerLink = this.bloggerLink,
        postdate = this.postdate
    )
}

fun List<BlogEntity>.toAppModelList(): List<BlogData> {
    return this.map { it.toAppModel() }
}
