package ma.ofppt.ocp_projet

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import ma.ofppt.ocp_projet.databinding.ActivityMainActivity4ActionactivityBinding

class MainActivity4_Actionactivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainActivity4ActionactivityBinding

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainActivity4ActionactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.ACdemand.setOnClickListener {
            val intent = Intent(this, MainActivity5_demand::class.java)
            startActivity(intent)
        }
        binding.ACchangement.setOnClickListener {
            val intent = Intent(this, MainActivity6_Change::class.java)
            startActivity(intent)
        }
        binding.AClogout.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}