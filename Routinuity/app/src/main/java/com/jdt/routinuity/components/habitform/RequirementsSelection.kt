package com.jdt.routinuity.components.habitform

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.jdt.routinuity.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RequirementsSelection(
    toggleRequirements: (String) -> Unit,
    delimiter: String,
    changeDelimiter: (String) -> Unit,
    selectedType: String,
    requirements: String,
    toggleRequirementsAmount: (String) -> Unit
){
    val timerUnits = listOf("Seconds", "Minutes", "Hours")
    var selectedTypeContext by remember { mutableStateOf(selectedType) }
    var delimiterContext by remember { mutableStateOf(delimiter) }
    var expanded by remember { mutableStateOf(false) }
    var requirementThreshold by remember { mutableStateOf(requirements) }

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp, 0.dp)
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(130.dp),
            horizontalArrangement = Arrangement.Center
        ){
            listOf("Quantity", "Timer", "Repetition").forEach { type ->
                RequirementsButton(
                    modifier = Modifier.weight(1f),
                    label = type,
                    isActive = (type == selectedType),
                    onClick = {
                        selectedTypeContext = type
                        toggleRequirements(type)
                        toggleRequirementsAmount("")
                        requirementThreshold = ""
                        if (selectedTypeContext != "Repetition"){
                            delimiterContext = ""
                            changeDelimiter("")
                        }
                    },
                    inactiveContentColor = MaterialTheme.colorScheme.inversePrimary,
                    inactiveContainerColor = Color.Transparent,
                    activeContentColor = MaterialTheme.colorScheme.background,
                    activeContainerColor = MaterialTheme.colorScheme.primary,
                    icon = when (type) {
                        "Quantity" -> R.drawable.ic_quantity
                        "Timer" -> R.drawable.ic_timer
                        "Repetition" -> R.drawable.ic_repetition
                        else -> R.drawable.ic_quantity
                    }
                )
                Spacer(modifier = Modifier.width(5.dp))
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {

            TextField(
                value = requirementThreshold,
                onValueChange = {
                    requirementThreshold = it
                    toggleRequirementsAmount(it)
                },   keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                ),
                visualTransformation = VisualTransformation.None,
                label = { Text("Enter Amount") },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    cursorColor = MaterialTheme.colorScheme.primary
                )
            )
            Spacer(Modifier.height(10.dp))
            when (selectedType) {
                "Quantity" -> {
                    TextField(
                        value = delimiterContext,
                        onValueChange = {
                            delimiterContext = it
                            changeDelimiter(it)
                        },
                        label = { Text("Enter Quantity Delimiter") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            cursorColor = MaterialTheme.colorScheme.primary
                        )
                    )
                }

                "Timer" -> {
                    ExposedDropdownMenuBox(
                        expanded = expanded,
                        onExpandedChange = {
                            expanded = !expanded
                        }
                    ) {
                        TextField(
                            value = delimiter,
                            onValueChange = {},
                            readOnly = true,
                            label = { Text("Select Timer Unit") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { expanded = true }
                                .menuAnchor(
                                    type = MenuAnchorType.PrimaryNotEditable,
                                    enabled = true
                                ),
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color.Transparent,
                                unfocusedContainerColor = Color.Transparent,
                                cursorColor = MaterialTheme.colorScheme.primary
                            )
                        )
                        ExposedDropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false },
                            modifier = Modifier.background(color = MaterialTheme.colorScheme.background)
                        ) {
                            timerUnits.forEach { unit ->
                                DropdownMenuItem(
                                    text = { Text(unit) },
                                    onClick = {
                                        delimiterContext = unit
                                        expanded = false
                                        changeDelimiter(unit)
                                    },
                                    modifier = Modifier.background(color = MaterialTheme.colorScheme.background)
                                )
                            }
                        }
                    }

                }

                "Repetition" -> {
                    TextField(
                        value = "Times",
                        onValueChange = {
                            delimiterContext = it
                            changeDelimiter(it)
                        },
                        label = { Text("Repetition Count") },
                        modifier = Modifier.fillMaxWidth(),
                        enabled = false,
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            disabledContainerColor = Color.Transparent,
                            disabledTextColor = MaterialTheme.colorScheme.inversePrimary,
                        )
                    )
                }
            }
        }
    }
}
