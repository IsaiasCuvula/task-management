package com.bersyte.taskFlow.common.navigation


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Schedule
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.bersyte.taskFlow.common.components.VerticalSpace
import com.bersyte.taskFlow.feature.notifications.ui.components.HomeNotificationButton
import com.bersyte.taskFlow.feature.profile.ui.components.DisplayUserInfo
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationDrawer(
    content: @Composable (PaddingValues) -> Unit,
    navController: NavHostController
) {

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var selectedDrawerItem by rememberSaveable {
        mutableIntStateOf(0)
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Column(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                ) {
                    DisplayUserInfo()
                    VerticalSpace(16)

                    DrawerItem.drawerItems.forEachIndexed { index, drawerItem ->
                        val isSelected = selectedDrawerItem == index
                        NavigationDrawerItem(
                            label = { Text(drawerItem.title) },
                            selected = isSelected,
                            icon = { Icon(
                                if(isSelected) drawerItem.selectedIcon else drawerItem.unselectedIcon,
                                contentDescription = drawerItem.title
                            ) },
                            onClick = {
                                navController.navigate(drawerItem.route.name)
                                //
                                selectedDrawerItem = index
                                scope.launch {
                                    drawerState.close()
                                }
                            }
                        )
                    }
                    VerticalSpace(12)
                }
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {},
                    navigationIcon =  {
                        IconButton(onClick = {
                            scope.launch {
                                if (drawerState.isClosed) {
                                    drawerState.open()
                                } else {
                                    drawerState.close()
                                }
                            }
                        }) {
                            Icon(
                                Icons.Default.Menu,
                                contentDescription = "Menu",
                                modifier = Modifier.size(32.dp)
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Transparent
                    ),
                    actions = {
                        IconButton(
                            onClick = {
                                navController.navigate(Route.Schedule.name)
                            }
                        ) {
                            Icon(
                                Icons.Rounded.Schedule,
                                contentDescription = "Today's tasks",
                                modifier = Modifier.size(28.dp)
                            )
                        }

                        HomeNotificationButton(navController)
                    }
                )
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        navController.navigate(Route.AddTask.name)
                    },
                    shape = CircleShape,
                ) {
                    Icon(Icons.Rounded.Add, contentDescription = "Add Task")
                }
            }
        ) { innerPadding ->
            content(innerPadding)
        }
    }
}
