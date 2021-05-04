package com.codart.ipoteka.ui.main.profile

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.codart.ipoteka.R
import com.codart.ipoteka.data.entities.CalendarPay

class ProfileAdapter(val context: Context) : RecyclerView.Adapter<ProfileViewHolder>() {

    private val items = arrayListOf<CalendarPay>()

    fun setItems(items: List<CalendarPay>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ProfileViewHolder {
        return ProfileViewHolder(itemView = LayoutInflater.from(viewGroup.context).inflate(
                R.layout.cell_profile_item,
                viewGroup,
                false
        ),context)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int)
    {
        holder.setIsRecyclable(false)
        holder.bind(items[position])
    }
}

class ProfileViewHolder(itemView: View,val context: Context) : RecyclerView.ViewHolder(
        itemView){

    private lateinit var item: CalendarPay
    val txtSum: TextView = itemView.findViewById(R.id.txt_cell_profile_sum)
    val txtPenya: TextView = itemView.findViewById(R.id.txt_cell_profile_penya)
    val txtDateTransaction: TextView = itemView.findViewById(R.id.txt_cell_profile_date_transaction)
    val txtDate: TextView = itemView.findViewById(R.id.txt_cell_profile_date)
    val layout: ConstraintLayout = itemView.findViewById(R.id.layout_cell_item)

    fun bind(item: CalendarPay) {
        this.item = item
        txtSum.text = item.amount+" $"
        txtPenya.text = item.peni+" $"
        txtDateTransaction.text = item.status_name
        txtDate.text=item.date_pay

        if(item.status_id=="0")
        {
            Log.d("Adapter status name", item.status_name)
            Log.d("Adapter ", (item.status_name=="Не оплачен").toString())
            layout.background = context.getDrawable(R.drawable.background_unpaid)
            txtDate.setTextColor(Color.parseColor("#EF3A5B"))
        }
        else
        {

            Log.d("Adapter paid", "true")
        }
    }

}
