/*
 * Main composable entry point for KMPMobileApp
 * Shared UI code for Android and iOS
 * Uses ViewModel pattern to bridge FlowGraph with Compose UI
 */
package io.codenode.mobileapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
// Feature 085 (universal-runtime collapse): replaced per-module Controller/Adapter
// with `create{Module}Runtime` factory functions. ViewModel + State + FlowGraph + UI
// imports updated to canonical subpackage locations.
import io.codenode.stopwatch.controller.createStopWatchRuntime
import io.codenode.stopwatch.flow.stopWatchFlowGraph
import io.codenode.stopwatch.viewmodel.StopWatchViewModel
import io.codenode.stopwatch.userInterface.StopWatchScreen
import io.codenode.userprofiles.controller.createUserProfilesRuntime
import io.codenode.userprofiles.flow.userProfilesFlowGraph
import io.codenode.userprofiles.persistence.UserProfilesPersistence
import io.codenode.userprofiles.viewmodel.UserProfilesViewModel
import io.codenode.userprofiles.userInterface.UserProfiles

/**
 * Main application composable.
 * This is the entry point for the shared UI.
 */
@Composable
fun App() {
    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            MainContent()
        }
    }
}

/**
 * Main content composable with bottom navigation for StopWatch and UserProfiles tabs.
 */
@Composable
fun MainContent() {
    // StopWatch setup — feature 085: universal-runtime path
    val stopWatchController = remember {
        createStopWatchRuntime(stopWatchFlowGraph).also {
            it.setAttenuationDelay(1000)
        }
    }
    val stopWatchViewModel = remember { StopWatchViewModel(stopWatchController) }

    // UserProfiles setup — feature 085: universal-runtime path
    val userProfilesController = remember {
        createUserProfilesRuntime(userProfilesFlowGraph).also {
            it.start()
        }
    }
    val userProfilesViewModel = remember {
        UserProfilesViewModel(userProfilesController, UserProfilesPersistence.dao)
    }

    var selectedTab by remember { mutableStateOf(0) }
    val tabs = listOf("StopWatch", "UserProfiles")

    Scaffold(
        bottomBar = {
            NavigationBar {
                tabs.forEachIndexed { index, title ->
                    NavigationBarItem(
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        label = { Text(title) },
                        icon = {}
                    )
                }
            }
        }
    ) { paddingValues ->
        when (selectedTab) {
            0 -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                ) {
                    StopWatchScreen(
                        viewModel = stopWatchViewModel,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
            1 -> {
                UserProfiles(
                    viewModel = userProfilesViewModel,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                )
            }
        }
    }
}
