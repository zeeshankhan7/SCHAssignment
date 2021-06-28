package com.zak.datauses.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import com.zak.datauses.R
import com.zak.datauses.adapters.MobileDataAdapter.MobileDataViewHolder
import com.zak.datauses.data.model.Quarter
import com.zak.datauses.data.model.Year
import com.zak.datauses.utilities.AnimationUtils.collapse
import com.zak.datauses.utilities.AnimationUtils.expand
import java.util.*

class MobileDataAdapter(private val context: Context) : RecyclerView.Adapter<MobileDataViewHolder>() {
    private var yearList: List<Year> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MobileDataViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.mobile_data_item, parent, false)
        return MobileDataViewHolder(itemView)
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onBindViewHolder(holder: MobileDataViewHolder, position: Int) {
        val year = yearList[position]
        holder.textViewYear!!.text = year.year.toString()
        if (year.isYearCompleted) holder.textViewUsage!!.text = year.totalUsage.toString() else holder.textViewUsage!!.text = year.totalUsage.toString() + "*"
        if (!year.isDecreasedGrowth) holder.imageButtonDetailedUsage!!.visibility = View.GONE else {
            holder.imageButtonDetailedUsage!!.visibility = View.VISIBLE
            holder.imageButtonDetailedUsage!!.setOnClickListener {
                if (holder.linearLayoutQuarterDetailsContainer!!.visibility == View.VISIBLE) {
                    collapse(holder.linearLayoutQuarterDetailsContainer!!)
                } else {
                    expand(holder.linearLayoutQuarterDetailsContainer!!)
                }
            }
        }
        initQuarterDetails(holder, year.quarters)
    }

    fun initQuarterDetails(holder: MobileDataViewHolder, quarters: List<Quarter>?) {
        if (quarters!!.size >= 1) {
            holder.textViewQuarter1Value!!.text = quarters[0].usage.toString()
            if (quarters[0].usageGrowth!! >= 0) {
                holder.textViewQuarter1!!.setTextColor(context.resources.getColor(R.color.quarter_status_increase_color))
                holder.textViewQuarter1Value!!.setTextColor(context.resources.getColor(R.color.quarter_status_increase_color))
            } else {
                holder.textViewQuarter1!!.setTextColor(context.resources.getColor(R.color.quarter_status_decrease_color))
                holder.textViewQuarter1Value!!.setTextColor(context.resources.getColor(R.color.quarter_status_decrease_color))
            }
        } else {
            holder.textViewQuarter1!!.visibility = View.GONE
            holder.textViewQuarter1Value!!.visibility = View.GONE
        }
        if (quarters.size >= 2) {
            holder.textViewQuarter2Value!!.text = quarters[1].usage.toString()
            if (quarters[1].usageGrowth!! >= 0) {
                holder.textViewQuarter2!!.setTextColor(context.resources.getColor(R.color.quarter_status_increase_color))
                holder.textViewQuarter2Value!!.setTextColor(context.resources.getColor(R.color.quarter_status_increase_color))
            } else {
                holder.textViewQuarter2!!.setTextColor(context.resources.getColor(R.color.quarter_status_decrease_color))
                holder.textViewQuarter2Value!!.setTextColor(context.resources.getColor(R.color.quarter_status_decrease_color))
            }
        } else {
            holder.textViewQuarter2!!.visibility = View.GONE
            holder.textViewQuarter2Value!!.visibility = View.GONE
        }
        if (quarters.size >= 3) {
            holder.textViewQuarter3Value!!.text = quarters[2].usage.toString()
            if (quarters[2].usageGrowth!! >= 0) {
                holder.textViewQuarter3!!.setTextColor(context.resources.getColor(R.color.quarter_status_increase_color))
                holder.textViewQuarter3Value!!.setTextColor(context.resources.getColor(R.color.quarter_status_increase_color))
            } else {
                holder.textViewQuarter3!!.setTextColor(context.resources.getColor(R.color.quarter_status_decrease_color))
                holder.textViewQuarter3Value!!.setTextColor(context.resources.getColor(R.color.quarter_status_decrease_color))
            }
        } else {
            holder.textViewQuarter3!!.visibility = View.GONE
            holder.textViewQuarter3Value!!.visibility = View.GONE
        }
        if (quarters.size >= 4) {
            holder.textViewQuarter4Value!!.text = quarters[3].usage.toString()
            if (quarters[3].usageGrowth!! >= 0) {
                holder.textViewQuarter4!!.setTextColor(context.resources.getColor(R.color.quarter_status_increase_color))
                holder.textViewQuarter4Value!!.setTextColor(context.resources.getColor(R.color.quarter_status_increase_color))
            } else {
                holder.textViewQuarter4!!.setTextColor(context.resources.getColor(R.color.quarter_status_decrease_color))
                holder.textViewQuarter4Value!!.setTextColor(context.resources.getColor(R.color.quarter_status_decrease_color))
            }
        } else {
            holder.textViewQuarter4!!.visibility = View.GONE
            holder.textViewQuarter4Value!!.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int {
        return yearList.size
    }

    fun setYearList(yearList: List<Year>) {
        this.yearList = yearList
    }

    inner class MobileDataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @BindView(R.id.tvYear)
        var textViewYear: TextView? = null

        @BindView(R.id.tvUsage)
        var textViewUsage: TextView? = null

        @BindView(R.id.iBtnDetailedUsage)
        var imageButtonDetailedUsage: ImageButton? = null

        @BindView(R.id.llQuarterDetailsContainer)
        var linearLayoutQuarterDetailsContainer: LinearLayout? = null

        @BindView(R.id.tvQuarter1)
        var textViewQuarter1: TextView? = null

        @BindView(R.id.tvQuarter1Value)
        var textViewQuarter1Value: TextView? = null

        @BindView(R.id.tvQuarter2)
        var textViewQuarter2: TextView? = null

        @BindView(R.id.tvQuarter2Value)
        var textViewQuarter2Value: TextView? = null

        @BindView(R.id.tvQuarter3)
        var textViewQuarter3: TextView? = null

        @BindView(R.id.tvQuarter3Value)
        var textViewQuarter3Value: TextView? = null

        @BindView(R.id.tvQuarter4)
        var textViewQuarter4: TextView? = null

        @BindView(R.id.tvQuarter4Value)
        var textViewQuarter4Value: TextView? = null

        init {
            textViewYear=itemView.findViewById(R.id.tvYear)
            textViewUsage=itemView.findViewById(R.id.tvUsage)
            imageButtonDetailedUsage=itemView.findViewById(R.id.iBtnDetailedUsage)
            linearLayoutQuarterDetailsContainer=itemView.findViewById(R.id.llQuarterDetailsContainer)
            textViewQuarter1=itemView.findViewById(R.id.tvQuarter1)
            textViewQuarter1Value=itemView.findViewById(R.id.tvQuarter1Value)
            textViewQuarter2=itemView.findViewById(R.id.tvQuarter2)
            textViewQuarter2Value=itemView.findViewById(R.id.tvQuarter2Value)
            textViewQuarter3=itemView.findViewById(R.id.tvQuarter3)
            textViewQuarter3Value=itemView.findViewById(R.id.tvQuarter3Value)
            textViewQuarter4=itemView.findViewById(R.id.tvQuarter4)
            textViewQuarter4Value=itemView.findViewById(R.id.tvQuarter4Value)
        }
    }
}