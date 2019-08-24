package com.djelroy.jsm.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.djelroy.jsm.R
import kotlinx.android.synthetic.main.activity_new_application.*

class NewApplicationActivity : AppCompatActivity() {

    private lateinit var titleEditText: EditText
    private lateinit var companyEditText: EditText
    private lateinit var descriptionEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_application)

        titleEditText = findViewById<EditText>(R.id.edit_title)
        companyEditText = findViewById<EditText>(R.id.edit_company)
        descriptionEditText = findViewById<EditText>(R.id.edit_description)

        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener {
            val replyIntent = Intent()
            if(TextUtils.isEmpty(edit_company.text) || TextUtils.isEmpty(edit_title.text))
                setResult(Activity.RESULT_CANCELED, replyIntent)
            else{
                val title = titleEditText.text.toString()
                replyIntent.putExtra(EXTRA_APPLICATION_TITLE, title)
                val company = companyEditText.text.toString()
                replyIntent.putExtra(EXTRA_APPLICATION_COMPANY, company)
                val description = descriptionEditText.text.toString()
                replyIntent.putExtra(EXTRA_APPLICATION_DESCRIPTION, description)
                setResult(Activity.RESULT_OK, replyIntent)
            }

            finish()
        }
    }


    companion object {
        const val EXTRA_APPLICATION_TITLE = "com.djelroy.jsm.APPLICATION_TITLE"
        const val EXTRA_APPLICATION_COMPANY = "com.djelroy.jsm.APPLICATION_COMPANY"
        const val EXTRA_APPLICATION_DESCRIPTION = "com.djelroy.jsm.APPLICATION_DESCRIPTION"

    }
}
