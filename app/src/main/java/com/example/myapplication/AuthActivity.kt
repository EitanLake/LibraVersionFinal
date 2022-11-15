package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityAuthBinding
import com.google.firebase.auth.FirebaseAuth
import kotlin.concurrent.thread


class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding
    //guarda variable
    private lateinit var auth: FirebaseAuth
    // inicia Firebase

    override fun onCreate(savedInstanceState: Bundle?) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        super.onCreate(savedInstanceState)
        Thread.sleep(1000)
        setTheme(R.style.SplashTheme)

        binding = ActivityAuthBinding.inflate(layoutInflater)
        //manda la ruta para los xml
        setContentView(binding.root)

       //Setup
        setup()
    }
    private fun setup() {
        binding.btnregistro.setOnClickListener{
            if (binding.txtcorreo.text.isNotEmpty() && binding.txtpass.text.isNotEmpty()){
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(binding.txtcorreo.text.toString(),
                    binding.txtpass.text.toString()).addOnCompleteListener{
                        if (it.isSuccessful){
                            showHome(it.result.user?.email ?:"", ProviderType.BASIC)
                        } else {
                            showAlert()
                        }
                }
            }
        }

        binding.btniniciarsesion.setOnClickListener{
            if (binding.txtcorreo.text.isNotEmpty() && binding.txtpass.text.isNotEmpty()){
                FirebaseAuth.getInstance().signInWithEmailAndPassword(binding.txtcorreo.text.toString(),
                    binding.txtpass.text.toString()).addOnCompleteListener{
                    if (it.isSuccessful){
                        showHome(it.result.user?.email ?:"", ProviderType.BASIC)
                    } else {
                        showAlert()
                    }
                }
            }

        }
    }

     private fun showAlert () {
         val builder = AlertDialog.Builder(this)
         builder.setTitle("Error")
         builder.setMessage("Se ha producido un error al registrar usuario")
         builder.setPositiveButton("accept",null)
         val dialog: AlertDialog = builder.create()
         dialog.show()
     }

    private fun showHome(email: String, provider : ProviderType){
        val homeIntent: Intent = Intent(this, bottom_navigation_bar::class.java).apply {
            putExtra("email",email)
            putExtra("Provider",provider.name)
        }
        startActivity(homeIntent)
    }
}

