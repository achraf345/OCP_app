package ma.ofppt.ocp_projet

import DatabaseHelper
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ma.ofppt.ocp_projet.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        databaseHelper = DatabaseHelper(this)
        binding.Uresetpassword?.setOnClickListener {
            startActivity(Intent(this, MainActivity2_resetpassword::class.java))
        }
        binding.Ulogin?.setOnClickListener {
            val matricule = binding.Umatricule?.text.toString()
            val password2 = binding.Upassword?.text.toString()

            if (matricule.isNotEmpty() && password2.isNotEmpty()) {
                loginUser(matricule, password2)
                Toast.makeText(this, "Login Succesful", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }
        binding.Uactive?.setOnClickListener {
            startActivity(Intent(this, MainActivity3_activeaccount::class.java))
        }
        binding.Uecspaceadministrateur?.setOnClickListener {
            startActivity(Intent(this, MainActivity7_escepaceadministrateur::class.java))
        }
        binding.UescpaceApprovissionement?.setOnClickListener {
            startActivity(Intent(this, MainActivity8_escpaceapprovisionement::class.java))
        }
    }
    private fun loginUser(matricule: String, password: String) {
        val userExists = databaseHelper.readUser(matricule, password)
        if (userExists) {
            Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, MainActivity4_Actionactivity::class.java))
            finish()
        } else {
            Toast.makeText(this, "Login Failed: Incorrect credentials", Toast.LENGTH_SHORT).show()
        }
    }
}
