package ma.ofppt.ocp_projet

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import ma.ofppt.ocp_projet.databinding.ActivityMainActivity3ActiveaccountBinding
import ma.ofppt.ocp_projet.databinding.ActivityMainActivity6ChangeBinding

class MainActivity6_Change : AppCompatActivity() {
    private lateinit var binding: ActivityMainActivity6ChangeBinding
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainActivity6ChangeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val productSpinner: Spinner = findViewById(R.id.product2)
        val product = arrayOf("Pc DELL", "Smart Phone : Samsung", "Smart Phone : Iphone")
        val Sadapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, product)
        Sadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        productSpinner.adapter = Sadapter

        val typeSpinner: Spinner = findViewById(R.id.type2)
        val type = arrayOf("Pc fix", "pc laptop", "Samsung A56", "Samsung S24", "iPHONE 16 pro", "iPHONE 16 pro max")
        val Tadapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, type)
        Tadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        typeSpinner.adapter = Tadapter

        val AgentSpinner: Spinner = findViewById(R.id.TypeAgent2)
        val Agent = arrayOf("Tamka", "Orcadre", "Directeur", "VIP")
        val Typeadapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, Agent)
        Tadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        AgentSpinner.adapter = Typeadapter

        binding.backtext.setOnClickListener {
            val textactiveintent = Intent(this, MainActivity::class.java)
            startActivity(textactiveintent)
        }

        binding.back.setOnClickListener {
            val backintent = Intent(this, MainActivity4_Actionactivity::class.java)
            startActivity(backintent)
        }
    }
}