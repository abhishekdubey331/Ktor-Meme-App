package com.cxyzy.ktor.demo.controller

import com.cxyzy.ktor.demo.PAGE_SIZE
import com.cxyzy.ktor.demo.bean.Meme
import com.cxyzy.ktor.demo.collectionName
import com.cxyzy.ktor.demo.dbName
import com.cxyzy.ktor.demo.randomCat
import com.cxyzy.ktor.demo.utils.ApiUrls
import com.cxyzy.ktor.demo.utils.MemeUtil
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.route
import org.koin.ktor.ext.inject
import org.litote.kmongo.*
import org.litote.kmongo.coroutine.CoroutineClient
import org.litote.kmongo.coroutine.aggregate
import org.slf4j.Logger
import org.slf4j.LoggerFactory


fun Route.userRoutes() {

    val logger: Logger = LoggerFactory.getLogger("MemeController")
    val client: CoroutineClient by inject()
    val memeUtil: MemeUtil by inject()

    route(ApiUrls.FETCH_LATEST_MEMES) {
        get {
            val memes = client.getDatabase(dbName)
                .getCollection<Meme>(collectionName)
                .find(Meme::_id lt memeUtil.getLastId(call))
                .sort(memeUtil.sortDescending())
                .limit(PAGE_SIZE).toList()

            if (memes.isNullOrEmpty()) {
                memeUtil.handleEmptyData(call)
            } else {
                call.respond(HttpStatusCode.OK, memes)
            }
        }
    }


    route(ApiUrls.FETCH_RANDOM_MEMES) {
        get {
            val memes = client.getDatabase(dbName)
                .getCollection<Meme>(collectionName)
                .aggregate<Meme>(
                    match(
                        Meme::category eq randomCat
                    ),
                    sample(PAGE_SIZE),
                    sort(memeUtil.sortDescending())
                ).toList()

            if (memes.isNullOrEmpty()) {
                memeUtil.handleEmptyData(call)
            } else {
                call.respond(HttpStatusCode.OK, memes)
            }
        }
    }


}



