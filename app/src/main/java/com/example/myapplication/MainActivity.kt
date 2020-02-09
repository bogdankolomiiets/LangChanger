package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.PackageManager.GET_META_DATA
import android.os.Bundle
import android.view.View
import android.widget.Button
import java.util.*

class MainActivity : BaseActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.language_uk_btn).setOnClickListener(this)
        findViewById<Button>(R.id.language_ru_btn).setOnClickListener(this)
        findViewById<Button>(R.id.new_activity).setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.language_uk_btn -> persistLanguage("uk", "UA")
            R.id.language_ru_btn -> persistLanguage("ru", "RU")
            R.id.new_activity -> {
                val secondActivityIntent = Intent(this, SecondActivity::class.java)
                startActivity(secondActivityIntent)
                //finish()
            }
        }
    }

    private fun persistLanguage(locale_lang: String, locale_country: String) {
        LocalizationHelper.setLocale(this, locale_lang, locale_country)
        recreate()
    }
}
