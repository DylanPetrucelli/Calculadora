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
    private var isNewNumber: Boolean = true
    //clearZero wasnt working so i'm fixing the problem in another way

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

        val numberButtons = listOf(binding.one, binding.two, binding.three, binding.four,
            binding.five, binding.six, binding.seven, binding.eight, binding.nine, binding.zero)
        numberButtons.forEach { button ->
            button.setOnClickListener {
                if (isNewNumber) {
                    binding.bigNum.text = button.text.toString()
                    isNewNumber = false
                } else {
                    binding.bigNum.append(button.text.toString())
                }
            }
        }

        binding.dot.setOnClickListener {
            if (isNewNumber) {
                binding.bigNum.text = "0."
                isNewNumber = false
            } else if (!binding.bigNum.text.toString().contains(".")) {
                binding.bigNum.append(".")
            }
        }


        binding.plus.setOnClickListener {
            binding.smallNum.text = binding.bigNum.text.toString() + " + "
            binding.bigNum.text = "0"
            isNewNumber = true
            currOp = 1
        }
        binding.minus.setOnClickListener {
            binding.smallNum.text = binding.bigNum.text.toString() + " - "
            binding.bigNum.text = "0"
            isNewNumber = true
            currOp = 2
        }
        binding.multiply.setOnClickListener {
            binding.smallNum.text = binding.bigNum.text.toString() + " * "
            binding.bigNum.text = "0"
            isNewNumber = true
            currOp = 3
        }
        binding.divide.setOnClickListener {
            binding.smallNum.text = binding.bigNum.text.toString() + " / "
            binding.bigNum.text = "0"
            isNewNumber = true
            currOp = 4
        }

        binding.Clear.setOnClickListener {
            binding.bigNum.text = "0"
            binding.smallNum.text = ""
            isNewNumber = true
        }
        binding.Backspace.setOnClickListener {
            val currVal = binding.bigNum.text.toString()
            if (currVal.isNotEmpty()) {
                binding.bigNum.text = currVal.substring(0, currVal.length - 1)
            }
        }

        //I cannot stop this from crashing when the small number is zero this does not work at all
        binding.equals.setOnClickListener {
            if (binding.smallNum.text != "0" && binding.smallNum.text.isNotEmpty()) {
                when (currOp) {
                    1 -> addNumbers()
                    2 -> subNumbers()
                    3 -> multNumbers()
                    4 -> divNumbers()
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun addNumbers () {
        val firstNumber = binding.smallNum.text.toString().trim()
        val secNumber = binding.bigNum.text.toString().trim()

        val num1 = firstNumber.substring(0, firstNumber.length -2).toDouble()
        val num2 = secNumber.toDouble()

        binding.bigNum.text = (num1 + num2).toString()
        binding.smallNum.text = "0  "
    }
    private fun subNumbers () {
        val firstNumber = binding.smallNum.text.toString().trim()
        val secNumber = binding.bigNum.text.toString().trim()

        val num1 = firstNumber.substring(0, firstNumber.length -2).toDouble()
        val num2 = secNumber.toDouble()

        binding.bigNum.text = (num1 - num2).toString()
        binding.smallNum.text = "0  "
    }

    private fun multNumbers () {
        val firstNumber = binding.smallNum.text.toString().trim()
        val secNumber = binding.bigNum.text.toString().trim()

        val num1 = firstNumber.substring(0, firstNumber.length -2).toDouble()
        val num2 = secNumber.toDouble()

        binding.bigNum.text = (num1 * num2).toString()
        binding.smallNum.text = "0  "
    }

    @SuppressLint("SetTextI18n")
    private fun divNumbers () {
        val firstNumber = binding.smallNum.text.toString().trim()
        val secNumber = binding.bigNum.text.toString().trim()

        val num1 = firstNumber.substring(0, firstNumber.length -2).toDouble()
        val num2 = secNumber.toDouble()

        if (num2.toInt() == 0) {
            binding.bigNum.text = "don't do that"
            isNewNumber = true
            return
        }

        binding.bigNum.text = (num1 / num2).toString()
        binding.smallNum.text = "0  "
    }
}
