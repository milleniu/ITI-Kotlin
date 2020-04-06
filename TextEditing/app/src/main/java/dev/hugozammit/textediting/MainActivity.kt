package dev.hugozammit.textediting

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    private lateinit var editText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.editText)
    }

    fun onNavigationButtonClick(view: View) {
        val intent = Intent(this, DisplayTextActivity::class.java)

        val text = editText!!.text.toString()
        intent.putExtra(Constants.INTENT_EXTRA_TEXT, if (text.isNullOrBlank()) null else text)

        startActivity(intent)
    }

}
