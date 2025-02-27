package ma.ofppt.ocp_projet

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ma.ofppt.ocp_projet.database.DBTicket
import ma.ofppt.ocp_projet.databinding.ActivityMainActivity10LivraisonBinding
import ma.ofppt.ocp_projet.databinding.ActivityMainActivity9TicketBinding

class MainActivity10_Livraison : AppCompatActivity() {
    private lateinit var binding: ActivityMainActivity10LivraisonBinding
    private lateinit var dbDemand: DBDEMAND

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainActivity10LivraisonBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dbDemand = DBDEMAND(this)
        loadLastDemand()
        val lastDemand = dbDemand.getLastDemand()

        if (lastDemand != null) {
            binding.Ticket.text = "ID: ${lastDemand.id}"
            binding.Typechangeordemand.text = "Product: ${lastDemand.product}"
            binding.Tagent.text = "Agent Type: ${lastDemand.agentType}"

            binding.Confirm.setOnClickListener {
                Toast.makeText(this, "the Ticket is under Review", Toast.LENGTH_SHORT).show()
                intent.putExtra("ticket_id", lastDemand.id)
                intent.putExtra("product", lastDemand.product)
                intent.putExtra("agent_type", lastDemand.agentType)
                startActivity(intent)
            }
        }
    }
    @SuppressLint("SetTextI18n")
    private fun loadLastDemand() {
        val lastDemand = dbDemand.getLastDemand()
        if (lastDemand != null) {
            binding.Ticket.text = "ID: ${lastDemand.id}"
            binding.Typechangeordemand.text = "Product: ${lastDemand.product}"
            binding.Tagent.text = "Agent Type: ${lastDemand.agentType}"
        } else {
            binding.Ticket.text = "No Data"
            binding.Typechangeordemand.text = "No Data"
            binding.Tagent.text = "No Data"
        }
    }
}