package com.salim.nbastatsapp.player

import android.util.JsonToken
import com.salim.nbastatsapp.team.Team
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.Moshi
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson

class PlayerTypeAdapter(moshi: Moshi): JsonAdapter<Player>() {

    private val options = JsonReader.Options.of(
        "id", "first_name", "height_feet", "height_inches", "last_name", "position", "team", "weight_pounds"
    )
    private val teamTypeAdapter: JsonAdapter<Team> = moshi.adapter(Team::class.java)

    @FromJson
    override fun fromJson(reader: JsonReader): Player {
        reader.beginObject()
        var id: Int = 0
        var firstName: String = ""
        var heightFeet: Int? = null
        var heightInches: Int? = null
        var lastName: String = ""
        var position: String = ""
        var team: String = ""
        var weightPounds: Int? = null
        while (reader.hasNext()) {
            when (reader.selectName(options)) {
                ID_NUMBER -> id = reader.nextInt()
                FIRST_NAME_NUMBER -> firstName = reader.nextString()
                HEIGHT_FEET_NUMBER -> heightFeet = checkNullableInt(reader)
                HEIGHT_INCHES_NUMBER -> heightInches = checkNullableInt(reader)
                LAST_NAME_NUMBER -> lastName = reader.nextString()
                POSITION_NUMBER -> position = reader.nextString()
                TEAM_NUMBER -> team = teamTypeAdapter.fromJson(reader)?.name ?: ""
                WEIGHT_POUNDS_NUMBER -> weightPounds = checkNullableInt(reader)
                ERROR_JSON_NUMBER -> {
                    reader.skipValue()
                }
            }
        }
        reader.endObject()
        return Player(id, firstName, heightFeet, heightInches, lastName, position, team, weightPounds)
    }

    private fun checkNullableInt(reader: JsonReader): Int? {
        return if (reader.peek() == JsonReader.Token.NULL){
            null
        } else {
            reader.nextInt()
        }
    }

    @ToJson
    override fun toJson(writer: JsonWriter, value: Player?) {
        if (value == null) {
            writer.nullValue()
            return
        }
        writer.beginObject()
        writer.name("id")
        writer.value(value.id)
        writer.name("first_name")
        writer.value(value.firstName)
        writer.name("height_feet")
        writer.value(value.heightFeet)
        writer.name("height_inches")
        writer.value(value.heightInches)
        writer.name("last_name")
        writer.value(value.lastName)
        writer.name("position")
        writer.value(value.position)
        writer.name("team")
        writer.value(value.teamName)
        writer.name("weight_pounds")
        writer.value(value.weightPounds)
        writer.endObject()
    }

    companion object {
        private const val ID_NUMBER = 0
        private const val FIRST_NAME_NUMBER = 1
        private const val HEIGHT_FEET_NUMBER = 2
        private const val HEIGHT_INCHES_NUMBER = 3
        private const val LAST_NAME_NUMBER = 4
        private const val POSITION_NUMBER = 5
        private const val TEAM_NUMBER = 6
        private const val WEIGHT_POUNDS_NUMBER = 7
        private const val ERROR_JSON_NUMBER = -1
    }
}