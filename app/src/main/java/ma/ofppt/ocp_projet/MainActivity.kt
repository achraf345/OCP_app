package ma.ofppt.ocp_projet

import DatabaseHelper
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import ma.ofppt.ocp_projet.databinding.ActivityMainActivity3ActiveaccountBinding
import ma.ofppt.ocp_projet.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var databaseHelper: DatabaseHelper

    @SuppressLint("MissingInflatedId", "WrongViewCast")
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
            val resetintent = Intent(this, MainActivity2_resetpassword::class.java)
            startActivity(resetintent)
        }
        binding.Ulogin?.setOnClickListener {
            val matricule = binding.Umatricule?.text.toString()
            val password2 = binding.Upassword?.text.toString()

            if (matricule.isNotEmpty() && password2.isNotEmpty()) {
                LoginUser(
                    matricule, password2,
                    matricule = TODO(),
                    phonenumber = TODO(),
                    password = TODO(),
                    cpassword = TODO()
                )
            }else{
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
            binding.Ulogin!!.setOnClickListener{
                val intentlogin = Intent(this,MainActivity4_Actionactivity::class.java)
                startActivity(intentlogin)
            }
            binding.Uactive?.setOnClickListener {
                val activeintent = Intent(this, MainActivity3_activeaccount::class.java)
                startActivity(activeintent)
            }
            binding.Uecspaceadministrateur?.setOnClickListener {
                val escpacead = Intent(this, MainActivity7_escepaceadministrateur::class.java)
                startActivity(escpacead)
            }
            binding.UescpaceApprovissionement?.setOnClickListener {
                val escpaceap = Intent(this, MainActivity8_escpaceapprovisionement::class.java)
                startActivity(escpaceap)
            }
        }
    }
    private fun LoginUser(firstname: String, lastname: String, matricule: String, phonenumber: String, password: String, cpassword: String){
        val userExist = databaseHelper.ReadUser(firstname, lastname, matricule, phonenumber, password, cpassword)
        if (userExist){
            Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
            val intent = Intent(this , MainActivity4_Actionactivity::class.java)
            startActivity(intent)
            finish()
        }else{
            Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
        }
    }
}