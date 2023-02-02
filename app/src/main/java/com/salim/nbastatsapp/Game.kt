package com.salim.nbastatsapp


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Game(
    @Json(name = "id")
    val id: Int,
    @Json(name = "date")
    val date: String,
    @Json(name = "home_team_score")
    val homeTeamScore: Int,
    @Json(name = "visitor_team_score")
    val visitorTeamScore: Int,
    @Json(name = "period")
    val period: Int,
    @Json(name = "postseason")
    val postseason: Boolean,
    @Json(name = "season")
    val season: Int,
    @Json(name = "status")
    val status: String,
    @Json(name = "time")
    val time: String,
    @Json(name = "home_team")
    val homeTeam: Team,
    @Json(name = "visitor_team")
    val visitorTeam: Team
)