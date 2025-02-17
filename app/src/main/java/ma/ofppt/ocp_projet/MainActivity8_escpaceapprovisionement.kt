package ma.ofppt.ocp_projet

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ma.ofppt.ocp_projet.databinding.ActivityMainActivity3ActiveaccountBinding
import ma.ofppt.ocp_projet.databinding.ActivityMainActivity8EscpaceapprovisionementBinding

class MainActivity8_escpaceapprovisionement : AppCompatActivity() {
    private lateinit var binding: ActivityMainActivity8EscpaceapprovisionementBinding
    private lateinit var spinner: Spinner
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainActivity8EscpaceapprovisionementBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main_activity8_escpaceapprovisionement)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        spinner = findViewById(R.id.spinnerAp)
        val servicesad = arrayOf("Service: 692", "Service: 211", "Service: 763", "Service: 057", "Service: 364")
        val adapterad = ArrayAdapter(this, android.R.layout.simple_spinner_item, servicesad)
        adapterad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapterad
    }
}