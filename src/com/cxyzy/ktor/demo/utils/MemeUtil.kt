package com.cxyzy.ktor.demo.utils

import com.mongodb.BasicDBObject
import io.ktor.application.ApplicationCall
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import org.bson.conversions.Bson
import org.json.simple.JSONObject

class MemeUtil {

    suspend fun handleEmptyData(call: ApplicationCall) {
        val rootObject = JSONObject()
        rootObject["success"] = false
        rootObject["message"] = "No memes exist for the request"
        call.respond(HttpStatusCode.OK, rootObject)
    }

    fun getLastId(call: ApplicationCall): Int {
        return (call.parameters["lastId"]?.toInt()) ?: 0
    }

    fun sortDescending(): Bson {
        return BasicDBObject("_id", -1)
    }

}