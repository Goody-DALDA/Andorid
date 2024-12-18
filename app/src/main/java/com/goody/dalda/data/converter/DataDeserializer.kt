package com.goody.dalda.data.converter


import com.goody.dalda.data.dto.home.Beer
import com.goody.dalda.data.dto.home.Data
import com.goody.dalda.data.dto.home.Sake
import com.goody.dalda.data.dto.home.Soju
import com.goody.dalda.data.dto.home.TraditionalLiquor
import com.goody.dalda.data.dto.home.Whisky
import com.goody.dalda.data.dto.home.Wine
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import java.lang.reflect.Type

class DataDeserializer : JsonDeserializer<Data> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): Data {
        val jsonObject = json.asJsonObject
        return when {
            jsonObject.has("aroma") && jsonObject.has("finish") -> context.deserialize<Whisky>(
                json,
                Whisky::class.java
            )

            jsonObject.has("appearance") && jsonObject.has("mouthfeel") -> context.deserialize<Beer>(
                json,
                Beer::class.java
            )

            jsonObject.has("comment") && !jsonObject.has("pairingFood") -> context.deserialize<Soju>(
                json,
                Soju::class.java
            )

            jsonObject.has("acid") && jsonObject.has("body") -> context.deserialize<Wine>(
                json,
                Wine::class.java
            )

            jsonObject.has("aroma") && jsonObject.has("finish") && !jsonObject.has("type") -> context.deserialize<Sake>(
                json,
                Sake::class.java
            )

            jsonObject.has("brewery") -> context.deserialize<TraditionalLiquor>(
                json,
                TraditionalLiquor::class.java
            )

            else -> throw JsonParseException("Unknown type of Data")
        }
    }
}
