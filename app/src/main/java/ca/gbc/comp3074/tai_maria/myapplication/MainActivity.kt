package ca.gbc.comp3074.tai_maria.myapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import ca.gbc.comp3074.tai_maria.myapplication.ui.theme.MyApplicationTheme
import kotlin.math.round

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                MainFunc(LocalContext.current)
            }
        }
    }
}

@Composable
fun MainFunc(context: Context) {
    var numHours by rememberSaveable { mutableStateOf("") }
    var hourRate by rememberSaveable { mutableStateOf("") }
    var taxRate by rememberSaveable { mutableStateOf("") }
    var pay: Double
    var overtimePay: Double
    var totalPay: Double
    var tax: Double
    Column (
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Button(
            onClick = {
                context.startActivity(Intent(context, AboutActivity::class.java))
            }
        ){
            Text("About")
        }
        TextField(
            modifier = Modifier.padding(14.dp),
            value = numHours,
            onValueChange = { numHours = it },
            label = { Text("Number of Hours") }
        )

        TextField(
            modifier = Modifier.padding(14.dp),
            value = hourRate,
            onValueChange = { hourRate = it },
            label = { Text("Hourly Rate") }
        )

        TextField(
            modifier = Modifier.padding(14.dp),
            value = taxRate,
            onValueChange = { taxRate = it },
            label = { Text("Tax Rate") }
        )

        val hours = numHours.toDoubleOrNull() ?: 0.0
        val hRate = hourRate.toDoubleOrNull() ?: 0.0
        val tRate = taxRate.toDoubleOrNull() ?: 0.0


        if (hours <= 40) {
            pay = hours * hRate
            overtimePay = 0.0
            
        } else {
            pay = 40 * hRate
            overtimePay = (hours - 40) * hRate * 1.5
        }

        totalPay = pay + overtimePay
        tax = round(pay * (tRate/100))

        DisplayPayData(pay, overtimePay, totalPay, tax)

    }
}

@Composable
fun DisplayPayData(pay: Double, overtimePay: Double, totalPay: Double, tax: Double){
    Column {
        Text("Pay: $pay")
        Text("Overtime Pay: $overtimePay")
        Text("Total Pay: $totalPay")
        Text("Tax: $tax")
    }
}










