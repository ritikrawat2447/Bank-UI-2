package com.example.bankingapp

sealed class Screen( val route : String , val title : String ) {

    object Home : Screen("home_screen","Home")
    object Payment : Screen("payment_screen","Payment")
    object Transaction : Screen("transaction_screen","Transaction")

}