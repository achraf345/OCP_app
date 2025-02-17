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
import ma.ofppt.ocp_projet.databinding.ActivityMainActivity6ChangeBinding

class MainActivity4_Actionactivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_activity4_actionactivity)

        val dementintet = findViewById<Button>(R.id.Demand2)
        dementintet.setOnClickListener {
            val intentd = Intent(this, MainActivity5_demand::class.java)
            startActivity(intentd)
        }

        val changeintent = findViewById<Button>(R.id.Changement2)
        changeintent.setOnClickListener {
            val intentc = Intent(this, MainActivity6_Change::class.java)
            startActivity(intentc)
        }

        val logoutintent = findViewById<TextView>(R.id.Logout)
        logoutintent.setOnClickListener {
            val intentlogout = Intent(this, MainActivity::class.java)
            startActivity(intentlogout)
        }
    }

}