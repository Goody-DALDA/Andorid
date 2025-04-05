package com.oyj.domain.model

import java.util.Date

data class OAuthTokenDomain(
    val accessToken: String,
    val accessTokenExpiresAt: Date,
    val refreshToken: String,
    val refreshTokenExpiresAt: Date,
    val idToken: String? = null,
    val scopes: List<String>? = null
)
