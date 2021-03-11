package com.sun.americanroom.data.source.remote.fetchjson

import com.sun.americanroom.data.model.CityEntry
import com.sun.americanroom.utils.Constant
import com.sun.americanroom.utils.KeyEntity
import com.sun.americanroom.utils.TopRoomEntry
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class ParseDataWithJson {

    fun getJsonFromUrl(urlString: String): String {
        val url = URL(urlString)
        val httpURLConnect = (url.openConnection() as HttpURLConnection).apply {
            requestMethod = METHOD_GET
            connectTimeout = TIME_OUT
            readTimeout = TIME_OUT
            doOutput = true
            connect()
        }
        val bufferedReader = BufferedReader(InputStreamReader(url.openStream()))
        val stringBuilder = StringBuilder()
        var line: String?
        while (bufferedReader.readLine().also { line = it } != null) {
            stringBuilder.append(line)
        }
        httpURLConnect.disconnect()
        return stringBuilder.toString()
    }

    fun parseJsonToData(jsonObject: JSONObject?, keyEntity: String): Any? =
        try {
            val jsonObjectContent =
                jsonObject?.getJSONObject(Constant.JSON_KEY_CONTENT)
            when (keyEntity) {
                KeyEntity.CITY -> {
                    parseJsonToList(
                        jsonObjectContent?.getJSONArray(CityEntry.CITIES),
                        keyEntity
                    )
                }
                KeyEntity.TOP_ROOM -> {
                    parseJsonToList(
                        jsonObjectContent?.getJSONArray(TopRoomEntry.LIST),
                        keyEntity
                    )
                }
                else -> null
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }

    private fun parseJsonToObject(jsonObject: JSONObject?, keyEntity: String): Any? {
        val parseJsonToModel = ParseJson()
        return when (keyEntity) {
            KeyEntity.CITY -> {
                parseJsonToModel.parseJsonToCityFromTop(jsonObject)
            }
            KeyEntity.TOP_ROOM -> {
                parseJsonToModel.parseJsonToTopRoom(jsonObject)
            }
            else -> null
        }
    }

    private fun parseJsonToList(jsonArray: JSONArray?, keyEntity: String): Any {
        val data = mutableListOf<Any?>()
        when (keyEntity) {
            KeyEntity.CITY -> {
                for (i in 0 until (jsonArray?.length() ?: 0)) {
                    val jsonObject = jsonArray?.getJSONObject(i)
                    data.add(parseJsonToObject(jsonObject, keyEntity))
                }
            }
            KeyEntity.TOP_ROOM -> {
                for (i in 0 until (jsonArray?.length() ?: 0)) {
                    val jsonObject = jsonArray?.getJSONObject(i)
                    if (data.size.equals(NUMBER_OF_ROOM)) return data
                    data.add(parseJsonToObject(jsonObject, keyEntity))
                }
            }
        }
        return data
    }

    companion object {
        private const val NUMBER_OF_ROOM = 5
        private const val TIME_OUT = 20000
        private const val METHOD_GET = "GET"
    }
}
