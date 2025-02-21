package ma.ofppt.ocp_projet

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ma.ofppt.ocp_projet.databinding.ActivityMainActivity6ChangeBinding

class MainActivity6_Change : AppCompatActivity() {

    private lateinit var binding: ActivityMainActivity6ChangeBinding
    private lateinit var databaseHelper: DBCHANGE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainActivity6ChangeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        databaseHelper = DBCHANGE(this)
        val products = listOf("Select Product", "PC", "Imprimante", "SmartPhone")
        val adapterProduct = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, products)
        binding.Cproduct2.adapter = adapterProduct

        val types = listOf("Select Type", "PC Dell fix", "PC Dell Laptop", "Imprimante", "Samsung A54", "Samsung S24", "Iphone 16 Pro", "Iphone 16 Pro Max")
        val adapterType = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, types)
        binding.Ctype2.adapter = adapterType

        val agentTypes = listOf("Select Agent Type", "Tamka", "Orcadre", "Dericteur", "VIP", "VIP+")
        val adapterAgentType = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, agentTypes)
        binding.Ctypeagent2.adapter = adapterAgentType

        binding.Cback.setOnClickListener {
            startActivity(Intent(this, MainActivity4_Actionactivity::class.java))
        }
        binding.Cbacktext.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.Cchange.setOnClickListener {
            val matricule = binding.Cmatricule2.text.toString()
            val fullname = binding.Cfullname2.text.toString()
            val product = binding.Cproduct2.selectedItem.toString()
            val type = binding.Ctype2.selectedItem.toString()
            val description = binding.Cdescription.text.toString()
            val phone = binding.Cchangementphone.text.toString()
            val agentType = binding.Ctypeagent2.selectedItem.toString()

            if (matricule.isEmpty() || fullname.isEmpty() || description.isEmpty() || phone.isEmpty() ||
                product == "Select Product" || type == "Select Type" || agentType == "Select Agent Type") {
                Toast.makeText(this, "Please fill all fields correctly", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val isInserted = databaseHelper.insertChangeRequest(matricule, fullname, product, type, description, phone, agentType)
            if (isInserted) {
                Toast.makeText(this, "Change Request Submitted Successfully", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Failed to Submit Change Request", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
