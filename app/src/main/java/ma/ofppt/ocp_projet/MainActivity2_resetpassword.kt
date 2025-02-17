package ma.ofppt.ocp_projet

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ma.ofppt.ocp_projet.databinding.ActivityMainActivity2ResetpasswordBinding
import ma.ofppt.ocp_projet.databinding.ActivityMainActivity5DemandBinding

class MainActivity2_resetpassword : AppCompatActivity() {
    private lateinit var binding: ActivityMainActivity2ResetpasswordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainActivity2ResetpasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.Comfirm.setOnClickListener {
            val confirmintent = Intent(this , MainActivity::class.java)
            startActivity(confirmintent)

            Toast.makeText(this,"Password is completely change",Toast.LENGTH_SHORT).show()
        }
    }
}