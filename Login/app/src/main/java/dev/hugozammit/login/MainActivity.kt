package dev.hugozammit.login

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var loginTextEdit: EditText? = null
    private var passwordTextEdit: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loginTextEdit = findViewById(R.id.editText_login)
        passwordTextEdit = findViewById(R.id.editText_password)

        var (userName, userPassword) = restoreFromCache()
        loginTextEdit!!.setText(userName)
        passwordTextEdit!!.setText(userPassword)
    }

    fun onLogin(view: View) {
        val userName = loginTextEdit!!.text.toString()
        val userPassword = passwordTextEdit!!.text.toString()

        if( userName.isEmpty() ) {
            loginTextEdit!!.error = "Please enter your email"
            return
        } else if( !Patterns.EMAIL_ADDRESS.matcher((userName)).matches() ) {
            loginTextEdit!!.error = "Please enter a valid email address"
            return
        } else {
            loginTextEdit!!.error = null
        }

        if( userPassword.isEmpty() ) {
            passwordTextEdit!!.error = "Please enter your password"
            return
        } else {
            passwordTextEdit!!.error = null
        }

        saveToCache(Pair(userName, userPassword))

        val toastContent = "E-Mail / Password saved"
        val toastDuration = Toast.LENGTH_SHORT
        Toast.makeText(applicationContext, toastContent, toastDuration).show()
    }

    private val sharedPreferencesName = "UserData";
    private val sharedPreferencesUserName = "username";
    private val sharedPreferencesPassword = "password";

    private fun saveToCache(userInfo: Pair<String, String>) {
        val (userName, userPassword) = userInfo;
        val sharedPreferences = getSharedPreferences(sharedPreferencesName, Context.MODE_PRIVATE)

        val editor = sharedPreferences.edit()
        editor.putString(sharedPreferencesUserName, userName)
        editor.putString(sharedPreferencesPassword, userPassword)
        editor.commit()
    }

    private fun restoreFromCache(): Pair<String, String> {
        val sharedPreferences = getSharedPreferences(sharedPreferencesName, Context.MODE_PRIVATE)
        val userName = sharedPreferences.getString(sharedPreferencesUserName, "")!!
        val userPassword = sharedPreferences.getString(sharedPreferencesPassword, "")!!

        return Pair( userName, userPassword )
    }
}
