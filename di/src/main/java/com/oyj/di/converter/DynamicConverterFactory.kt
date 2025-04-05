package com.oyj.di.converter

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type
import com.oyj.dataa.dto.home.Data
import com.oyj.dataa.dto.blog.BlogSearchDto

class DynamicConverterFactory : Converter.Factory() {
    override fun responseBodyConverter(
        type: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit,
    ): Converter<ResponseBody, *>? =
        if (type == BlogSearchDto::class.java) {
            val contentType = "application/json".toMediaType()
            val converter = Json.asConverterFactory(contentType)

            converter.responseBodyConverter(type, annotations, retrofit)
        } else {
            val alcoholGson =
                GsonBuilder()
                    .registerTypeAdapter(Data::class.java, DataDeserializer())
                    .create()
            val alcoholGsonConverter = GsonConverterFactory.create(alcoholGson)

            alcoholGsonConverter.responseBodyConverter(type, annotations, retrofit)
        }
}
