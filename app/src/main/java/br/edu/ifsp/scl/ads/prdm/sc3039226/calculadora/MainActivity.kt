package br.edu.ifsp.scl.ads.prdm.sc3039226.calculadora

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.edu.ifsp.scl.ads.prdm.sc3039226.calculadora.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.one.setOnClickListener { clearZero(); binding.bigNum.append("1") }
        binding.two.setOnClickListener { clearZero(); binding.bigNum.append("2") }
        binding.three.setOnClickListener { clearZero(); binding.bigNum.append("3") }
        binding.four.setOnClickListener { clearZero(); binding.bigNum.append("4") }
        binding.five.setOnClickListener { clearZero(); binding.bigNum.append("5") }
        binding.six.setOnClickListener { clearZero(); binding.bigNum.append("6") }
        binding.seven.setOnClickListener { clearZero(); binding.bigNum.append("7") }
        binding.eight.setOnClickListener { clearZero(); binding.bigNum.append("8") }
        binding.nine.setOnClickListener { clearZero(); binding.bigNum.append("9") }
        binding.zero.setOnClickListener { clearZero(); binding.bigNum.append("0") }
        binding.dot.setOnClickListener { binding.bigNum.append(".") }


        binding.plus.setOnClickListener {
            binding.smallNum.text = binding.bigNum.text.toString() + " + "
            binding.bigNum.text = ""
        }
        binding.minus.setOnClickListener {
            binding.smallNum.text = binding.bigNum.text.toString() + " - "
            binding.bigNum.text = ""
        }
        binding.multiply.setOnClickListener {
            binding.smallNum.text = binding.bigNum.text.toString() + " * "
            binding.bigNum.text = ""
        }
        binding.divide.setOnClickListener {
            binding.smallNum.text = binding.bigNum.text.toString() + " / "
            binding.bigNum.text = ""
        }

        binding.Clear.setOnClickListener {
            binding.bigNum.text = ""
            binding.smallNum.text = ""
        }
        binding.Backspace.setOnClickListener {
            val currVal = binding.bigNum.text.toString()
            if (currVal.isNotEmpty()) {
                binding.bigNum.text = currVal.substring(0, currVal.length - 1)
            }
        }

        binding.equals.setOnClickListener {
            //nothing here yet im just commiting so i can save
        }
    }

    private fun clearZero () {
        if (binding.bigNum.text == "0") {
            binding.bigNum.text = ""
        }
    }
}
