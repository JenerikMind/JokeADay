package com.example.jokeaday.ui.sharedComposables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.example.jokeaday.R
import com.example.jokeaday.ui.theme.DarkGreen
import com.example.jokeaday.ui.theme.Purple40
import com.example.jokeaday.ui.theme.spacingSmallest

@Composable
fun SettingsDrawerSheet(isChecked: State<Boolean>, onCheckedChange: (Boolean) -> Unit) {
    ModalDrawerSheet(
        drawerContainerColor = Purple40,
        drawerShape = RoundedCornerShape(0)
    ) {
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = spacingSmallest)
            ) {
                Checkbox(
                    checked = isChecked.value,
                    onCheckedChange = onCheckedChange,
                    colors = CheckboxDefaults.colors(
                        checkmarkColor = DarkGreen,
                        uncheckedColor = Color.White,
                        checkedColor = Color.White
                    )
                )
                Text(stringResource(id = R.string.setting_nsfw))
            }
        }
    }
}