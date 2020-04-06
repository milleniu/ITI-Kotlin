package dev.hugozammit.login

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var usernameTextEdit: EditText;
    private lateinit var passwordTextEdit: EditText;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        usernameTextEdit = findViewById(R.id.editText_login)
        passwordTextEdit = findViewById(R.id.editText_password)

        var (userName, userPassword) = restoreFromCache()
        usernameTextEdit.setText(userName)
        passwordTextEdit.setText(userPassword)
    }

    fun onLogin(view: View) {
        val userName = usernameTextEdit.text.toString()
        val userPassword = passwordTextEdit.text.toString()

        val userNameValidationError = validateUsername(userName)
        val userPasswordValidationError = validationPassword(userPassword)

        usernameTextEdit.error = userNameValidationError
        passwordTextEdit.error = userPasswordValidationError

        if( userNameValidationError != null || userPasswordValidationError != null )
            return

        saveToCache(Pair(userName, userPassword))

        Toast.makeText(applicationContext, getString(R.string.login_toast_content), Toast.LENGTH_SHORT).show()
    }

    private fun validateUsername(userName: String): String? {
        if( userName.isEmpty() )
            return getString(R.string.login_validation_missing)

        if( !Patterns.EMAIL_ADDRESS.matcher((userName)).matches() )
            return getString(R.string.login_validation_invalid)

        return null
    }

    private fun validationPassword(password: String): String? {
        if( password.isEmpty() )
            return getString(R.string.password_validation_missing)

        return null;
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
        val userName = sharedPreferences.getString(sharedPreferencesUserName, "") ?: ""
        val userPassword = sharedPreferences.getString(sharedPreferencesPassword, "") ?: ""

        return Pair( userName, userPassword )
    }
}
