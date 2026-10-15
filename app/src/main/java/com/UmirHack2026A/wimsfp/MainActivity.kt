package com.UmirHack2026A.wimsfp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.UmirHack2026A.wimsfp.data.api.ApiClient
import com.UmirHack2026A.wimsfp.ui.theme.WIMSFPTheme
import com.UmirHack2026A.wimsfp.ui.theme.navigation.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ApiClient.init(this)

        setContent {
            WIMSFPTheme() {
                AppNavigation()
            }
        }
    }
}
