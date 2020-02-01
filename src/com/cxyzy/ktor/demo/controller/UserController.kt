package com.cxyzy.ktor.demo.controller

import com.cxyzy.ktor.demo.bean.Meme
import com.cxyzy.ktor.demo.dbName
import com.mongodb.BasicDBObject
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.route
import org.koin.ktor.ext.inject
import org.litote.kmongo.coroutine.CoroutineClient
import org.litote.kmongo.lt
import org.slf4j.Logger
import org.slf4j.LoggerFactory


fun Route.userRoutes() {

    val logger: Logger = LoggerFactory.getLogger("UserController")
    val client: CoroutineClient by inject()

    val collectionName = "memes"

    route("/memes") {

        get("/list/{lastId}") {
            val lastId = call.parameters["lastId"]?.toInt()
            val memes = client.getDatabase(dbName)
                .getCollection<Meme>(collectionName)
                .find(Meme::_id lt lastId)
                .sort(BasicDBObject("_id", -1))
                .limit(10).toList()

            call.respond(HttpStatusCode.OK, memes)
        }
    }
}



