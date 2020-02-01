package com.cxyzy.ktor.demo.utils

import com.cxyzy.ktor.demo.bean.Meme
import com.cxyzy.ktor.demo.collectionName
import com.cxyzy.ktor.demo.dbName
import com.mongodb.BasicDBObject
import io.ktor.application.ApplicationCall
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import org.bson.conversions.Bson
import org.json.simple.JSONObject
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.litote.kmongo.coroutine.CoroutineClient
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.eq

class MemeUtil : KoinComponent {

    private val client: CoroutineClient by inject()

    suspend fun handleEmptyData(call: ApplicationCall) {
        val rootObject = JSONObject()
        rootObject["success"] = false
        rootObject["message"] = "No memes exist for the request"
        call.respond(HttpStatusCode.OK, rootObject)
    }

    fun getLastId(call: ApplicationCall): Int {
        return (call.request.queryParameters["lastId"]?.toInt()) ?: 0
    }


    fun getMemeId(call: ApplicationCall): Int {
        return (call.parameters["id"]?.toInt()) ?: 0
    }

    fun sortDescending(): Bson {
        return BasicDBObject("_id", -1)
    }

    fun sortPopular(): Bson {
        return BasicDBObject("downloads", -1).append("upVotes", -1).append("shared", -1)
    }

    suspend fun getMeme(call: ApplicationCall): Meme? {
        return client.getDatabase(dbName)
            .getCollection<Meme>(collectionName)
            .findOne(Meme::_id eq getMemeId(call))
    }

    fun getMemeCollection(): CoroutineCollection<Meme> {
        return client.getDatabase(dbName)
            .getCollection(collectionName)
    }

}