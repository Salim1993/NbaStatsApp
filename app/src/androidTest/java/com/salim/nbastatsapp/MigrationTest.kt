package com.salim.nbastatsapp

import androidx.room.testing.MigrationTestHelper
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelperFactory
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.salim.nbastatsapp.database.NbaAppDatabase
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class MigrationTest {

    @get:Rule
    val helper: MigrationTestHelper = MigrationTestHelper(
        InstrumentationRegistry.getInstrumentation(),
        NbaAppDatabase::class.java.canonicalName,
        FrameworkSQLiteOpenHelperFactory()
    )

    @Test
    @Throws(IOException::class)
    fun migrate1To2() {
        helper.createDatabase(TEST_DB, 1).apply {
            // db has schema version 1. insert some data using SQL queries.
            // You cannot use DAO classes because they expect the latest schema.
            execSQL("INSERT INTO players (id, first_name, height_feet, height_inches, last_name," +
                    " position, team_name, weight_pounds) VALUES (1, 'John', 6, 2, 'Doe', 'Point Guard', 'Lakers', 200);"
            )

            // Prepare for the next version.
            close()
        }

        // Re-open the database with version 2 and provide
        // MIGRATION_1_2 as the migration process.
        helper.runMigrationsAndValidate(TEST_DB, 2, true, NbaAppDatabase.MIGRATION_1_2)

        // MigrationTestHelper automatically verifies the schema changes,
        // but you need to validate that the data was migrated properly.
    }

    companion object {
        private const val TEST_DB = "migration-test"
    }
}