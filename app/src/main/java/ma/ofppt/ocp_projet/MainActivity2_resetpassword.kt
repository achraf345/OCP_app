package ma.ofppt.ocp_projet

import DatabaseHelper
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ma.ofppt.ocp_projet.databinding.ActivityMainActivity2ResetpasswordBinding

class MainActivity2_resetpassword : AppCompatActivity() {
    private lateinit var binding: ActivityMainActivity2ResetpasswordBinding
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainActivity2ResetpasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        dbHelper = DatabaseHelper(this)
        binding.Rcomfirm.setOnClickListener {
            resetPassword()
        }
        binding.Rbacktext.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
    private fun resetPassword() {
        val matricule = binding.Rmatricule.text.toString().trim()
        val newPassword = binding.Rpassword.text.toString().trim()
        val confirmPassword = binding.Rconfirmepassword.text.toString().trim()
        if (matricule.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show()
            return
        }
        if (newPassword != confirmPassword) {
            Toast.makeText(this, "Les mots de passe ne correspondent pas", Toast.LENGTH_SHORT).show()
            return
        }
        val updated = dbHelper.updatePassword(matricule, newPassword)
        if (updated) {
            Toast.makeText(this, "Mot de passe mis à jour avec succès", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            Toast.makeText(this, "Échec de la mise à jour, utilisateur introuvable", Toast.LENGTH_SHORT).show()
        }
    }
}