package dev.hugozammit.textediting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class DisplayTextActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_text)

        val text = intent.getStringExtra(Constants.INTENT_EXTRA_TEXT)
        val displayText = text ?: getString(R.string.editText_default)

        findViewById<TextView>(R.id.textView)!!.text = displayText
    }

}
