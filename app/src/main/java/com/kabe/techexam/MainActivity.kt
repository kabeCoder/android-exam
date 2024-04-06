package com.kabe.techexam

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.kabe.techexam.features.NavGraphs
import com.kabe.techexam.ui.theme.TechExamTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TechExamTheme {
                DestinationsNavHost(navGraph = NavGraphs.root)
            }
        }
    }
}