package ma.ofppt.ocp_projet

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import ma.ofppt.ocp_projet.databinding.ActivityMainActivity5DemandBinding

class MainActivity5_demand : AppCompatActivity() {

    private lateinit var binding: ActivityMainActivity5DemandBinding
    private lateinit var databaseHelper: DBDEMAND

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainActivity5DemandBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseHelper = DBDEMAND(this)

        val products = listOf("Select Product", "PC", "Imprimante", "SmartPhone")
        val adapterProduct = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, products)
        binding.Dproduct.adapter = adapterProduct

        val types = listOf("Select Type", "PC Dell fix", "PC Dell Laptop", "Imprimante", "Samsung A54", "Samsung S24", "Iphone 16 Pro", "Iphone 16 Pro Max")
        val adapterType = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, types)
        binding.Dtype.adapter = adapterType

        val agentTypes = listOf("Select Agent Type", "Tamka", "Orcadre", "Dericteur", "VIP", "VIP+")
        val adapterAgentType = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, agentTypes)
        binding.DtypeAgent.adapter = adapterAgentType

        binding.Dback.setOnClickListener {
            startActivity(Intent(this, MainActivity4_Actionactivity::class.java))
        }
        binding.Dbacktext.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
        binding.Ddemande.setOnClickListener {
            val matricule = binding.Dmatricule.text.toString()
            val fullname = binding.Dfullname.text.toString()
            val product = binding.Dproduct.selectedItem.toString()
            val type = binding.Dtype.selectedItem.toString()
            val phone = binding.Dphonenumber.text.toString()
            val agentType = binding.DtypeAgent.selectedItem.toString()

            if (matricule.isEmpty() || fullname.isEmpty() || phone.isEmpty() ||
                product == "Select Product" || type == "Select Type" || agentType == "Select Agent Type") {
                Toast.makeText(this, "Please fill all fields correctly", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val isInserted = databaseHelper.insertDemand(matricule, fullname, product, type, phone, agentType)
            if (isInserted) {
                Toast.makeText(this, "Demand Submitted Successfully", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Failed to Submit Demand", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
