package com.example.laba6mobile2

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.PersistableBundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow

class FirstFragment : Fragment() {
    private lateinit var tableLayout: TableLayout
    private lateinit var buttons: List<Button>
    private lateinit var checkedButtons: List<Button>
    private val numbers = mutableListOf<Int>()
    private val selectedButtons = mutableListOf<Button>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_first, container, false)

        if(resources.configuration.orientation == 2){
            view.findViewById<Button>(R.id.helpButton).visibility = View.INVISIBLE
        }
        tableLayout = view.findViewById(R.id.tableLayout)
        buttons = mutableListOf<Button>().apply {
            for (i in 0 until tableLayout.childCount) {
                val tableRow = tableLayout.getChildAt(i) as TableRow
                for (j in 0 until tableRow.childCount) {
                    val button = tableRow.getChildAt(j) as Button
                    button.setOnClickListener { onButtonClicked(it) }
                    add(button)
                }
            }
        }
        checkedButtons = buttons
        if(savedInstanceState == null) {
            initGame()
        }
        return view
    }
    private fun initGame() {
        numbers.clear()
        selectedButtons.clear()
        for (i in 1..10) {
            numbers.add(i)
            numbers.add(i)
        }
        numbers.shuffle()
        for (button in buttons) {
            button.text = ""
            button.isEnabled = true
        }
    }
    private fun onButtonClicked(view: View) {
        val button = view as Button

        if (selectedButtons.size < 2 && button.text.isEmpty()) {
            button.text = numbers[buttons.indexOf(button)].toString()
            selectedButtons.add(button)
            if (selectedButtons.size == 2) {
                checkSelectedButtons()
            }
        }
    }
    @SuppressLint("ResourceAsColor")
    private fun checkSelectedButtons() {
        val firstButton = selectedButtons[0]
        val secondButton = selectedButtons[1]
        if (firstButton.text == secondButton.text) {
            firstButton.isActivated = true
            secondButton.isActivated = true
            disableButtons(firstButton, secondButton)
            selectedButtons.clear()
        }
        else {
            disableButtons(firstButton, secondButton)
            object : Thread() {
                override fun run() {
                    try {
                        sleep(1000)
                        activity?.runOnUiThread() {
                            firstButton.text = ""
                            secondButton.text = ""
                            selectedButtons.clear()
                            enableButtons(firstButton, secondButton)
                        }
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                }
            }.start()
        }
    }
    private fun disableButtons(b1 : Button, b2 : Button) {
        b1.isEnabled = false
        b2.isEnabled = false
    }
    private fun enableButtons(b1 : Button, b2 : Button) {
        b1.isEnabled = true
        b2.isEnabled = true
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putIntArray("n",numbers.toIntArray())
    }
    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        val n = savedInstanceState?.getIntArray("n")
        if (n != null) {
            for(a in n){
                numbers.add(a)
            }
        }
    }
}