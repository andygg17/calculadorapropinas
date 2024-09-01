package garcia.andrea.calcularpropinas

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Switch
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var tip = 0.0
        val calculo = findViewById<Button>(R.id.calculate_button)
        calculo.setOnClickListener {
            val cost = findViewById<EditText>(R.id.cost_of_service).text.toString().toDoubleOrNull()
            if (cost == null) {
                tip = 0.0
                findViewById<TextView>(R.id.tip_result).text = "Tip: $tip"
                return@setOnClickListener
            }
            val porciento = when (findViewById<RadioGroup>(R.id.tip_options).checkedRadioButtonId) {
                R.id.option_twenty_percent -> 0.20
                R.id.option_eighteen_percent -> 0.18
                else -> 0.15
            }
            tip = cost * porciento
            if (findViewById<Switch>(R.id.round_up_switch).isChecked) {
                tip = kotlin.math.ceil(tip)
            }
            findViewById<TextView>(R.id.tip_result).text = "Tip: $tip"
        }
    }
}