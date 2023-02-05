package com.salim.nbastatsapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.salim.nbastatsapp.team.Team
import com.salim.nbastatsapp.player.Player
import com.salim.nbastatsapp.player.PlayerDao
import com.salim.nbastatsapp.team.TeamDao

@Database(entities = [Player::class, Team::class], version = 2, exportSchema = true)
abstract class NbaAppDatabase : RoomDatabase() {

    abstract fun playerDao(): PlayerDao
    abstract fun teamDao(): TeamDao

    companion object {
        const val DATABASE_NAME = "nba-app-database"

        val MIGRATION_1_2 = object : Migration(1, 2) {

            override fun migrate(database: SupportSQLiteDatabase) {
                // create new table
                database.execSQL(
                    "CREATE TABLE players_new (\n" +
                            "  id INTEGER PRIMARY KEY NOT NULL,\n" +
                            "  first_name TEXT NOT NULL,\n" +
                            "  height_feet INTEGER,\n" +
                            "  height_inches INTEGER,\n" +
                            "  last_name TEXT NOT NULL,\n" +
                            "  position TEXT NOT NULL,\n" +
                            "  team_name TEXT NOT NULL,\n" +
                            "  weight_pounds INTEGER\n" +
                            ");"
                )

                // copy data from old table into new table
                database.execSQL(
                    "INSERT INTO players_new (id, first_name, height_feet,\n" +
                        " height_inches, last_name, position, team_name, weight_pounds)\n" +
                        " SELECT id, first_name, height_feet, height_inches,\n" +
                        " last_name, position, team_name, weight_pounds FROM players;"
                )

                // delete new table
                database.execSQL("DROP TABLE players")

                // rename new table
                database.execSQL("ALTER TABLE players_new RENAME TO players")
            }
        }
    }
}