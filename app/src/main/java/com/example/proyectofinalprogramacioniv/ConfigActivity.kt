package com.example.proyectofinalprogramacioniv

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Switch
import androidx.appcompat.app.AppCompatDelegate
import com.example.proyectofinalprogramacioniv.Domain.ClsAuthentication

class ConfigActivity : AppCompatActivity() {

    private lateinit var regresarH:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config)

        regresarH = findViewById(R.id.Regresarh)

        val swiitch = findViewById<Switch>(R.id.swtich)
        val ivModo = findViewById<ImageView>(R.id.ivModo)


        swiitch.setOnCheckedChangeListener { _, _ ->
            if (swiitch.isChecked) {

                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                swiitch.text = "Desactivar modo oscuro"
                ivModo.setImageResource(R.drawable.modooscuro)
            } else{

                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                swiitch.text = "Activar modo oscuro"
                ivModo.setImageResource(R.drawable.modoclaro)

            }

        }
    }

    // Funciones ***********************************************************************************

    // Funcion para regresar a SignInActivity
    private fun SignIn(){

        val intent: Intent = Intent(this, SignInActivity::class.java)
        startActivity(intent)
    }

    fun RegresarAlHomee(view: View) {

        val ventanaSiguiente: Intent = Intent(this, DesignHome::class.java)
        startActivity(ventanaSiguiente)
    }

    //Funcion para cerrar secion
    fun SignOut(view: View){

        // Objeto referencia a ClsAuthentication
        var oClsAuthentication: ClsAuthentication = ClsAuthentication()

        // Ejecucion de funcion
        oClsAuthentication.SignOut()

        // Ejecucion de funcion SignIn()
        SignIn()
    }

}