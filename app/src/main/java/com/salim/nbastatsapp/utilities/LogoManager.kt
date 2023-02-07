package com.salim.nbastatsapp.utilities

import com.salim.nbastatsapp.R

object LogoManager {

    /**
     * Returns the proper logo resource ID for a given NBA team name.
     *
     * @param teamName The name of the NBA team.
     * @return The resource ID of the logo for the team. Returns {@link R.drawable.ic_launcher_background}
     * if the team name is not recognized.
     *
     * Suppress detekt error since all we doing is going through all NBA team names. Don't think
     * length of when statement makes this function unnecessarily complex
     */
    @Suppress("CyclomaticComplexMethod")
    fun getProperLogo(teamName: String): Int {
        return when (teamName) {
            "Atlanta Hawks" -> R.drawable.atlanta_hawks_logo
            "Boston Celtics" -> R.drawable.boston_celtics_logo
            "Brooklyn Nets" -> R.drawable.brooklyn_nets_logo
            "Charlotte Hornets" -> R.drawable.charlotte_hornets_logo
            "Chicago Bulls" -> R.drawable.chicago_bulls_logo
            "Cleveland Cavaliers" -> R.drawable.cleveland_cavaliers_logo
            "Dallas Mavericks" -> R.drawable.dallas_mavericks_logo
            "Denver Nuggets" -> R.drawable.denver_nuggets_logo
            "Detroit Pistons" -> R.drawable.detroit_pistons_logo
            "Golden State Warriors" -> R.drawable.golden_state_warriors_logo
            "Houston Rockets" -> R.drawable.houston_rockets_logo
            "Indiana Pacers" -> R.drawable.indiana_pacers_logo
            "LA Clippers" -> R.drawable.la_clippers_logo
            "Los Angeles Lakers" -> R.drawable.los_angeles_lakers_logo
            "Memphis Grizzlies" -> R.drawable.memphis_grizzlies_logo
            "Miami Heat" -> R.drawable.miami_heat_logo
            "Milwaukee Bucks" -> R.drawable.milwaukee_bucks_logo
            "Minnesota Timberwolves" -> R.drawable.minnesota_timberwolves_logo
            "New Orleans Pelicans" -> R.drawable.new_orleans_pelicans_logo
            "New York Knicks" -> R.drawable.new_york_knicks_logo
            "Oklahoma City Thunder" -> R.drawable.oklahoma_city_thunder_logo
            "Orlando Magic" -> R.drawable.orlando_magic_logo
            "Philadelphia 76ers" -> R.drawable.philadelphia_76ers_logo
            "Phoenix Suns" -> R.drawable.phoenix_suns_logo
            "Portland Trail Blazers" -> R.drawable.portland_trail_blazers_logo
            "Sacramento Kings" -> R.drawable.sacramento_kings_logo
            "San Antonio Spurs" -> R.drawable.san_antonio_spurs_logo
            "Toronto Raptors" -> R.drawable.toronto_raptors_logo
            "Utah Jazz" -> R.drawable.utah_jazz_logo
            "Washington Wizards" -> R.drawable.washington_wizards_logo
            else -> R.drawable.team_not_available
        }
    }
}