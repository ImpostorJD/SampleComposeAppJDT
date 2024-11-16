package com.jdt.routinuity.dashboard

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.input.pointer.pointerInput
import com.jdt.routinuity.ui.theme.RoutinuityTheme
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.*
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun BiDirectionalScrollableWeekCalendar(
    modifier: Modifier = Modifier,
    textColor: Color = MaterialTheme.colorScheme.background,
    activeColor: Color = MaterialTheme.colorScheme.primary,
    initialSelectedDay: LocalDate,
    onClick: ((LocalDate) -> Unit)? = null,
) {

    var weekDates by remember { mutableStateOf(generateInitialWeekDates(initialSelectedDay)) }
    var currentMonth by remember { mutableStateOf(getMonthAndYear(weekDates.first())) }
    var isLoading by remember { mutableStateOf(true) }
    val listState = rememberLazyListState()

    LaunchedEffect(weekDates) {
        isLoading = true
        kotlinx.coroutines.delay(500)
        isLoading = false
    }

    LaunchedEffect(listState) {
        snapshotFlow { listState.layoutInfo.visibleItemsInfo.firstOrNull()?.index ?: 0 }
            .map { firstVisibleIndex ->
                val lastVisibleIndex = listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
                firstVisibleIndex to lastVisibleIndex
            }
            .collectLatest { (firstVisibleIndex, lastVisibleIndex) ->
                val threshold = 2

                val isNearEnd = lastVisibleIndex >= weekDates.size - threshold
                if (isNearEnd) {
                    weekDates = loadMoreFutureDates(weekDates)
                }

                val isAtTop = firstVisibleIndex <= threshold
                if (isAtTop) {
                    val previousSize = weekDates.size
                    weekDates = loadMorePastDates(weekDates)
                    val addedItems = weekDates.size - previousSize
                    listState.scrollToItem(firstVisibleIndex + addedItems)
                }
            }
    }
    Column(
        modifier = Modifier.fillMaxWidth().height(100.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(currentMonth, style = MaterialTheme.typography.titleLarge, color = textColor)

        Box(
            modifier.fillMaxSize()
        ) {
            if (isLoading) {
                ShimmerPlaceholder()
            } else {
                LazyRow(
                    state = listState,
                    modifier = modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(10.dp, 0.dp, 0.dp, 0.dp)
                ) {
                    itemsIndexed(weekDates) { index, date ->
                        val middleItemIndex by remember(listState) {
                            derivedStateOf {
                                listState.firstVisibleItemIndex + listState.layoutInfo.visibleItemsInfo.size / 2
                            }
                        }

                        if (index == middleItemIndex) {
                            currentMonth = getMonthAndYear(date)
                        }

                        val isSelected = date == initialSelectedDay


                        val backgroundColor by animateColorAsState(
                            targetValue = if (isSelected) textColor.copy(alpha = 0.8f) else Color.Transparent,
                            animationSpec = tween(durationMillis = 300), label = ""
                        )
                        val textColorAnimated by animateColorAsState(
                            targetValue = if (isSelected) activeColor else textColor,
                            animationSpec = tween(durationMillis = 300), label = ""
                        )

                        Box(
                            modifier = Modifier
                                .fillMaxHeight()
                                .padding(6.dp)
                                .background(backgroundColor, shape = MaterialTheme.shapes.small)
                                .pointerInput(Unit) {
                                    detectTapGestures { onClick?.invoke(date) }
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.padding(4.dp)
                            ) {
                                Text(
                                    text = date.dayOfWeek.getDisplayName(
                                        TextStyle.SHORT,
                                        Locale.getDefault()
                                    ),
                                    color = textColorAnimated,
                                    style = androidx.compose.ui.text.TextStyle(fontSize = 10.sp),
                                    textAlign = TextAlign.Center
                                )
                                Text(
                                    text = date.dayOfMonth.toString(),
                                    color = textColorAnimated,
                                    style = androidx.compose.ui.text.TextStyle(
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Bold
                                    ),
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ShimmerPlaceholder() {
    val shimmerColors = listOf(
        MaterialTheme.colorScheme.background.copy(alpha = 0.3f),
        MaterialTheme.colorScheme.background.copy(alpha = 0.1f)
    )

    val transition = rememberInfiniteTransition(label = "")
    val offsetX by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = ""
    )

    val brush = Brush.linearGradient(
        colors = shimmerColors,
        start = androidx.compose.ui.geometry.Offset(offsetX, 0f),
        end = androidx.compose.ui.geometry.Offset(offsetX + 300f, 0f),
        tileMode = TileMode.Mirror
    )

    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp, 16.dp)
    ) {
        repeat(7) {
            Spacer(Modifier.width(3.dp))
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .background(brush, shape = MaterialTheme.shapes.small)
                    .padding(8.dp)
            )
            Spacer(Modifier.width(5.dp))
        }
    }
}

fun generateInitialWeekDates(initialDay: LocalDate): List<LocalDate> {
//    val pastDates = (1..7).map { initialDay.minusDays(it.toLong()) }.reversed()
    val futureDates = (0..14).map { initialDay.plusDays(it.toLong()) }
    return  futureDates
}

fun loadMorePastDates(existingDates: List<LocalDate>): List<LocalDate> {

    val newPastDates = (1..8).map { existingDates.first().minusDays(it.toLong()) }.reversed()
    return newPastDates + existingDates
}

fun loadMoreFutureDates(existingDates: List<LocalDate>): List<LocalDate> {
    val newFutureDates = (1..8).map { existingDates.last().plusDays(it.toLong()) }
    return existingDates + newFutureDates
}

fun getMonthAndYear(date: LocalDate): String {
    return "${date.month.getDisplayName(TextStyle.FULL, Locale.getDefault())} ${date.year}"
}

@Preview
@Composable
fun WeekdaysScrollablePreview() {
    RoutinuityTheme {
        BiDirectionalScrollableWeekCalendar(initialSelectedDay = LocalDate.now())
    }
}
