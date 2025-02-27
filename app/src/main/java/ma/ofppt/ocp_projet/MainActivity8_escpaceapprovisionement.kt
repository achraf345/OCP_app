package ma.ofppt.ocp_projet

import DatabaseHelper
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import ma.ofppt.ocp_projet.databinding.ActivityMainActivity8EscpaceapprovisionementBinding

class MainActivity8_escpaceapprovisionement : AppCompatActivity() {
    private lateinit var binding: ActivityMainActivity8EscpaceapprovisionementBinding
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainActivity8EscpaceapprovisionementBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseHelper = DatabaseHelper(this)

        val locations = listOf("Select an option:", "KH Ville", "Mrah", "Bni Amir", "Mlikat", "Point B", "Point A", "Sidi Chenane", "COZ")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, locations)
        binding.EAPspinner.adapter = adapter

        binding.EAPcomfirm.setOnClickListener {
            val matricule = binding.EAPmatricule.text.toString().trim()
            val fullName = binding.EAPfullname.text.toString().trim()
            val phoneNumber = binding.EAPnumber.text.toString().trim()
            val password = binding.EAPpassword.text.toString().trim()
            val location = binding.EAPspinner.selectedItem.toString()

            if (matricule.isEmpty() || fullName.isEmpty() || phoneNumber.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (phoneNumber.length < 10) {
                Toast.makeText(this, "Invalid phone number", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (location == "Select an option:") {
                Toast.makeText(this, "Please select a valid location", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val result = databaseHelper.addUser(fullName, phoneNumber, matricule, "", password, password, location)

            if (result != -1L) {
                Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity10_Livraison::class.java))
                finish()
            } else {
                Toast.makeText(this, "Failed to Create Account", Toast.LENGTH_SHORT).show()
            }
        }
        binding.EAPbacktext.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}