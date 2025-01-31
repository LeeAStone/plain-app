package com.ismartcoding.plain.ui.page.settings

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RadioButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.ismartcoding.lib.helpers.CoroutinesHelper.withIO
import com.ismartcoding.plain.R
import com.ismartcoding.plain.data.preference.AmoledDarkThemePreference
import com.ismartcoding.plain.data.enums.DarkTheme
import com.ismartcoding.plain.data.preference.DarkThemePreference
import com.ismartcoding.plain.data.preference.LocalAmoledDarkTheme
import com.ismartcoding.plain.data.preference.LocalDarkTheme
import com.ismartcoding.plain.ui.base.BottomSpace
import com.ismartcoding.plain.ui.base.DisplayText
import com.ismartcoding.plain.ui.base.PListItem
import com.ismartcoding.plain.ui.base.PScaffold
import com.ismartcoding.plain.ui.base.PSwitch
import com.ismartcoding.plain.ui.base.Subtitle
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DarkThemePage(
    navController: NavHostController,
) {
    val context = LocalContext.current
    val darkTheme = LocalDarkTheme.current
    val amoledDarkTheme = LocalAmoledDarkTheme.current
    val scope = rememberCoroutineScope()

    PScaffold(
        navController,
        content = {
            LazyColumn {
                item {
                    DisplayText(text = stringResource(R.string.dark_theme))
                    DarkTheme.values().map {
                        PListItem(
                            title = it.getText(context),
                            onClick = {
                                scope.launch {
                                    withIO {
                                        DarkThemePreference.putAsync(context, it)
                                    }
                                }
                            },
                        ) {
                            RadioButton(selected = it.value == darkTheme, onClick = {
                                scope.launch {
                                    withIO {
                                        DarkThemePreference.putAsync(context, it)
                                    }
                                }
                            })
                        }
                    }
                    Subtitle(
                        text = stringResource(R.string.other),
                    )
                    PListItem(
                        title = stringResource(R.string.amoled_dark_theme),
                        onClick = {
                            AmoledDarkThemePreference.put(context, !amoledDarkTheme)
                        },
                    ) {
                        PSwitch(activated = amoledDarkTheme) {
                            AmoledDarkThemePreference.put(context, !amoledDarkTheme)
                        }
                    }
                    BottomSpace()
                }
            }
        }
    )
}
