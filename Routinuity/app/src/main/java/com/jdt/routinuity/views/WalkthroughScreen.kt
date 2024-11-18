package com.jdt.routinuity.views

import android.graphics.BitmapFactory
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jdt.routinuity.R
import com.jdt.routinuity.ui.theme.RoutinuityTheme
import kotlinx.coroutines.launch

@Composable
fun WalkthroughScreen(){

    val pages: List<@Composable () -> Unit>  = listOf(
        {GetStarted()},
        {GetStarted()},
        {GetStarted()}
    )
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { pages.size }
    )


    val coroutineScope = rememberCoroutineScope()

    val formCompletionState = remember { mutableStateListOf(false, false, false) }

    val context = LocalContext.current

    val bitmap = remember {
        BitmapFactory.decodeStream(context.resources.openRawResource(R.raw.hero))
    }
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
            targetValue = if (pagerState.currentPage == index) 30.dp else 20.dp,
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
            modifier = Modifier.weight(1f)
                .pointerInput(Unit) { detectTapGestures { } }
        ) { page ->
           pages[page]()
        }

        Row(
            modifier = Modifier.fillMaxWidth().height(250.dp),
            horizontalArrangement = Arrangement.SpaceBetween
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
            Button(
                onClick = {
                    coroutineScope.launch {
                        if (pagerState.currentPage > 0) {
                            pagerState.animateScrollToPage(pagerState.currentPage - 1)
                        }
                    }
                },
                enabled = pagerState.currentPage > 0
            ) {
                Text("Previous")
            }

            Button(
                onClick = {
                    coroutineScope.launch {
                        if (formCompletionState[pagerState.currentPage]) {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    }
                },
                enabled = formCompletionState[pagerState.currentPage] &&
                        pagerState.currentPage < pagerState.pageCount - 1
            ) {
                Text("Next")
            }
        }
//        Image(
//            bitmap = bitmap.asImageBitmap(),
//            contentDescription = "",
//            modifier = Modifier.fillMaxSize(),
//            contentScale = ContentScale.Fit
//        )
    }
}
@Composable
fun GetStarted(){
    Text("hello world")
}


@Preview
@Composable
fun WalkthroughScreenPreview(){
    RoutinuityTheme {
        WalkthroughScreen()
    }
}