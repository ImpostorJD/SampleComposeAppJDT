package com.jdt.routinuity.sheets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jdt.routinuity.R
import com.jdt.routinuity.components.CustomHeaderSheet
import com.jdt.routinuity.components.settingsheet.SettingStrip
import com.jdt.routinuity.ui.theme.RoutinuityTheme

@Composable
fun SettingSheet(
    onCollapse: () -> Unit,
    logout: () -> Unit
) {
    val settingsProperties = listOf(
        mapOf(
            "icon" to R.drawable.ic_about,
            "action" to {  },
            "title" to "About"
        ),
        mapOf(
            "icon" to R.drawable.ic_reset,
            "action" to {  },
            "title" to "Reset Stats"
        ),
        mapOf(
            "icon" to R.drawable.ic_logout,
            "action" to { logout() },
            "title" to "Logout"
        ),
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        CustomHeaderSheet(
            onCollapse = onCollapse,
            title = "Settings",
        )
        LazyColumn (
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(20.dp, 0.dp),
        ) {
            items(settingsProperties.size){i ->
                val item = settingsProperties[i]
                SettingStrip(
                    icon = item["icon"] as Int,
                    action = item["action"] as () -> Unit,
                    label = item["title"] as String
                )

            }
        }
    }
}

@Preview
@Composable
fun SettingSheetPreview(){
    RoutinuityTheme {
        SettingSheet(onCollapse = {}, logout = {})
    }
}