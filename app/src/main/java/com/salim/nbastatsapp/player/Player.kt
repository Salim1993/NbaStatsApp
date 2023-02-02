package com.salim.nbastatsapp.player


import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.salim.nbastatsapp.Team
import com.squareup.moshi.Json

/**
 * A data class that represents a player.
 */
@Entity(tableName = "players")
data class Player(
    /**
     * The ID of the player.
     */
    @PrimaryKey
    @ColumnInfo(name = "id")
    @Json(name = "id")
    val id: Int,

    /**
     * The first name of the player.
     */
    @ColumnInfo(name = "first_name")
    @Json(name = "first_name")
    val firstName: String,

    /**
     * The height of the player in feet.
     */
    @ColumnInfo(name = "height_feet")
    @Json(name = "height_feet")
    val heightFeet: Int,

    /**
     * The height of the player in inches.
     */
    @ColumnInfo(name = "height_inches")
    @Json(name = "height_inches")
    val heightInches: Int,

    /**
     * The last name of the player.
     */
    @ColumnInfo(name = "last_name")
    @Json(name = "last_name")
    val lastName: String,

    /**
     * The position of the player.
     */
    @ColumnInfo(name = "position")
    @Json(name = "position")
    val position: String,

    /**
     * The team of the player.
     */
    @Embedded(prefix = "team")
    @Json(name = "team")
    val team: Team,

    /**
     * The weight of the player in pounds.
     */
    @ColumnInfo(name = "weight_pounds")
    @Json(name = "weight_pounds")
    val weightPounds: Int
)