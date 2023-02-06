package com.salim.nbastatsapp.navigation

object PlayerDetailsNavigationInfo {
    const val PLAYER_DETAIL_DESTINATION_ARG = "player_name"
    private const val PLAYER_DETAIL_DESTINATION_ROOT = "player_list"
    const val PLAYER_DETAIL_DESTINATION_ROUTE =
        "$PLAYER_DETAIL_DESTINATION_ROOT/{$PLAYER_DETAIL_DESTINATION_ARG}"

    fun buildPlayerDetailRoute(argument: Int) = "$PLAYER_DETAIL_DESTINATION_ROUTE/$argument"
}

object PlayerListNavigationInfo {
    const val PLAYER_LIST_DESTINATION_ROUTE = "player_list"
}