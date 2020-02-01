package com.cxyzy.ktor.demo.bean

import org.bson.codecs.pojo.annotations.BsonId
import java.util.*


data class User(
    @BsonId val id: UUID = UUID.randomUUID(),
    val userName: String,
    val password: String,
    val email: String = ""
)


data class Meme(
    val __v: Int,
    val _id: Int,
    val category: String,
    val contributorId: String,
    val created: String,
    val downloads: Int,
    val largeUrl: String,
    val shared: Int,
    val smallUrl: String,
    val upVotes: Int,
    val updated: String
)