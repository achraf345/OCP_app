package ma.ofppt.ocp_projet

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import ma.ofppt.ocp_projet.databinding.ActivityMainActivity3ActiveaccountBinding

class MainActivity3_activeaccount : AppCompatActivity() {
    private lateinit var binding: ActivityMainActivity3ActiveaccountBinding
    private lateinit var FirebaseActive : FirebaseAuth

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainActivity3ActiveaccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        FirebaseActive = FirebaseAuth.getInstance()
        binding.Logintext.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val services = arrayOf("Service: 692", "Service: 211", "Service: 763", "Service: 057", "Service: 364")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, services)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.Spinner.adapter
        binding.active.setOnClickListener {
            val firstname = binding.Firstname.text.toString()
            val lastname = binding.LastName.text.toString()
            val matricule = binding.Matricule.text.toString()
            val phonenumber = binding.Phonenumber.text.toString()
            val password2 = binding.Password2.text.toString()
            val confirmpassword = binding.ConfirmPassword.text.toString()
            val spinner = binding.Spinner.selectedItem.toString()

            if (firstname.isEmpty() || lastname.isEmpty() ||
                matricule.isEmpty() || phonenumber.isEmpty() ||
                password2.isEmpty() || confirmpassword.isEmpty()
                || spinner.isEmpty()) {
                if (password2 == confirmpassword) {
                    FirebaseActive.createUserWithEmailAndPassword(matricule, password2).addOnCompleteListener {
                        if (it.isSuccessful) {
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                        }
                        else {
                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                else{
                    Toast.makeText(this, "Password not match", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
