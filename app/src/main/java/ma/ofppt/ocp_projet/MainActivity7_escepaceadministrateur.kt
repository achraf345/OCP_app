package ma.ofppt.ocp_projet

import DatabaseHelper
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ma.ofppt.ocp_projet.databinding.ActivityMainActivity7EscepaceadministrateurBinding

class MainActivity7_escepaceadministrateur : AppCompatActivity() {
    private lateinit var binding: ActivityMainActivity7EscepaceadministrateurBinding
    private lateinit var databaseHelper: DatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainActivity7EscepaceadministrateurBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseHelper = DatabaseHelper(this)

        val items = listOf("Select an option:", "KH Ville", "Mrah", "Bni Amir", "Mlikat", "Point B", "Point A", "Sidi Chenane", "COZ")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, items)
        binding.EADspinner.adapter = adapter

        binding.EADcomfirm.setOnClickListener {
            val matricule = binding.EADmatricule.text.toString().trim()
            val fullName = binding.EADfullname.text.toString().trim()
            val password = binding.EADpassword.text.toString().trim()
            val location = binding.EADspinner.selectedItem.toString()

            if (location == "Select an option:") {
                Toast.makeText(this, "Please select a valid location", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (matricule.isNotEmpty() && fullName.isNotEmpty() && password.isNotEmpty()) {
                val result = databaseHelper.addUser(fullName, "", matricule, "", password, password, location)

                if (result != -1L) {
                    Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, MainActivity9_Ticket::class.java))
                    finish()
                } else {
                    Toast.makeText(this, "Failed to Create Admin", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }
        binding.EADbacktext.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}