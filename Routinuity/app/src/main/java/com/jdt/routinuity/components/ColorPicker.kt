package com.jdt.routinuity.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.github.skydoves.colorpicker.compose.ColorEnvelope
import com.github.skydoves.colorpicker.compose.HsvColorPicker
import com.github.skydoves.colorpicker.compose.rememberColorPickerController
import com.jdt.routinuity.toHex

@Composable
fun ColorPickerDialog(
    onDismiss: () -> Unit,
    onColorChange: (String) -> Unit,
    currentColor: Color
) {
    val controller = rememberColorPickerController()
    var selectedColor by remember { mutableStateOf(currentColor) }

    Dialog(onDismissRequest = { onDismiss() }) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White, shape = RoundedCornerShape(16.dp))
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Select a Color")

                HsvColorPicker(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(450.dp)
                        .padding(10.dp),
                    controller = controller,
                    initialColor = selectedColor,
                    onColorChanged = { colorEnvelope: ColorEnvelope ->
                        selectedColor = colorEnvelope.color
                    }
                )

                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Box(modifier = Modifier.size(50.dp).background(color = selectedColor))
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        onColorChange(selectedColor.toHex())
                        onDismiss()
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(25f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.background
                    ),
                ) {
                    Text(text = "Change Color")
                }
            }
        }
    }
}
