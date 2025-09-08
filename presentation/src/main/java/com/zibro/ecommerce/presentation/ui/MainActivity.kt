package com.zibro.ecommerce.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.zibro.ecommerce.presentation.ui.theme.EcommerceAppTheme
import com.zibro.ecommerce.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel : MainViewModel by viewModels()

    @Inject
    lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            EcommerceAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.surface
                ) {
                    MainScreen(googleSignInClient)
                }
            }
        }
        viewModel.updateColumnCount(getColumnCount())
    }

    private fun getColumnCount() : Int {
        return getDisplayWidthDp().toInt() / DEFAULT_COLUMN_SIZE
    }

    // 현재 기기의 디스플레이 너비 dp값
    private fun getDisplayWidthDp() : Float {
        return resources.displayMetrics.run {
            widthPixels / density
        }
    }

    companion object {
        private const val DEFAULT_COLUMN_SIZE = 120
    }
}