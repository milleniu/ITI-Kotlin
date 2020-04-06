package dev.hugozammit.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.EditText
import android.widget.Toast
import dev.hugozammit.login.SharedPreferencesHelper.get
import dev.hugozammit.login.SharedPreferencesHelper.set

class MainActivity : AppCompatActivity() {

    private lateinit var usernameTextEdit: EditText
    private lateinit var passwordTextEdit: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        usernameTextEdit = findViewById(R.id.editText_login)
        passwordTextEdit = findViewById(R.id.editText_password)

        val sharedPreferences = SharedPreferencesHelper.defaultPreferences(this, Constants.SHARED_PREFERENCES_USER_DATA)
        val userName: String = sharedPreferences[Constants.SHARED_PREFERENCES_USERNAME] ?: ""
        val userPassword: String = sharedPreferences[Constants.SHARED_PREFERENCES_PASSWORD] ?: ""

        usernameTextEdit.setText(userName)
        passwordTextEdit.setText(userPassword)
    }

    fun onLogin(view: View) {
        val username = usernameTextEdit.text.toString()
        val password = passwordTextEdit.text.toString()

        val usernameValidationError = validateUsername(username)
        val passwordValidationError = validatePassword(password)

        usernameTextEdit.error = usernameValidationError
        passwordTextEdit.error = passwordValidationError

        if( usernameValidationError != null || passwordValidationError != null )
            return

        val sharedPreferences = SharedPreferencesHelper.defaultPreferences(this, Constants.SHARED_PREFERENCES_USER_DATA)
        sharedPreferences[Constants.SHARED_PREFERENCES_USERNAME] = username
        sharedPreferences[Constants.SHARED_PREFERENCES_PASSWORD] = password

        Toast.makeText(applicationContext, getString(R.string.login_toast_content), Toast.LENGTH_SHORT).show()
    }

    private fun validateUsername(userName: String): String? {
        if( userName.isEmpty() )
            return getString(R.string.login_validation_missing)

        if( !Patterns.EMAIL_ADDRESS.matcher((userName)).matches() )
            return getString(R.string.login_validation_invalid)

        return null
    }

    private fun validatePassword(password: String): String? {
        if( password.isEmpty() )
            return getString(R.string.password_validation_missing)

        return null;
    }
}
