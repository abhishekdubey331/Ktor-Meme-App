package com.cxyzy.ktor.demo.bean


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