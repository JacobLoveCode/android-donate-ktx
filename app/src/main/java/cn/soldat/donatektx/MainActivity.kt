package cn.soldat.donatektx

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import cn.soldat.donate_ktx.AlipayDonate
import cn.soldat.donatektx.ui.theme.AndroidDonateKTXTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        context = this
        super.onCreate(savedInstanceState)
        setContent {
            AndroidDonateKTXTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Button(onClick = {
                        donateAlipay()
                    }) {
                        Text(text = "Alipay")
                    }
                }
            }
        }
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }
}

private fun donateAlipay() {
    val hasInstalledAlipay = AlipayDonate.hasInstalledAlipayClient(MainActivity.context)
    if (hasInstalledAlipay) {
        AlipayDonate.startAlipayClient(MainActivity.context as Activity, "paycode")
    }
}