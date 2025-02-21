package ma.ofppt.ocp_projet

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import ma.ofppt.ocp_projet.adapters.TicketAdapter
import ma.ofppt.ocp_projet.database.DBTicket
import ma.ofppt.ocp_projet.databinding.ActivityMainActivity9TicketBinding

class MainActivity9_Ticket : AppCompatActivity() {
    private lateinit var binding: ActivityMainActivity9TicketBinding
    private lateinit var db: DBTicket

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainActivity9TicketBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = DBTicket(this)

        loadTickets()
    }
    private fun loadTickets() {
        val tickets = db.getAllTickets()
        val adapter = TicketAdapter(tickets)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }
}