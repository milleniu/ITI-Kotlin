package dev.hugozammit.textediting

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    private var textTv: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textTv = findViewById(R.id.editText)
    }

    fun onNavigationButtonClick(view: View) {
        val intent = Intent(this, DisplayTextActivity::class.java)

        val text = textTv!!.text.toString()
        intent.putExtra(Constants.text(), if (text.isEmpty()) null else text)

        startActivity(intent)
    }

}
