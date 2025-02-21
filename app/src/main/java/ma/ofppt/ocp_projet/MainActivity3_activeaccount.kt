package ma.ofppt.ocp_projet

import DatabaseHelper
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import ma.ofppt.ocp_projet.databinding.ActivityMainActivity3ActiveaccountBinding

class MainActivity3_activeaccount : AppCompatActivity() {
    private lateinit var binding: ActivityMainActivity3ActiveaccountBinding
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainActivity3ActiveaccountBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        databaseHelper = DatabaseHelper(this)

        val items = listOf("Select an option :", "KH Ville", "Mrah", "Bni Amir", "Mlikat", "Point B", "Point A", "Sidi Chenane", "COZ")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, items)
        binding.Aspinner.adapter = adapter

        binding.Aactive.setOnClickListener {
            val firstname = binding.Afirstname.text.toString()
            val lastname = binding.AlastName.text.toString()
            val matricule = binding.Amatricule.text.toString()
            val phonenumber = binding.Aphonenumber.text.toString()
            val password2 = binding.Apassword.text.toString()
            val confirmpassword = binding.Aconfirmpassword.text.toString()
            val spinnerSelection = binding.Aspinner.selectedItem.toString()

            if (spinnerSelection == "Select an option :") {
                Toast.makeText(this, "Please select a valid location", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (firstname.isNotEmpty() && lastname.isNotEmpty() &&
                matricule.isNotEmpty() && phonenumber.isNotEmpty() &&
                password2.isNotEmpty() && confirmpassword.isNotEmpty()) {
                if (password2 == confirmpassword) {
                    activeaccount(firstname, lastname, matricule, phonenumber, password2, confirmpassword, spinnerSelection)
                } else {
                    Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }
        binding.Alogintext.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            Toast.makeText(this,"Escpace User", Toast.LENGTH_SHORT).show()
        }
    }
    private fun activeaccount(firstname: String, lastname: String, matricule: String, phonenumber: String, password: String, cpassword: String, location: String) {
        val insertedRowId = databaseHelper.addUser(firstname, lastname, matricule, phonenumber, password, cpassword, location)
        if (insertedRowId != -1L) {
            Toast.makeText(this, "Your Account is Activated!", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            Toast.makeText(this, "Activation Failed: Could not insert data", Toast.LENGTH_SHORT).show()
        }
    }
}