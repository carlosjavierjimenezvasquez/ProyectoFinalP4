package com.example.proyectofinalprogramacioniv

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectofinalprogramacioniv.Domain.ClsAuthentication
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignInActivity : AppCompatActivity() {

    //Variables Globales
    private lateinit var firebaseAnalytics: FirebaseAnalytics /* */
    private lateinit var auth: FirebaseAuth
    private lateinit var signInBtn: Button
    private lateinit var emailEdt: EditText
    private lateinit var passwordEdt: EditText
    private lateinit var signUpTv: TextView
    private lateinit var signGoogleBtn: ImageView
    //private lateinit var signFacebookBtn: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signin_main)

        // Obtain the FirebaseAnalytics instance.
        firebaseAnalytics = Firebase.analytics

        // Initialize Firebase Auth
        auth = Firebase.auth

        // Asignacion de eventos a variables Globales
        signInBtn = findViewById(R.id.btnSignIn)
        emailEdt = findViewById(R.id.edtEmail)
        passwordEdt = findViewById(R.id.edtPassword)
        signUpTv = findViewById(R.id.tvGoSignUp)
        signGoogleBtn = findViewById(R.id.imgSignGoogle)
        //signFacebookBtn = findViewById(R.id.imgSignFacebook)

        //Inicializando Metodos
        SignIn()

    }

    override fun onStart() {
        super.onStart()

        // Objeto referencia a ClsAuthentication
        var oClsAuthentication: ClsAuthentication = ClsAuthentication()

        // Capturar respuesta de usuario iniciado
        var response = oClsAuthentication.VerificationSignIn()

        // Verificar respuesta
        if (response != 0){

            GoHome()
        }
        /*else{

            val intent: Intent = Intent(applicationContext, SignInActivity::class.java)
            startActivity(intent)
        }*/
    }

    // Funciones ***********************************************************************************

    fun GoSignUp(view: View) {

        val intent: Intent = Intent(applicationContext, SignUpActivity::class.java)
        startActivity(intent)
    }

    private fun GoHome() {

        val intent: Intent = Intent(applicationContext, DesignHome::class.java)
        startActivity(intent)
    }

    private fun Alerts() {

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se a pruducido un error")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    // Metodo de verificacion de inicio de sesion
    private fun SignIn() {

        //var response = 0
        //var oClsAuthentication: ClsAuthentication = ClsAuthentication()

        signInBtn.setOnClickListener {

            var email = emailEdt.text.toString()
            var password = passwordEdt.text.toString()
            /*var response = oClsAuthentication.SignIn(email, password)

            if (response != 0){

                GoHome()

            } else{

                Alerts()
            }*/

            if (email.isNotEmpty() && password.isNotEmpty()) {

                FirebaseAuth.getInstance().signInWithEmailAndPassword(
                    email,
                    password
                ).addOnCompleteListener {

                    if (it.isSuccessful) {

                        GoHome()

                    } else {

                        Alerts()
                    }
                }

            } else {

                Toast.makeText(
                    applicationContext,
                    "Debes rellenar los campos", Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}