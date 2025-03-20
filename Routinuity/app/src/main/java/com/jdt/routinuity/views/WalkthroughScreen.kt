package com.jdt.routinuity.views

import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jdt.routinuity.R
import com.jdt.routinuity.components.ProfileView
import com.jdt.routinuity.utils.RawImageViewer
import com.jdt.routinuity.ui.theme.RoutinuityTheme
import com.jdt.routinuity.utils.DataStoreManager
import com.jdt.routinuity.utils.DataStoreManager.Companion
import com.jdt.routinuity.views.walkthrough.AttributesSlider
import com.jdt.routinuity.views.walkthrough.DataStoringInput
import kotlinx.coroutines.launch
@Composable
fun WalkthroughScreen(navController: NavHostController) {
    val context = LocalContext.current.applicationContext
    val dataStoreManager = remember { DataStoreManager(context) }

    val getChildDataList = remember { mutableStateListOf<(() -> Any)?>(null, null, null, null) }
    val validationStates = remember { mutableStateListOf(true, false, true, false) }

    val gson = Gson()

    fun updateValidationState(page: Int, isValid: Boolean) {
        validationStates[page] = isValid
    }

    val pages: List<@Composable () -> Unit> = listOf(
        { RawImageViewer(R.raw.hero) },
        {
            DataStoringInput(
                provideDataGetter = { getData -> getChildDataList[1] = getData },
                onValidationChanged = { isValid -> updateValidationState(1, isValid) }
            )
        },
        {
            AttributesSlider { getData -> getChildDataList[2] = getData }
        },
        {
            ProfileView(
                hasParentSubmit = true,
                onParentSubmit = { getData -> getChildDataList[3] = getData },
                onValidationChanged = {isValid -> updateValidationState(3, isValid)}
            )
        }
    )

    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { pages.size }
    )
    val coroutineScope = rememberCoroutineScope()
    val isNextEnabled = validationStates[pagerState.currentPage]

    val texts = listOf(
        "Start your self-improvement journey. Create new habits and remove bad habits.",
        "We value your privacy, so your information should be accessible only to you.",
        "Set your own attributes to get started. Different colors are recommended for different attributes.",
        "Tell us a little about yourself!"
    )

    val animatedColors = List(pages.size) { index ->
        animateColorAsState(
            targetValue = if (pagerState.currentPage == index) MaterialTheme.colorScheme.primary
            else MaterialTheme.colorScheme.inversePrimary,
            animationSpec = tween(durationMillis = 250),
            label = ""
        ).value
    }

    val animatedSizes = List(pages.size) { index ->
        animateDpAsState(
            targetValue = if (pagerState.currentPage == index) 15.dp else 10.dp,
            animationSpec = tween(durationMillis = 250),
            label = ""
        ).value
    }

    fun handleNextClick() {
//        getChildDataList[pagerState.currentPage]?.invoke()?.let { data ->
//            Log.d("WalkthroughScreen", "Page ${pagerState.currentPage} data: $data")
//        } ?: Log.d("WalkthroughScreen", "Page ${pagerState.currentPage} getter is null")

        if (pagerState.currentPage == pages.size - 1) {
            coroutineScope.launch {
                try {
                    getChildDataList[1]?.invoke()?.let { projectId ->
                        dataStoreManager.setProjectId(projectId.toString())
                    }

                    getChildDataList[2]?.invoke()?.let { attributesData ->
                        val type = object : TypeToken<List<List<String>>>() {}.type
                        val value: List<List<String>> = gson.fromJson(attributesData.toString(), type)
                        dataStoreManager.saveAttributes(value)
                    }

                    getChildDataList[3]?.invoke()?.let { profileData ->
                        dataStoreManager.setProjectId(profileData.toString())
                    }

                    dataStoreManager.setFinishedWalkthrough(true)
                    navController.navigate("dashboard")

                } catch (e: Exception) {
                    Log.e("WalkthroughScreen", "Error saving data: ${e.message}")
                }
            }
            navController.navigate("dashboard")
        } else {
            coroutineScope.launch { pagerState.animateScrollToPage(pagerState.currentPage + 1) }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 10.dp)
                .pointerInput(Unit) { detectTapGestures { } },
            userScrollEnabled = isNextEnabled
        ) { page ->
            pages[page]()
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                pages.indices.forEach { index ->
                    Box(
                        modifier = Modifier
                            .size(animatedSizes[index])
                            .background(animatedColors[index], shape = CircleShape)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                }
            }
            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = texts[pagerState.currentPage],
                style = TextStyle(textAlign = TextAlign.Center, fontSize = 16.sp),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(30.dp))

            Button(
                onClick = { handleNextClick() },
                enabled = isNextEnabled,
                shape = RoundedCornerShape(25.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.background
                ),
                modifier = Modifier.align(Alignment.End)
            ) {
                Text(if (pagerState.currentPage != pages.size - 1) "Next" else "Finish")
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

@Preview
@Composable
fun WalkthroughScreenPreview() {
    val navController = rememberNavController()
    RoutinuityTheme {
        WalkthroughScreen(navController)
    }
}
