package com.bersyte.taskmanagement.feature.home.views.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CalendarMonth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bersyte.taskmanagement.common.components.HorizontalSpace
import com.bersyte.taskmanagement.common.components.VerticalSpace
import com.bersyte.taskmanagement.utils.AppHelper
import com.bersyte.taskmanagement.utils.Month
import kotlinx.datetime.number

@Composable
fun CalendarWeekView() {

    val today = AppHelper.getCurrentDate().date
    val month = Month.getName(today.month.number)
    val daysOfWeek = AppHelper.getDaysOfTheWeek()

    val selectedDate  = remember { mutableStateOf(value = today)}
    val textStyle = MaterialTheme.typography


    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Rounded.CalendarMonth, contentDescription = "Calendar")
            HorizontalSpace(8)
            Text(
                "$month, ${today.year}",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold
                )
            )
        }
        VerticalSpace(16)
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            items(daysOfWeek) { date ->
                val isSelectedDate = selectedDate.value == date
                val bgColor = if(isSelectedDate) colorScheme.primary else Color.Transparent

                Box (
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = bgColor,
                            shape = RoundedCornerShape(16.dp)
                        )
                        .padding(4.dp)
                        .clickable {selectedDate.value = date}
                        .size(height = 80.dp, width = 50.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = date.dayOfWeek.name.take(3),
                            style = textStyle.titleSmall.copy(
                                color = colorScheme.onSurface.copy(
                                    alpha = 0.5f
                                )
                            )
                        )
                        Text(
                            text = date.dayOfMonth.toString(),
                            style = textStyle.titleLarge.copy(
                                fontWeight = FontWeight.Bold,
                                color = colorScheme.onSurface
                            )
                        )
                    }
                }
            }
        }
    }
}
