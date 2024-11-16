import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

enum class TriangleDirection {
    Left, Right
}

enum class TriangleOrientation {
    Upright, UpsideDown
}

@Composable
fun RoundedRightTriangle(
    modifier: Modifier = Modifier,
    color: Color = Color.Black,
    cornerRadius: Dp = 10.dp,
    direction: TriangleDirection = TriangleDirection.Right,
    orientation: TriangleOrientation = TriangleOrientation.Upright,
    onClick: (() -> Unit)? = null,
    content: @Composable (() -> Unit)? = null,
) {
    Box(
        modifier = modifier
            .fillMaxHeight()
            .clip(
                if (direction == TriangleDirection.Right)
                    if(orientation == TriangleOrientation.Upright)
                        RoundedCornerShape(0.dp, cornerRadius, 0.dp, 0.dp)
                    else
                        RoundedCornerShape(0.dp, 0.dp, 0.dp, 0.dp)
                else
                    if(orientation == TriangleOrientation.Upright)
                        RoundedCornerShape(cornerRadius, 0.dp, 0.dp, 0.dp)
                    else
                        RoundedCornerShape(0.dp, 0.dp, 0.dp, 0.dp)
            )
    ) {
        Triangle(
            modifier = Modifier.fillMaxSize(),
            color = color,
            direction = direction,
            orientation = orientation,
            content = content,
            onClick = onClick
        )
    }
}

@Composable
fun Triangle(
    modifier: Modifier = Modifier,
    color: Color = Color.Black,
    direction: TriangleDirection = TriangleDirection.Right,
    orientation: TriangleOrientation = TriangleOrientation.Upright,
    content: @Composable (() -> Unit)? = null,
    onClick: (() -> Unit)? = null,
) {
    Box(
        modifier = modifier
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = { onClick?.invoke() }
                )
            }
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val width = size.width
            val height = size.height

            val path = Path().apply {
                if (direction == TriangleDirection.Right) {
                    if (orientation == TriangleOrientation.Upright) {
                        moveTo(0f, height)
                        lineTo(width, height)
                        lineTo(width, 0f)
                    } else { // UpsideDown
                        moveTo(0f, 0f)
                        lineTo(width, 0f)
                        lineTo(width, height)
                    }
                } else { // Left
                    if (orientation == TriangleOrientation.Upright) {
                        moveTo(width, height)
                        lineTo(0f, height)
                        lineTo(0f, 0f)
                    } else { // UpsideDown
                        moveTo(width, 0f)
                        lineTo(0f, 0f)
                        lineTo(0f, height)
                    }
                }
                close()
            }
            drawPath(path, color)
        }

        content?.invoke()
    }
}
