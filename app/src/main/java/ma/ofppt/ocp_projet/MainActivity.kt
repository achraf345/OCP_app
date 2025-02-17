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

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainActivity3ActiveaccountBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var reset : TextView
    private lateinit var Login : Button
    @SuppressLint("MissingInflatedId", "WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainActivity3ActiveaccountBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        firebaseAuth = FirebaseAuth.getInstance()
        reset = findViewById(R.id.Reset)
        reset.setOnClickListener{
            val intentReset = Intent(this,MainActivity2_resetpassword::class.java)
            startActivity(intentReset)
        }

        Login = findViewById(R.id.Login)
        Login.setOnClickListener{
            val intentlogin = Intent(this,MainActivity4_Actionactivity::class.java)
            startActivity(intentlogin)
        }


        binding.active.setOnClickListener {
            val activeintent = Intent(this, MainActivity3_activeaccount::class.java)
            startActivity(activeintent)
        }

        Login.setOnClickListener {
            val matricule = binding.Matricule.text.toString()
            val password2 = binding.Password2.text.toString()

            if (matricule.isEmpty() && password2.isEmpty()) {
                firebaseAuth.signInWithEmailAndPassword(matricule, password2).addOnCompleteListener {
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

            val escpacead = findViewById<TextView>(R.id.ecspaceadministrateur)
            escpacead.setOnClickListener {
                val escpacead = Intent(this, MainActivity7_escepaceadministrateur::class.java)
                startActivity(escpacead)
            }

            val escpaceap = findViewById<TextView>(R.id.escpaceApprovissionement)
            escpaceap.setOnClickListener {
                val escpaceap = Intent(this, MainActivity8_escpaceapprovisionement::class.java)
                startActivity(escpaceap)
            }
        }
    }
}