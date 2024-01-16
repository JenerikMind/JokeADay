package com.example.jokeaday.ui.reusableComposables

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.jokeaday.ui.theme.customFontFamily
import com.example.jokeaday.ui.theme.fontSizeNormal

@Composable
fun CustomTextButton(
    onClick: () -> Unit,
    modifier: Modifier,
    resId: Int,
    textColor: Color
) {
    TextButton(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(0),
    ) {
        Text(
            text = stringResource(id = resId),
            color = textColor,
            fontFamily = customFontFamily,
            fontSize = fontSizeNormal,
            fontWeight = FontWeight.Bold
        )
    }
}