package com.goody.dalda.data.converter

import com.goody.dalda.data.dto.home.Data
import com.google.gson.GsonBuilder
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type

class DynamicConverterFactory : Converter.Factory() {

    private val gsonConverter = GsonConverterFactory.create()

    private val alcoholGson = GsonBuilder()
        .registerTypeAdapter(Data::class.java, DataDeserializer())
        .create()
    private val alcoholGsonConverter = GsonConverterFactory.create(alcoholGson)

    override fun responseBodyConverter(
        type: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *>? {
        return if (type == Data::class.java) {
            alcoholGsonConverter.responseBodyConverter(type, annotations, retrofit)
        } else {
            gsonConverter.responseBodyConverter(type, annotations, retrofit)
        }
    }
}
