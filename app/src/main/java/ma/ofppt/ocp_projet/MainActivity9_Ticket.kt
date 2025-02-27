package ma.ofppt.ocp_projet

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import ma.ofppt.ocp_projet.database.DBTicket
import ma.ofppt.ocp_projet.databinding.ActivityMainActivity9TicketBinding

class MainActivity9_Ticket : AppCompatActivity() {
    private lateinit var binding: ActivityMainActivity9TicketBinding
    private lateinit var dbDemand: DBDEMAND

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainActivity9TicketBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dbDemand = DBDEMAND(this)
        loadLastDemand()
        val lastDemand = dbDemand.getLastDemand()

        if (lastDemand != null) {
            binding.IDTicket.text = "ID: ${lastDemand.id}"
            binding.ProductType.text = "Product: ${lastDemand.product}"
            binding.Typeagent.text = "Agent Type: ${lastDemand.agentType}"

            binding.Valider.setOnClickListener {
                Toast.makeText(this, "the Ticket is Confirmed",Toast.LENGTH_SHORT).show()
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
            binding.IDTicket.text = "ID: ${lastDemand.id}"
            binding.ProductType.text = "Product: ${lastDemand.product}"
            binding.Typeagent.text = "Agent Type: ${lastDemand.agentType}"
        } else {
            binding.IDTicket.text = "No Data"
            binding.ProductType.text = "No Data"
            binding.Typeagent.text = "No Data"
        }
    }
}