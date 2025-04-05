package com.oyj.domain.repository

import com.oyj.domain.model.PostDomain

interface NoticeRepository {
    suspend fun fetchNotice(): List<PostDomain>
}