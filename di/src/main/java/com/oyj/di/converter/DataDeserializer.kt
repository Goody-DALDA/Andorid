package com.oyj.di.converter

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import com.oyj.dataa.dto.home.Beer
import com.oyj.dataa.dto.home.Data
import com.oyj.dataa.dto.home.Sake
import com.oyj.dataa.dto.home.Soju
import com.oyj.dataa.dto.home.TraditionalLiquor
import com.oyj.dataa.dto.home.Whisky
import com.oyj.dataa.dto.home.Wine
import java.lang.reflect.Type

class DataDeserializer : JsonDeserializer<Data> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext,
    ): Data {
        val jsonObject = json.asJsonObject
        return when {
            jsonObject.has("aroma") && jsonObject.has("finish") && !jsonObject.has("type") ->
                context.deserialize<Sake>(
                    json,
                    Sake::class.java,
                )

            jsonObject.has("aroma") && jsonObject.has("finish") ->
                context.deserialize<Whisky>(
                    json,
                    Whisky::class.java,
                )

            jsonObject.has("appearance") && jsonObject.has("mouthfeel") ->
                context.deserialize<Beer>(
                    json,
                    Beer::class.java,
                )

            jsonObject.has("comment") && !jsonObject.has("pairingFood") ->
                context.deserialize<Soju>(
                    json,
                    Soju::class.java,
                )

            jsonObject.has("acid") && jsonObject.has("body") ->
                context.deserialize<Wine>(
                    json,
                    Wine::class.java,
                )

            jsonObject.has("brewery") ->
                context.deserialize<TraditionalLiquor>(
                    json,
                    TraditionalLiquor::class.java,
                )

            else -> throw JsonParseException("Unknown type of Data")
        }
    }
}
