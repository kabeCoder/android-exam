@file:OptIn(ExperimentalMaterial3Api::class)

package com.kabe.techexam.features.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.kabe.techexam.R
import com.kabe.techexam.domain.RandomPerson
import com.kabe.techexam.features.destinations.DetailScreenDestination
import com.kabe.techexam.features.home.views.PullToRefreshLazyColumn
import com.kabe.techexam.ui.theme.Black
import com.kabe.techexam.ui.theme.TechExamTheme
import com.kabe.techexam.ui.theme.spacing
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@RootNavGraph(start = true)
@Destination
@Composable
fun HomeScreen(navigator: DestinationsNavigator?) {
    val viewModel: HomeScreenViewModel = hiltViewModel()
    val randomPersonList by viewModel.randomPerson.collectAsState(initial = emptyList())
    val randomPersonLoadingState by viewModel.loadingStateRandomPerson.collectAsState(initial = false)

    val scope = rememberCoroutineScope()


    LaunchedEffect(randomPersonList.isEmpty()) {
        viewModel.getRandomPerson()
    }


    if (randomPersonLoadingState) {
        CircularProgressIndicator(
            modifier = Modifier
                .fillMaxSize()
                .padding(MaterialTheme.spacing.extraLarge + MaterialTheme.spacing.extraLarge + MaterialTheme.spacing.extraLarge)
        )
    } else {
        HomeScreenView(
            navigator = navigator,
            randomPersonList = randomPersonList,
            viewModel = viewModel,
            scope = scope,
        )
    }
}

@Composable
fun HomeScreenView(
    navigator: DestinationsNavigator?,
    randomPersonList: List<RandomPerson>,
    viewModel: HomeScreenViewModel,
    scope: CoroutineScope,
) {
    var isRefreshing by remember {
        mutableStateOf(false)
    }

    Box(modifier = Modifier.fillMaxSize()) {

        PullToRefreshLazyColumn(
            items = randomPersonList,
            content = { randomPerson ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(MaterialTheme.spacing.medium)
                        .clickable {
                            navigator?.navigate(DetailScreenDestination(randomPerson))
                        }
                ) {
                    Row(
                        modifier = Modifier.padding(MaterialTheme.spacing.medium),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(randomPerson.picture?.thumbnailProfile)
                                .crossfade(true)
                                .build(),
                            placeholder = null,
                            contentDescription = "",
                            modifier = Modifier
                                .height(80.dp)
                                .width(80.dp)
                                .clip(CircleShape)
                        )

                        Column(
                            modifier = Modifier.padding(start = MaterialTheme.spacing.small)
                        ) {
                            Text(
                                text = "${stringResource(id = R.string.label_name)}: ${randomPerson.name?.title}. ${randomPerson.name?.firstName} ${randomPerson.name?.lastName}",
                                style = MaterialTheme.typography.labelMedium.copy(
                                    fontSize = 14.sp,
                                    color = Black,
                                    fontWeight = FontWeight.W600
                                )
                            )
                            Text(
                                text = "${stringResource(id = R.string.label_gender)}: ${randomPerson.gender}",
                                style = MaterialTheme.typography.labelMedium.copy(
                                    fontSize = 14.sp,
                                    color = Black,
                                    fontWeight = FontWeight.W600
                                )
                            )
                            Text(
                                text = "${stringResource(id = R.string.label_nationality)}: ${randomPerson.nationality}",
                                style = MaterialTheme.typography.labelMedium.copy(
                                    fontSize = 14.sp,
                                    color = Black,
                                    fontWeight = FontWeight.W600
                                )
                            )
                        }
                    }
                }
            },
            isRefreshing = isRefreshing,
            onRefresh = {
                scope.launch {
                    viewModel.refreshRandomPerson()
                }
            }
        )

        LaunchedEffect(viewModel.loadingStateRandomPerson.collectAsState()) {
            isRefreshing = viewModel.loadingStateRandomPerson.value
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


