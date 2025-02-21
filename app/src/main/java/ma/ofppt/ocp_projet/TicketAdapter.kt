package ma.ofppt.ocp_projet.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ma.ofppt.ocp_projet.R
import ma.ofppt.ocp_projet.Ticket

class TicketAdapter(private val ticketList: List<Ticket>) :
    RecyclerView.Adapter<TicketAdapter.TicketViewHolder>() {

    class TicketViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTicketID: TextView = itemView.findViewById(R.id.Ticket)
        val tvProductType: TextView = itemView.findViewById(R.id.Typechangeordemand)
        val tvAgentType: TextView = itemView.findViewById(R.id.Tagent)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_main_activity9_ticket, parent, false)
        return TicketViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: TicketViewHolder, position: Int) {
        val ticket = ticketList[position]
        holder.tvTicketID.text = "Ticket ID: ${ticket.id}"
        holder.tvProductType.text = "Product: ${ticket.productType}"
        holder.tvAgentType.text = "Agent: ${ticket.agentType}"
    }

    override fun getItemCount(): Int = ticketList.size
}
