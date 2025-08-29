package br.edu.ifsp.scl.ads.prdm.sc3039226.calculadora

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.edu.ifsp.scl.ads.prdm.sc3039226.calculadora.databinding.ActivityMainBinding
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var currOp: Int = 0
    //This number represents the current operation being used
    //zero is nothing

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
            currOp = 1
        }
        binding.minus.setOnClickListener {
            binding.smallNum.text = binding.bigNum.text.toString() + " - "
            binding.bigNum.text = ""
            currOp = 2
        }
        binding.multiply.setOnClickListener {
            binding.smallNum.text = binding.bigNum.text.toString() + " * "
            binding.bigNum.text = ""
            currOp = 3
        }
        binding.divide.setOnClickListener {
            binding.smallNum.text = binding.bigNum.text.toString() + " / "
            binding.bigNum.text = ""
            currOp = 4
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
            when (currOp) {
                1 -> addNumbers()
            }
        }
    }

    private fun clearZero () {
        if (binding.bigNum.text == "0") {
            binding.bigNum.text = ""
        }
    }

    private fun addNumbers () {
        val firstNumber = binding.smallNum.text.toString().trim()
        val secNumber = binding.bigNum.text.toString().trim()

        val num1 = firstNumber.substring(0, firstNumber.length -2).toDouble()
        val num2 = secNumber.toDouble()

        binding.bigNum.text = (num1 + num2).toString()
    }

}
