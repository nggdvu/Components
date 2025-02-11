package com.vund33.components.ui.home

import android.app.LocaleManager
import android.content.Context
import android.os.Build
import android.os.LocaleList
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.core.os.LocaleListCompat


enum class ThemeType {
    DYNAMIC, MONOCHROME, ANDROID
}

class HomeViewModel {

    private val THEME_PREFS = "theme_prefs"
    private val THEME_KEY = "theme"

    var selectedTheme by mutableStateOf(ThemeType.DYNAMIC)
        private set

    fun setLanguage(context: Context, languageCode: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            context.getSystemService(LocaleManager::class.java)
                .applicationLocales = LocaleList.forLanguageTags(languageCode)
        } else {
            AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(languageCode))
        }
        saveLanguagePreference(context, languageCode)
    }

    private fun saveLanguagePreference(context: Context, languageCode: String) {
        val pref = context.getSharedPreferences("language_prefs", Context.MODE_PRIVATE)
        pref.edit().putString("language", languageCode).apply()
    }

    fun getCurrentLanguageCode(context: Context): String {
        val locale = context.resources.configuration.locales[0]
        return locale.language
    }

    fun updateTheme(context: Context, theme: ThemeType) {
        selectedTheme = theme
        saveThemePreference(context, theme)
    }

    private fun saveThemePreference(context: Context, theme: ThemeType) {
        val pref = context.getSharedPreferences(THEME_PREFS, Context.MODE_PRIVATE)
        pref.edit().putString(THEME_KEY, theme.name).apply()
    }

    fun loadThemePreference(context: Context) {
        val pref = context.getSharedPreferences(THEME_PREFS, Context.MODE_PRIVATE)
        val themeName = pref.getString(THEME_KEY, ThemeType.DYNAMIC.name)
        selectedTheme = ThemeType.valueOf(themeName!!)
    }

    @Composable
    fun getThemeColors(isDarkTheme: Boolean): ColorScheme {

        val context = LocalContext.current
        return when (selectedTheme) {

            ThemeType.DYNAMIC -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                    if (isDarkTheme) {
                        androidx.compose.material3.dynamicDarkColorScheme(context)
                    } else {
                        androidx.compose.material3.dynamicLightColorScheme(context)
                    }
                } else {
                    if (isDarkTheme) {
                        darkColorScheme()
                    } else {
                        lightColorScheme()
                    }
                }
            }

            ThemeType.MONOCHROME -> {
                val monochromeColor = Color(0xFF808080)
                if (isDarkTheme) {
                    darkColorScheme(
                        primary = monochromeColor,
                        secondary = monochromeColor,
                        tertiary = monochromeColor,
                        background = Color.Black,
                        surface = Color.Black,
                        onPrimary = Color.White,
                        onSecondary = Color.White,
                        onTertiary = Color.White,
                        onBackground = Color.White,
                        onSurface = Color.White,
                    )
                } else {
                    lightColorScheme(
                        primary = monochromeColor,
                        secondary = monochromeColor,
                        tertiary = monochromeColor,
                        background = Color.White,
                        surface = Color.White,
                        onPrimary = Color.Black,
                        onSecondary = Color.Black,
                        onTertiary = Color.Black,
                        onBackground = Color.Black,
                        onSurface = Color.Black,
                    )
                }
            }

            ThemeType.ANDROID -> {
                if (isDarkTheme) {
                    darkColorScheme(
                        primary = Color(0xFFBB86FC),
                        secondary = Color(0xFF03DAC6),
                        tertiary = Color(0xFF3700B3)
                    )
                } else {
                    lightColorScheme(
                        primary = Color(0xFF6200EE),
                        secondary = Color(0xFF03DAC6),
                        tertiary = Color(0xFF3700B3)
                    )
                }
            }
        }
    }
}