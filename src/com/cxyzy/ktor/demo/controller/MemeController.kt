package com.cxyzy.ktor.demo.controller

import com.cxyzy.ktor.demo.PAGE_SIZE
import com.cxyzy.ktor.demo.bean.Meme
import com.cxyzy.ktor.demo.randomCat
import com.cxyzy.ktor.demo.utils.ApiUrls
import com.cxyzy.ktor.demo.utils.MemeUtil
import com.cxyzy.ktor.demo.utils.orElse
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.put
import io.ktor.routing.route
import org.koin.ktor.ext.inject
import org.litote.kmongo.*
import org.litote.kmongo.coroutine.aggregate


fun Route.userRoutes() {

    val memeUtil: MemeUtil by inject()

    route(ApiUrls.FETCH_LATEST_MEMES) {
        get {
            val memes = memeUtil.getMemeCollection()
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
            val memes = memeUtil.getMemeCollection()
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

    route(ApiUrls.FETCH_POPULAR_MEMES) {
        get {
            val memes = memeUtil.getMemeCollection()
                .find()
                .sort(memeUtil.sortPopular())
                .limit(PAGE_SIZE)
                .toList()

            if (memes.isNullOrEmpty()) {
                memeUtil.handleEmptyData(call)
            } else {
                call.respond(HttpStatusCode.OK, memes)
            }
        }
    }

    route(ApiUrls.INC_UP_VOTE) {
        put {
            memeUtil.getMeme(call)?.upVotes?.let {
                val meme = memeUtil.getMemeCollection()
                    .updateOne(Meme::_id eq memeUtil.getMemeId(call), setValue(Meme::upVotes, it + 1))
                call.respond(HttpStatusCode.OK, meme)
            }.orElse {
                memeUtil.handleEmptyData(call)
            }
        }
    }

    route(ApiUrls.INC_DOWNLOAD) {
        put {
            memeUtil.getMeme(call)?.downloads?.let {
                val meme = memeUtil.getMemeCollection()
                    .updateOne(Meme::_id eq memeUtil.getMemeId(call), setValue(Meme::downloads, it + 1))
                call.respond(HttpStatusCode.OK, meme)
            }.orElse {
                memeUtil.handleEmptyData(call)
            }
        }
    }


    route(ApiUrls.INC_SHARE) {
        put {
            memeUtil.getMeme(call)?.shared?.let {
                val meme = memeUtil.getMemeCollection()
                    .updateOne(Meme::_id eq memeUtil.getMemeId(call), setValue(Meme::shared, it + 1))
                call.respond(HttpStatusCode.OK, meme)
            }.orElse {
                memeUtil.handleEmptyData(call)
            }
        }
    }


}



