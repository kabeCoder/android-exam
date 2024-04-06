package com.kabe.techexam.features.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.kabe.techexam.domain.RandomPerson
import com.kabe.techexam.features.destinations.DetailScreenDestination
import com.kabe.techexam.ui.theme.TechExamTheme
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@RootNavGraph(start = true)
@Destination
@Composable
fun HomeScreen(navigator: DestinationsNavigator?) {
    val viewModel: HomeScreenViewModel = hiltViewModel()
    val randomPersonList by viewModel.randomPerson.collectAsState(initial = emptyList())
    val randomPersonLoadingState by viewModel.loadingStateRandomPerson.collectAsState(initial = "")

    LaunchedEffect(Unit) {
        viewModel.getRandomPerson()
    }

    HomeScreenView(
        navigator = navigator,
        randomPersonList = randomPersonList,
    )
}

@Composable
fun HomeScreenView(
    navigator: DestinationsNavigator?,
    randomPersonList: List<RandomPerson>,
) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (btnDetails) = createRefs()

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            randomPersonList.forEach { randomPerson ->
                Text(text = "Gender: ${randomPerson.gender}")
            }
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
