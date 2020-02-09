package com.example.myapplication

import android.content.Context
import com.example.myapplication.Constants.Companion.LANG_PREF_NAME
import com.example.myapplication.Constants.Companion.LOCALE_COUNTRY
import com.example.myapplication.Constants.Companion.LOCALE_LANG
import java.util.*

class LocalizationHelper {
    companion object {
        private const val DEF_LANGUAGE = "ru"
        private const val DEF_COUNTRY = "RU"

        fun setLocale(context: Context, lang: String, country: String): Context = try {
            persistLocale(context, lang, country)
            val locale = Locale(lang, country)
            Locale.setDefault(locale)

            val config = context.resources.configuration
            config.setLocale(locale)
            context.createConfigurationContext(config)
        } catch (e: Exception) {
            context
        }

        private fun persistLocale(context: Context, lang: String, country: String) {
            val sPref = context.getSharedPreferences(LANG_PREF_NAME, Context.MODE_PRIVATE)
            sPref.edit()
                .putString(LOCALE_LANG, lang)
                .putString(LOCALE_COUNTRY, country)
                .apply()
        }

        private fun getPersistedLocale(context: Context): Locale {
            val sPref = context.getSharedPreferences(LANG_PREF_NAME, Context.MODE_PRIVATE)
            val persistLang = sPref.getString(LOCALE_LANG, DEF_LANGUAGE) ?: DEF_COUNTRY
            val persistCountry = sPref.getString(LOCALE_COUNTRY, DEF_COUNTRY) ?: DEF_COUNTRY

            return Locale(persistLang, persistCountry)
        }

        fun onAttach(context: Context): Context {
            val locale = getPersistedLocale(context)
            return setLocale(context, locale.language, locale.country)
        }
    }
}