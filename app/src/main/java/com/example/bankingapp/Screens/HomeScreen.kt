package com.example.bankingapp.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bankingapp.R
import com.example.bankingapp.Screen
import com.example.bankingapp.Screens.dummy.allFeaturesList
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun HomeScreen(navController: NavController) {
    StatusBarColor()
    Scaffold(
        topBar = { topAppBar()},
        bottomBar = {
            BottomAppBar (
                actions = {
                    Button(onClick = { /* do something */ }) {
                        Icon(
                            Icons.Filled.Home,
                            contentDescription = "Localized description",
                        )
                        Text(text = "Home")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    IconButton(onClick = { /* do something */ }) {
                        Icon(Icons.Filled.Search, contentDescription = "Localized description")
                    }
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            Icons.Filled.Email,
                            contentDescription = "Localized description",
                        )
                    }
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            Icons.Default.Settings,
                            contentDescription = "Localized description",
                        )
                    }
                },
                containerColor = Color.White
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(colorResource(id = R.color.color_primary))

        ) {
            Column(
                modifier = Modifier
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(30.dp, 30.dp)
                    )
                    .align(Alignment.BottomCenter)
            ) {
                appFeatures(navController)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun topAppBar(){
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    TopAppBar(
        modifier = Modifier.padding(top = 2.dp),
        title = {
            Spacer(modifier = Modifier.padding(top = 24.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            ) {
                var itemCount by remember { mutableStateOf(3) }

                Image(
                    bitmap = ImageBitmap.imageResource(id = R.drawable.avatar),
                    contentDescription = " Profile Image",
                    modifier = Modifier
                        .weight(1f)
                        .size(50.dp)
                )
                Spacer(modifier = Modifier.padding(4.dp))
                Text(
                    text = "Hi, Push Puttichai",
                    fontWeight = FontWeight.W500,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    fontSize = 16.sp,
                    color = Color.White,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .weight(3f)
                )
                IconButton(onClick = { /*TODO*/ }, modifier = Modifier.weight(1f)) {
                    BadgedBox(
                        badge = {
                            Badge(
                                containerColor = Color.Red,
                                contentColor = Color.White
                            ) {
                                Text("$itemCount")
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Notifications,
                            contentDescription = "Notification Icon",
                            modifier = Modifier.size(26.dp, 28.dp)
                        )
                    }
                }
            }
        },
        colors = topAppBarColors(
            containerColor = colorResource(id = R.color.color_primary),
        ),
        scrollBehavior = scrollBehavior
    )

}

@Composable
fun appFeatures(navController: NavController) {
    Column(
        modifier = Modifier
            .padding(horizontal = 4.dp)
            .fillMaxWidth()
    ) {
        Image(painter = painterResource(id = R.drawable.multi), contentDescription = "All Cards" )
        LazyVerticalGrid(GridCells.Fixed(3), modifier = Modifier.fillMaxWidth()) {
            items(allFeaturesList) { features ->
                FeaturesView(icon = features.featureIcon, title = features.featureTitle , navController = navController)
            }
        }
    }
}

@Composable
fun FeaturesView(icon: Int, title: String , navController: NavController) {
    Card(
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        modifier = Modifier
            .padding(8.dp)
            .size(90.dp)
            .clickable {
                if (title == "Transaction\nReport") {
                    navController.navigate(Screen.Payment.route)
                }
                if (title == "Transfer") {
                    navController.navigate(Screen.Transaction.route)
                }
            },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = "Service Image",
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = title,
                fontSize = 12.sp,
                fontWeight = FontWeight.W500,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                color = colorResource(id = R.color.card_text_color),
                style = TextStyle(
                    lineHeight = 16.sp
                ),
                textAlign = TextAlign.Center
            )
        }
    }
}

data class Feature(
    val featureIcon: Int,
    val featureTitle: String
)

object dummy {
    val allFeaturesList = listOf(
        Feature(R.drawable.account, "Account\nand Cards"),
        Feature(R.drawable.transfer, "Transfer"),
        Feature(R.drawable.withdraw, "Withdraw"),
        Feature(R.drawable.prepaid, "Mobile\nPrepaid"),
        Feature(R.drawable.pay, "Pay\nthe bill"),
        Feature(R.drawable.save, "Save\nOnline"),
        Feature(R.drawable.credit, "Credit\nCard"),
        Feature(R.drawable.transaction, "Transaction\nReport"),
        Feature(R.drawable.beneficiary, "Beneficiary")
    )
}

@Composable
private fun StatusBarColor() {
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = false
    val statusBarColor = colorResource(id = R.color.color_primary)

    SideEffect {
        systemUiController.setStatusBarColor(
            color = statusBarColor,
            darkIcons = useDarkIcons
        )
    }
}