package com.takg.nbay

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.navigation.compose.rememberNavController
import com.takg.nbay.ui.navigation.Navigation
import com.takg.nbay.ui.theme.NBayTheme
import dagger.hilt.android.AndroidEntryPoint
import java.io.File

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NBayTheme {
                Surface(color = MaterialTheme.colors.background) {
                    // Setup Navigation Graph
                    Navigation(
                        outputDirectoryCallback = {
                            getOutputDirectory()
                        }
                    )
                }
            }
        }
    }

    /**
     * Get output directory for images
     */
    private fun getOutputDirectory(): File {
        val mediaDir = externalMediaDirs.firstOrNull()?.let {
            File(it, resources.getString(R.string.app_name)).apply { mkdirs() }
        }

        return if (mediaDir != null && mediaDir.exists()) mediaDir else filesDir
    }
}

