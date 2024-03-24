package com.example.momocinema.AppComponent

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.momocinema.R

@Composable
fun TextFieldCustom(
    @StringRes label: Int,
    leadingIcon: ImageVector,
    keyboardOptions: KeyboardOptions,
    value: String,
    onValueChange:(String) -> Unit,
    modifier: Modifier = Modifier.size(width = 310.dp, height = 65.dp)
) {

    OutlinedTextField(
        singleLine = true,
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = stringResource(label)) },
        placeholder = { Text(text = stringResource(id = label)) },
        leadingIcon = { Icon(imageVector = leadingIcon, contentDescription = null, tint = Color(0xFF0DD2FA)) },
        keyboardOptions = keyboardOptions,
        modifier = modifier
    )
}

@Composable
fun PasswordTextField(
    @StringRes label: Int,
    leadingIcon: ImageVector,
    keyboardOptions: KeyboardOptions,
    value: String,
    onValueChange:(String) -> Unit,
    idError: Int,
    modifier: Modifier = Modifier.size(width = 310.dp, height = 92.dp)
) {
    var isShowPassword by remember {
        mutableStateOf(false)
    }
    OutlinedTextField(
        singleLine = true,
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = stringResource(label)) },
        leadingIcon = { Icon(imageVector = leadingIcon, contentDescription = null, tint = Color(0xFF0DD2FA)) },
        placeholder = { Text(text = stringResource(id = label)) },
        keyboardOptions = keyboardOptions,
        trailingIcon = { IconButton(onClick = { isShowPassword = !isShowPassword }) {
            Icon(painter = painterResource(id = if(isShowPassword) R.drawable.view else R.drawable.hide), contentDescription = null, tint = Color(0xFF0DD2FA), modifier = Modifier.size(25.dp))
        }
        },
        visualTransformation = if(isShowPassword) VisualTransformation.None else PasswordVisualTransformation(),
        isError = (idError > 0),
        supportingText = {
            @StringRes var warningText = when (idError) {
                1 -> R.string.idError_1
                2 -> R.string.idError_2
                3 -> R.string.idError_3
                else -> R.string.idError_0
            }
            Text(text = stringResource(id = warningText), color = Color.Red )
        },
        modifier = modifier
    )
}