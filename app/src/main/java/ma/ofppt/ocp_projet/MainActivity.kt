package ma.ofppt.ocp_projet

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
    private lateinit var authuser : FirebaseAuth

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
        authuser = FirebaseAuth.getInstance()
        binding.Uresetpassword?.setOnClickListener {
            val resetintent = Intent(this, MainActivity2_resetpassword::class.java)
            startActivity(resetintent)
        }
        binding.Ulogin?.setOnClickListener {
            val matricule = binding.Umatricule?.text.toString()
            val password2 = binding.Upassword?.text.toString()

            if (matricule.isNotEmpty() && password2.isNotEmpty()) {
                authuser.signInWithEmailAndPassword(matricule, password2).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val intent = Intent(this, MainActivity4_Actionactivity::class.java)
                        startActivity(intent)
                    }
                    else {
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
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
}