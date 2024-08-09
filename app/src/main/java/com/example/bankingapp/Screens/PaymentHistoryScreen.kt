package com.example.bankingapp.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bankingapp.R
import com.example.bankingapp.Screens.dummyTransaction.transactionDetailsList
import com.example.bankingapp.Screens.dummyTransaction.transactionTypesList
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentHistoryScreen(navController: NavController) {
    StatusBarColor()
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = colorResource(id = R.color.heading_text_color),
                ),
                title = {
                    Text(
                        text = "Paymnet history",
                        fontWeight = FontWeight.W600,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        fontSize = 20.sp,
                        color = colorResource(id = R.color.heading_text_color)
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Filled.KeyboardArrowLeft,
                            contentDescription = "Localized description",
                            tint = colorResource(id = R.color.heading_text_color),
                            modifier = Modifier.size(30.dp)
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .background(Color.White)
                .fillMaxSize()
        ) {
            transactionType()
            transaction()
        }
    }
}

@Composable
fun transactionType() {
    val selectedIndex = remember { mutableStateOf(2) }
    LazyRow {
        items(transactionTypesList) { transactionType ->
            transactionTypeView(text = transactionType ,
                isSelected = selectedIndex.value == transactionTypesList.indexOf(transactionType),
                onClick = { selectedIndex.value = transactionTypesList.indexOf(transactionType) })
        }
    }
}

@Composable
fun transactionTypeView(text: String , isSelected : Boolean , onClick :() -> Unit ) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .background(
                color = if (isSelected) {
                    colorResource(id = R.color.color_primary)
                }else {
                    colorResource(id = R.color.tab_unselected_color)
                },
                shape = RoundedCornerShape(16.dp)
            )
            .size(100.dp, 44.dp)
            .clickable {
                onClick
            }
    ) {
        Text(
            text = text,
            fontWeight = FontWeight.W500,
            fontFamily = FontFamily(Font(R.font.poppins_regular)),
            fontSize = 16.sp,
            color = if ( isSelected ) Color.White else colorResource(id = R.color.heading_text_color),
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.Center)
        )
    }
}


@Composable
fun transaction() {
    LazyColumn {
        items(transactionDetailsList) { transaction ->
            transactionView(transactionDetails = transaction)
        }
    }
}

@Composable
fun transactionView(transactionDetails: TransactionDetails) {
    Card(
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = transactionDetails.month,
                    fontWeight = FontWeight.W600,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    fontSize = 16.sp,
                    color = colorResource(id = R.color.heading_text_color)
                )
                Text(
                    text = transactionDetails.date,
                    fontWeight = FontWeight.W600,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    fontSize = 12.sp,
                    color = colorResource(id = R.color.heading_text_color)
                )
            }
            Row {
                Column {
                    Text(
                        text = "Status",
                        fontWeight = FontWeight.W500,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        fontSize = 12.sp,
                        color = colorResource(id = R.color.card_text_color)
                    )
                    Text(
                        text = "Company",
                        fontWeight = FontWeight.W500,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        fontSize = 12.sp,
                        color = colorResource(id = R.color.card_text_color)
                    )
                }
                Spacer(modifier = Modifier.width(6.dp))
                Column {
                    Text(
                        text = transactionDetails.status,
                        fontWeight = FontWeight.W500,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        fontSize = 12.sp,
                        color = if (transactionDetails.status == "Unsuccesfully")
                            colorResource(id = R.color.color_red)
                        else colorResource(
                            id = R.color.color_blue
                        )
                    )
                    Text(
                        text = transactionDetails.compnay,
                        fontWeight = FontWeight.W500,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        fontSize = 12.sp,
                        color = colorResource(id = R.color.card_text_color)
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        text = "Amount",
                        fontWeight = FontWeight.W500,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        fontSize = 12.sp,
                        color = colorResource(id = R.color.card_text_color)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = transactionDetails.amount,
                        fontWeight = FontWeight.W500,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        fontSize = 12.sp,
                        color = colorResource(id = R.color.color_blue)
                    )
                }
            }

        }
    }
}

data class TransactionDetails(
    val month: String,
    val status: String,
    val compnay: String,
    val amount: String,
    val date: String
)

object dummyTransaction {
    val transactionDetailsList = listOf(
        TransactionDetails("October", "Unsuccesfully", "Capi Telecom", "$50", "30/10/2019"),
        TransactionDetails("September", "Succesfully", "Capi Telecom", "$50", "30/10/2019"),
        TransactionDetails("August", "Succesfully", "Capi Telecom", "$50", "30/10/2019"),
        TransactionDetails("July", "Succesfully", "Capi Telecom", "$50", "30/10/2019"),
        TransactionDetails("June", "Succesfully", "Capi Telecom", "$50", "30/10/2019"),
        TransactionDetails("October", "Unsuccesfully", "Capi Telecom", "$50", "30/10/2019"),
        TransactionDetails("September", "Succesfully", "Capi Telecom", "$50", "30/10/2019"),
        TransactionDetails("August", "Succesfully", "Capi Telecom", "$50", "30/10/2019"),
        TransactionDetails("July", "Succesfully", "Capi Telecom", "$50", "30/10/2019"),
        TransactionDetails("June", "Succesfully", "Capi Telecom", "$50", "30/10/2019")
    )
    val transactionTypesList = listOf(
        "Water",
        "Mobile",
        "Internet",
        "Electricity",
        "Rent",
        "Gas",
        "Cable",
        "Insurance",
        "Mortgage"
    )
}

@Composable
private fun StatusBarColor() {
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = true
    val statusBarColor = colorResource(id = R.color.white)

    SideEffect {
        systemUiController.setStatusBarColor(
            color = statusBarColor,
            darkIcons = useDarkIcons
        )
    }
}
