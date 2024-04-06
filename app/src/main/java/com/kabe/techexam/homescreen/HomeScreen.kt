package com.kabe.techexam.homescreen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import com.kabe.techexam.destinations.DetailScreenDestination
import com.kabe.techexam.ui.theme.TechExamTheme
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@RootNavGraph(start = true)
@Destination
@Composable
fun HomeScreen(navigator: DestinationsNavigator?) {
    HomeScreenView(navigator = navigator)
}

@Composable
fun HomeScreenView(
    navigator: DestinationsNavigator?
) {

    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (btnDetails) = createRefs()


        Button(
            modifier = Modifier.constrainAs(btnDetails) {
                centerVerticallyTo(parent)
                centerHorizontallyTo(parent)
            },
            onClick = {
                navigator?.navigate(DetailScreenDestination)
            })
        {

            Text(text = "Go to Detail Screen")
        }

    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    TechExamTheme {
        HomeScreen(navigator = null)
    }
}