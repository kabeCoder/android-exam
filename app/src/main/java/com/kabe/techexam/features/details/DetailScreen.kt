package com.kabe.techexam.features.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.constraintlayout.compose.ConstraintLayout
import com.kabe.techexam.domain.RandomPerson
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun DetailScreen(randomPerson: RandomPerson) {
    DetailScreenView(randomPerson = randomPerson)
}

@Composable
fun DetailScreenView(randomPerson: RandomPerson) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {

        val (textDetail) = createRefs()

        Column(
            modifier = Modifier.constrainAs(textDetail) {
                centerHorizontallyTo(parent)
                centerVerticallyTo(parent)
            },
        ) {
            Text(text = "Detail Screen")
        }

    }
}
