package es.manel.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import es.app.laliguilla.core.extension.isEmail
import es.app.laliguilla.core.extension.isNumeric

class MainActivity : AppCompatActivity() {
    private lateinit var password: String
    private lateinit var username: String
    private lateinit var passwordInputLayout: TextInputLayout
    private lateinit var usernameInputLayout: TextInputLayout
    private lateinit var btn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Primer portar les referències de la vista al controlador -MainActivity-

        usernameInputLayout = findViewById(R.id.inputlayout_login_username)
        passwordInputLayout = findViewById(R.id.inputlayout_login_password);
        btn = findViewById(R.id.login_btn)

        usernameInputLayout.editText?.doAfterTextChanged { editableText->

            if (editableText?.toString()?.length!! > 0) {
                usernameInputLayout.error = null
            }
        }


        this.btn.setOnClickListener { viewBtn ->
            username = usernameInputLayout.editText?.text.toString()
            password = passwordInputLayout.editText?.text.toString()

            validateInputLayoutUserName()

            validateInputLayoutPassword()

            if (validateInputLayoutUserName() && validateInputLayoutPassword()){
                if(username.equals("manelcc@gmail.com") && password.equals("123456")) {
                    startActivity(Intent(this, HomeActivity::class.java))
                }else{
                    Snackbar.make(usernameInputLayout,"Login Incorrecte, verifica les dades, si us plau",Snackbar.LENGTH_SHORT).show()
                }
            }else{
                Snackbar.make(usernameInputLayout,"Verifica que sigui corecte",Snackbar.LENGTH_SHORT).show()
            }


            /*

             //Validar userName && password = Anar a la home.

             if(username.isEmail() && password.isNumeric()) {
                 startActivity(Intent(this, HomeActivity::class.java))
             }else{
                 Toast.makeText(this,"el username te que ser un email",Toast.LENGTH_SHORT).show()
             }*/
        }
    }

    private fun validateInputLayoutPassword(): Boolean {
        return when (password.isNumeric()) {
            true -> { usernameInputLayout.error = null;true }
            false -> {
                passwordInputLayout.error = "Password no correcte"
                false
            }
        }
    }

    private fun validateInputLayoutUserName(): Boolean {
        if (username.isEmail()) {
            usernameInputLayout.error = null
            return true
        } else {
            usernameInputLayout.error = "Ups comprova l'email"
            return false
        }
    }

    fun String.isEmail(): Boolean {

        val p = "^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w+)+)\$".toRegex()
        return this.matches(p)
    }
}