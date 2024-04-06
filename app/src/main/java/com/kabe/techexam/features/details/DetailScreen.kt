package com.kabe.techexam.features.details

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.constraintlayout.compose.ConstraintLayout
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun DetailScreen() {
    DetailScreenView()
}

@Composable
fun DetailScreenView() {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {

        val (textDetail) = createRefs()

        Text(
            modifier = Modifier.constrainAs(textDetail) {
                centerHorizontallyTo(parent)
                centerVerticallyTo(parent)
            },
            text = "Detail Screen"
        )
    }
}
