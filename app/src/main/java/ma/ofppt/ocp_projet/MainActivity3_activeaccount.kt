package ma.ofppt.ocp_projet

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
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
    private lateinit var authFirebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainActivity3ActiveaccountBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()

        authFirebaseAuth = FirebaseAuth.getInstance()

        binding.Alogintext.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        val items = listOf("Select an option : ", "KH Ville", "Mrah", "Bni Amir", "Mlikat", "Point B", "Point A", "Sidi Chenane", "COZ")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, items)
        binding.Aspinner.adapter = adapter
        binding.Aspinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem = items[position]
                if (position != 0) {
                    Toast.makeText(this@MainActivity3_activeaccount, "Selected: $selectedItem", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                Toast.makeText(this@MainActivity3_activeaccount, "Nothing selected", Toast.LENGTH_SHORT).show()
            }
        }

        binding.Aactive.setOnClickListener {
            val firstname = binding.Afirstname.text.toString()
            val lastname = binding.AlastName.text.toString()
            val matricule = binding.Amatricule.text.toString()
            val phonenumber = binding.Aphonenumber.text.toString()
            val password2 = binding.Apassword.text.toString()
            val confirmpassword = binding.Aconfirmpassword.text.toString()
            val spinner = binding.Aspinner.selectedItem.toString()

            if (firstname.isNotEmpty() || lastname.isNotEmpty() ||
                matricule.isNotEmpty() || phonenumber.isNotEmpty() ||
                password2.isNotEmpty() || confirmpassword.isNotEmpty()
                || spinner.isNotEmpty()) {
                if (password2 == confirmpassword) {
                    authFirebaseAuth.createUserWithEmailAndPassword(matricule, password2).addOnCompleteListener {
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
