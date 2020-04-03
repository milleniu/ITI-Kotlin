package dev.hugozammit.textediting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class DisplayTextActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_text)

        val text = intent.getStringExtra(Constants.text())
        val displayText = if (text is String) text else "No text provided"

        findViewById<TextView>(R.id.textView)!!.text = displayText
    }

}
