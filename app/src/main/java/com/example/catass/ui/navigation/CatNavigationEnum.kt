package com.example.catass.ui.navigation

enum class CatNavigationEnum(val param: String = "") {
    HOME,
    DETAIL("id");

    companion object {
        fun fromRoute(route: String?): CatNavigationEnum =
            when (route?.substringBefore("/")) {
                HOME.name -> HOME
                DETAIL.name -> DETAIL
                else -> HOME
            }
    }
}