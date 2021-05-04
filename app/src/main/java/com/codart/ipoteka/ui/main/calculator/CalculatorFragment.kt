package com.codart.ipoteka.ui.main.calculator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.Group
import androidx.core.widget.doAfterTextChanged
import androidx.navigation.fragment.findNavController
import com.codart.ipoteka.R
import com.codart.ipoteka.ui.main.MainViewModel
import kotlin.math.floor


class CalculatorFragment : Fragment() {
    lateinit var seekBarSum: SeekBar
    lateinit var seekBarMonths: SeekBar
    lateinit var editSum: EditText
    lateinit var editMonths: EditText
    lateinit var txtPayment: TextView
    lateinit var txtTile: TextView
    lateinit var radioProgram1: RadioButton
    lateinit var radioProgram2: RadioButton
    lateinit var group: Group
    lateinit var back: ImageView
    /*lateinit var grn: RadioButton
    lateinit var usd: RadioButton*/
    var PROCENT=1.0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calculator, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        seekBarMonths = requireView().findViewById(R.id.calculator_seek_months)
        seekBarSum = requireView().findViewById(R.id.calculator_seek_sum)
        editSum = requireView().findViewById(R.id.calculator_edit_sum)
        editMonths = requireView().findViewById(R.id.calculator_edit_months)
        txtPayment = requireView().findViewById(R.id.calculator_txt_payment)
        txtTile = requireView().findViewById(R.id.txt_calculator_title)
        radioProgram1 = requireView().findViewById(R.id.calculator_program_1)
        radioProgram2 = requireView().findViewById(R.id.calculator_program_2)
        group = requireView().findViewById(R.id.group_calculator_program)
        back = requireView().findViewById(R.id.img_calculator_back)
        back.setOnClickListener {
            findNavController().navigateUp()
        }
       /* grn = requireView().findViewById(R.id.calculator_grn)
        usd = requireView().findViewById(R.id.calculator_usd)*/

        setContinue()
        setSeekBars()
        setEditTexts()
        setTexts()
        super.onViewCreated(view, savedInstanceState)
    }
    private fun setTexts(){
        when(MainViewModel.calculatorId)
        {
            3-> {
                PROCENT = 1.02
            }
            2->{
                radioProgram1.text = "Квартира"
                radioProgram2.text = "Участок"
                txtTile.text = "Под залог квартиры"
                PROCENT = 1.015
            }
            1->{
                group.visibility = View.GONE
                txtTile.text = "Кредит морякам"
                PROCENT = 1.03
            }
        }
    }
    private fun setContinue()
    {
        requireView().findViewById<AppCompatButton>(R.id.btn_calculator_continue).setOnClickListener {
            findNavController().navigate(R.id.action_calculatorFragment_to_inputFragment)
        }
    }
    private fun setSeekBars(){
       /* grn.setOnClickListener {
            seekBarSum.max= if(grn.isChecked)
                500000
            else
                100000
        }
        usd.setOnClickListener {
            seekBarSum.max= if(grn.isChecked)
                500000
            else
                100000
        }
        seekBarSum.max= if(grn.isChecked)
            500000
        else
            100000*/
        seekBarSum.max= 500000
        seekBarMonths.max=50
        seekBarSum.setOnSeekBarChangeListener(object :
                SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar,
                                           progress: Int, fromUser: Boolean) {
                // write custom code for progress is changed
                editSum.setText(progress.toString())
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
               editSum.setText(seekBar!!.progress.toString())
            }
        })
        seekBarMonths.setOnSeekBarChangeListener(object :
                SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar,
                                           progress: Int, fromUser: Boolean) {
                // write custom code for progress is changed
                editMonths.setText(progress.toString())
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                editMonths.setText(seekBar!!.progress.toString())
            }
        })
    }
    private fun setEditTexts(){
        editSum.doAfterTextChanged {
            calculate()
        }
        editMonths.doAfterTextChanged {
            calculate()
        }
    }
    private fun calculate(){
        if(editMonths.text.isNotEmpty() && editSum.text.isNotEmpty()){
            txtPayment.text=round(editSum.text.toString().toDouble()*PROCENT/editMonths.text.toString().toDouble()).toString()
        }
    }
    private fun round(v: Double): Any{
        val res=floor(v*1000.0) /1000.0
        return if(isWhole(res))
            res.toInt()
        else
            res

    }
    private fun isWhole(value: Double):Boolean {
        return value - value.toInt() == 0.0
    }

}