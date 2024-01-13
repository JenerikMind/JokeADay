package com.example.jokeaday.ui.commonComposables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.jokeaday.ui.theme.borderRadiusSize
import com.example.jokeaday.ui.theme.customFontFamily
import com.example.jokeaday.ui.theme.fontSizeNormal
import com.example.jokeaday.ui.theme.heightNormal
import com.example.jokeaday.ui.theme.spacingSmall

@Composable
fun PurpleButton(onClick: () -> Unit, resId: Int){
    Button(
        onClick = onClick,
        modifier = Modifier
            .padding(horizontal = spacingSmall)
            .height(heightNormal)
            .fillMaxWidth(),
        shape = RoundedCornerShape(borderRadiusSize),
    ) {
        Text(
            stringResource(id = resId),
            fontSize = fontSizeNormal,
            fontFamily = customFontFamily,
            fontWeight = FontWeight.Normal
        )
    }
}