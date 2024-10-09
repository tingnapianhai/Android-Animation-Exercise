package com.animation.exercise.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Air
import androidx.compose.material.icons.filled.EditCalendar
import androidx.compose.material.icons.filled.Nightlife
import androidx.compose.material.icons.filled.SportsGymnastics
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.animation.exercise.R
import com.animation.exercise.studio.FullPreview
import com.animation.exercise.studio.PreviewBox
import com.animation.exercise.studio.component.LargeTopBar
import com.animation.exercise.studio.component.MainBackground

@Composable
fun HomeScreen(
    actions: HomeModels.Actions
) {
    Scaffold(topBar = { LargeTopBar(stringResource(id = R.string.app_name)) }) { pv ->
        MainBackground {
            Column(
                modifier = Modifier.padding(pv)
            ) {
                Spacer(modifier = Modifier.size(80.dp))
                LazyVerticalGrid(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    columns = GridCells.Fixed(2),
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                    horizontalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    item {
                        HomeItemCard(
                            title = "Breathing Exercises",
                            icon = Icons.Default.Air,
                            onClick = actions::goToBreathingExercises
                        )
                    }
                    item {
                        HomeItemCard(
                            title = "Workouts",
                            icon = Icons.Default.SportsGymnastics,
                            onClick = { actions.showToast("Coming soon - Workouts") }
                        )
                    }
                    item {
                        HomeItemCard(
                            title = "Party Time",
                            icon = Icons.Default.Nightlife,
                            onClick = { actions.showToast("Coming soon - Party Time") }
                        )
                    }
                    item {
                        HomeItemCard(
                            title = "Life planner",
                            icon = Icons.Default.EditCalendar,
                            onClick = { actions.showToast("Coming soon - Life planner") }
                        )
                    }
                }
            }
        }
    }
}

@FullPreview
@Composable
private fun PreviewHomeScreen() = PreviewBox(addPadding = false) {
    HomeScreen(actions = HomeModels.Preview.actions)
}
