package com.jdt.routinuity.views.walkthrough

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jdt.routinuity.ui.theme.RoutinuityTheme

@Composable
fun AttributesSlider(){
   Column (
      modifier = Modifier
         .fillMaxSize()
         .background(color = MaterialTheme.colorScheme.background)
         .padding(0.dp, 20.dp)
   ) {
      Text("Attributes",
         modifier = Modifier.fillMaxWidth(),
         textAlign = TextAlign.Center,
         style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 25.sp)
      )
      LazyColumn (modifier = Modifier
         .weight(1f)
      ){
         item{
            for(i in 0 until 5){
               Row(
                  modifier = Modifier
                     .fillMaxWidth()
                     .padding(0.dp, 10.dp),
                  verticalAlignment = Alignment.CenterVertically
               ){
                  TextField(
                     value = "",
                     onValueChange = { },
                     textStyle = TextStyle(color = MaterialTheme.colorScheme.primary),
                     label = { Text(text = "Attribute ${i + 1}", color = MaterialTheme.colorScheme.primary) },
                     modifier = Modifier.weight(1f),
                     colors = TextFieldDefaults.colors(
                        cursorColor = MaterialTheme.colorScheme.primary,
                        focusedTextColor =  MaterialTheme.colorScheme.primary,
                        unfocusedTextColor = MaterialTheme.colorScheme.primary,
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent,
                        focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                        unfocusedIndicatorColor = MaterialTheme.colorScheme.primary,
                     )
                  )
                  Spacer(modifier = Modifier.width(3.dp))
                  Button(
                      onClick = { },
                      modifier = Modifier.size(40.dp),
                      shape = RoundedCornerShape(20f),
                      colors = ButtonDefaults.buttonColors(
                         containerColor = Color.Red
                      ),
                      contentPadding = PaddingValues(0.dp),
                  ) { }
               }
               Spacer(modifier = Modifier.height(5.dp))
            }
         }
      }
   }
}

@Preview
@Composable
fun AttributesSliderPreview(){
    RoutinuityTheme {
       AttributesSlider()
    }
}