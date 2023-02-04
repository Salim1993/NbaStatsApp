package com.salim.nbastatsapp.team


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Entity(tableName = "teams")
data class Team(
    @PrimaryKey
    @ColumnInfo(name = "id")
    @Json(name = "id")
    val id: Int,

    @ColumnInfo(name = "abbreviation")
    @Json(name = "abbreviation")
    val abbreviation: String,

    @ColumnInfo(name = "city")
    @Json(name = "city")
    val city: String,

    @ColumnInfo(name = "conference")
    @Json(name = "conference")
    val conference: String,

    @ColumnInfo(name = "division")
    @Json(name = "division")
    val division: String,

    @ColumnInfo(name = "full_name")
    @Json(name = "full_name")
    val fullName: String,

    @ColumnInfo(name = "name")
    @Json(name = "name")
    val name: String
)
