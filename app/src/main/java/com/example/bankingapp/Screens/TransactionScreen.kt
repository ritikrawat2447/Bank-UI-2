package com.example.bankingapp.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bankingapp.R
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionScreen(navController: NavController) {
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
                        text = "Confirm",
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
                .fillMaxSize()
                .background(Color.White)
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState()).weight(1f)
            ) {
                Text(
                    text = "Confirm transaction information",
                    fontWeight = FontWeight.W600,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    fontSize = 12.sp,
                    color = colorResource(id = R.color.confirm_text_color),
                    modifier = Modifier.padding(start = 4.dp)
                )
                Spacer(modifier = Modifier.height(2.dp))
                confirmView("from", "**** **** 6789")
                Spacer(modifier = Modifier.height(4.dp))
                confirmView("To", "Amanda")
                Spacer(modifier = Modifier.height(4.dp))
                confirmView("Card Number", "0123456789")
                Spacer(modifier = Modifier.height(4.dp))
                confirmView("Transaction Fee", "$10")
                Spacer(modifier = Modifier.height(4.dp))
                confirmView("Content", "From Jimmy")
                Spacer(modifier = Modifier.height(4.dp))
                confirmView("Amount", "$1000")
                Spacer(modifier = Modifier.height(4.dp))
                confirmView("Get OTP to verify transaction", "6789")
            }
            Button(
                onClick = { /*TODO*/ }, colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.color_primary),
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(
                    text = "Confirm",
                    fontWeight = FontWeight.W500,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.align(Alignment.Bottom)
                )
            }
        }
    }
}

@Composable
fun confirmView(label: String, value: String, onValueChange: (String) -> Unit = {}) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 4.dp)
    ) {
        Text(
            text = label,
            fontWeight = FontWeight.W600,
            fontFamily = FontFamily(Font(R.font.poppins_regular)),
            fontSize = 12.sp,
            color = colorResource(id = R.color.card_text_color)
        )
        if (label == "Get OTP to verify transaction") {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = value,
                    onValueChange = onValueChange,
                    enabled = true,
                    shape = RoundedCornerShape(20.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedTextColor = colorResource(id = R.color.card_text_color),
                        unfocusedBorderColor = colorResource(id = R.color.card_text_color),
                        unfocusedLabelColor = Color.White,
                        unfocusedLeadingIconColor = Color.White
                    ),
                    modifier = Modifier.weight(1f),
                )
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .wrapContentWidth()
                        .height(54.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.color_primary),
                        contentColor = Color.White
                    )
                ) {
                    Text(
                        text = "Get OTP",
                        fontWeight = FontWeight.W600,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        fontSize = 16.sp
                    )
                }
            }
        } else {
            OutlinedTextField(
                value = value,
                onValueChange = onValueChange,
                enabled = true,
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedTextColor = colorResource(id = R.color.card_text_color),
                    unfocusedBorderColor = colorResource(id = R.color.card_text_color),
                    unfocusedLabelColor = Color.White,
                    unfocusedLeadingIconColor = Color.White
                )
            )
        }
    }
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
