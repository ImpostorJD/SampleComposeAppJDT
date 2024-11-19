package com.jdt.routinuity.views.walkthrough

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jdt.routinuity.components.ColorPickerDialog
import com.jdt.routinuity.toColor
import com.jdt.routinuity.ui.theme.RoutinuityTheme

@Composable
fun AttributesSlider() {
   var showDialog by remember { mutableStateOf(false) }
   var selectedIndex by remember { mutableIntStateOf(-1) }
   val attributes = remember {
      mutableStateListOf(
         mutableStateListOf("Wisdom", "#00BCD4"),
         mutableStateListOf("Finesse", "#FFEB3B"),
         mutableStateListOf("Vitality", "#4CAF50"),
         mutableStateListOf("Charisma", "#F44336"),
         mutableStateListOf("Fortitude", "#9C27B0")
      )
   }

   Column(
      modifier = Modifier
         .fillMaxSize()
         .background(MaterialTheme.colorScheme.background)
         .padding(16.dp)
   ) {
      if(showDialog){
         ColorPickerDialog(
            onDismiss = { showDialog = false },
            onColorChange = { newColor ->
               if (selectedIndex != -1) {
                  attributes[selectedIndex][1] = newColor
               }
               selectedIndex = -1
            },
            currentColor = attributes[selectedIndex][1].toColor()
         )

      }

      Text(
         "Attributes",
         modifier = Modifier.fillMaxWidth(),
         textAlign = TextAlign.Center,
         style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 25.sp)
      )

      LazyColumn(
         modifier = Modifier
            .weight(1f)
            .padding(vertical = 8.dp)
      ) {
         items(attributes.size) { index ->
            Row(
               modifier = Modifier
                  .fillMaxWidth()
                  .padding(vertical = 8.dp),
               verticalAlignment = Alignment.CenterVertically
            ) {
               TextField(
                  value = attributes[index][0],
                  onValueChange = { newName ->
                     attributes[index][0] = newName
                  },
                  textStyle = TextStyle(color = MaterialTheme.colorScheme.primary),
                  label = {
                     Text(
                        text = "Attribute ${index + 1}",
                        color = MaterialTheme.colorScheme.primary
                     )
                  },
                  modifier = Modifier.weight(1f),
                  colors = TextFieldDefaults.colors(
                     cursorColor = MaterialTheme.colorScheme.primary,
                     focusedTextColor = MaterialTheme.colorScheme.primary,
                     unfocusedTextColor = MaterialTheme.colorScheme.primary,
                     focusedContainerColor = Color.Transparent,
                     unfocusedContainerColor = Color.Transparent,
                     focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                     unfocusedIndicatorColor = MaterialTheme.colorScheme.primary,
                  )
               )
               Spacer(modifier = Modifier.width(8.dp))
               Button(
                  onClick = {
                     selectedIndex = index
                     showDialog = true
                  },
                  modifier = Modifier.size(40.dp),
                  shape = RoundedCornerShape(20f),
                  colors = ButtonDefaults.buttonColors(
                     containerColor = attributes[index][1].toColor()
                  ),
                  contentPadding = PaddingValues(0.dp),
               ) {}
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