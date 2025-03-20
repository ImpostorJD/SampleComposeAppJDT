package com.jdt.routinuity.sheets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jdt.routinuity.R
import com.jdt.routinuity.components.CustomHeaderSheet
import com.jdt.routinuity.components.ProfileView
import com.jdt.routinuity.components.profile.ProfileInformationStrip
import com.jdt.routinuity.components.profile.ProfileStat
import com.jdt.routinuity.utils.formatter
import com.jdt.routinuity.ui.theme.RoutinuityTheme
import kotlinx.coroutines.delay
import java.time.LocalDate

@Composable
fun Profile(
    onCollapse: () -> Unit,
){
    val data = listOf(
        listOf(0f, 0f, 50f, 100f, 10f)
    )
    val labels = listOf(
        "Habit 1" to Color.Red,
        "Habit 2" to Color.Green,
        "Habit 3" to Color.Blue,
        "Habit 4" to Color.Yellow,
        "Habit 5" to Color.Magenta,
    )
    val maxValue = 100f
    val userInfo = listOf(
        "Username" to "User",
        "User Id" to "213-3131-3131f",
        "Member Since" to LocalDate.now().format(formatter),
        "Last Login" to LocalDate.now().format(formatter),
    )

    var visibleItems = remember { mutableStateListOf<Pair<String, String>>() }

    var editProfile by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {

        userInfo.forEach { pair ->
            visibleItems.add(pair)

            delay(300)
        }
    }
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = MaterialTheme.colorScheme.background
            )
    ){
        CustomHeaderSheet(
            onCollapse = onCollapse,
            title = "Profile",
            rightIcon = {
                if(!editProfile){
                    Button(
                        onClick = {
                            editProfile = true
                        },
                        Modifier.size(50.dp),
                        contentPadding = PaddingValues(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor =  Color.Transparent,
                            contentColor = MaterialTheme.colorScheme.primary,
                        ),
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_edit),
                            contentDescription = "",
                        )
                    }
                }

            }
        )
        if(!editProfile){
            LazyColumn (
                modifier =  Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(MaterialTheme.colorScheme.background)
            ){
                item {
                    ProfileStat(
                        data = data,
                        labels = labels,
                        maxValue = maxValue
                    )
                    Spacer(Modifier.height(5.dp).fillMaxWidth())

                }
                items(visibleItems) { pair ->
                    ProfileInformationStrip(
                        pair.first,
                        pair.second,
                    )
                }
            }
        }else{
            ProfileView(
                hasParentSubmit = false,
                onParentSubmit = {}
            )
        }

    }

}

@Preview
@Composable
fun ProfilePreview(){
    RoutinuityTheme {
        Profile(onCollapse = {})
    }
}
