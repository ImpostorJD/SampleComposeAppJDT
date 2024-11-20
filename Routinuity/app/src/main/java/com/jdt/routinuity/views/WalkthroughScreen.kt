package com.jdt.routinuity.views

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.jdt.routinuity.R
import com.jdt.routinuity.components.ProfileView
import com.jdt.routinuity.utils.RawImageViewer
import com.jdt.routinuity.ui.theme.RoutinuityTheme
import com.jdt.routinuity.views.walkthrough.AttributesSlider
import kotlinx.coroutines.launch

@Composable
fun WalkthroughScreen(navController: NavHostController) {

    var profileSubmitAction by remember { mutableStateOf<(() -> Unit)?>(null) }

    val pages:  List<@Composable () -> Unit> = listOf(
        { RawImageViewer(R.raw.hero) },
        { AttributesSlider() },
        {  ProfileView(
            hasParentSubmit = true,
            onParentSubmit = {
                if (it) {
                    navController.navigate("dashboard")
                }
            },
            onSubmitTriggered = { submitAction ->
                profileSubmitAction = submitAction
            }
        )
        }
    )
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { pages.size }
    )

    val coroutineScope = rememberCoroutineScope()

    val texts = listOf(
        "Start your self improvement journey. Create new habits and remove bad habits.",
        "You control your story. Set your own attributes to get started.",
        "Give us a little information about yourself?"
    )

    val animatedColors = List(pages.size) { index ->
        animateColorAsState(
            targetValue = if (pagerState.currentPage == index) {
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.inversePrimary
            },
            animationSpec = tween(durationMillis = 250), label = ""
        ).value
    }

    val animatedSize =List(pages.size) { index ->
        animateDpAsState(
            targetValue = if (pagerState.currentPage == index) 15.dp else 10.dp,
            animationSpec = tween(durationMillis = 250),
            label = ""
        ).value
    }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ){

        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .weight(1f)
                .padding(10.dp, 0.dp)
                .pointerInput(Unit) { detectTapGestures { } }
        ) { page ->
           pages[page]()
        }

        Column(
            modifier = Modifier.fillMaxWidth().height(200.dp),
        ) {
            Box (modifier = Modifier
                .fillMaxWidth()
            ){
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    for (index in pages.indices){
                        Box (
                            modifier = Modifier
                                .size(animatedSize[index])
                                .background(
                                    color = animatedColors[index],
                                    shape = CircleShape
                                )
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                    }

                }
            }
            Spacer(modifier = Modifier.height(40.dp))
            Column(modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(20.dp, 0.dp),
               horizontalAlignment = Alignment.End
            ){
                Text(texts[pagerState.currentPage], style = TextStyle(textAlign = TextAlign.Center, fontSize = 18.sp))
                Spacer(modifier = Modifier.height(30.dp))
                Button(
                    onClick = {
                        if(pagerState.currentPage == pages.size - 1){
                            profileSubmitAction?.invoke()
                            return@Button
                        }
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    },
                    shape = RoundedCornerShape(25f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.background
                    ),
                ) {
                    Text(if(pagerState.currentPage != (pages.size -1) ) "Next" else "Finished")
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
        }

    }
}


@Preview
@Composable
fun WalkthroughScreenPreview(){
    val navController = rememberNavController()
    RoutinuityTheme {
        WalkthroughScreen(navController)
    }
}