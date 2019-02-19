package com.channa.mobiledatausageapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.channa.mobiledatausageapp.R;
import com.channa.mobiledatausageapp.data.model.Quarter;
import com.channa.mobiledatausageapp.data.model.Year;
import com.channa.mobiledatausageapp.utility.AnimationUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MobileDataAdapter extends RecyclerView.Adapter<MobileDataAdapter.MobileDataViewHolder> {

    private Context context;
    private List<Year> yearList = new ArrayList<>();

    public MobileDataAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MobileDataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_mobile_data, parent, false);

        return new MobileDataViewHolder(itemView);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(@NonNull MobileDataViewHolder holder, int position) {

        Year year = yearList.get(position);

        holder.textViewYear.setText(String.valueOf(year.getYear()));

        if (year.isYearCompleted())
            holder.textViewUsage.setText(String.valueOf(year.getTotalUsage()));
        else
            holder.textViewUsage.setText(String.valueOf(year.getTotalUsage()) + "*");

        if (!year.isDecreasedGrowth())
            holder.imageButtonDetailedUsage.setVisibility(View.GONE);
        else {
            holder.imageButtonDetailedUsage.setVisibility(View.VISIBLE);
            holder.imageButtonDetailedUsage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (holder.linearLayoutQuarterDetailsContainer.getVisibility() == View.VISIBLE) {
                        AnimationUtils.collapse(holder.linearLayoutQuarterDetailsContainer);
                    } else {
                        AnimationUtils.expand(holder.linearLayoutQuarterDetailsContainer);
                    }
                }
            });
        }

        initQuarterDetails(holder, year.getQuarters());
    }

    public void initQuarterDetails(MobileDataViewHolder holder, List<Quarter> quarters) {

        if (quarters.size() >= 1) {
            holder.textViewQuarter1Value.setText(String.valueOf(quarters.get(0).getUsage()));

            if (quarters.get(0).getUsageGrowth() >= 0) {
                holder.textViewQuarter1.setTextColor(context.getResources().getColor(R.color.quarter_status_increase_color));
                holder.textViewQuarter1Value.setTextColor(context.getResources().getColor(R.color.quarter_status_increase_color));
            } else {
                holder.textViewQuarter1.setTextColor(context.getResources().getColor(R.color.quarter_status_decrease_color));
                holder.textViewQuarter1Value.setTextColor(context.getResources().getColor(R.color.quarter_status_decrease_color));
            }
        } else {
            holder.textViewQuarter1.setVisibility(View.GONE);
            holder.textViewQuarter1Value.setVisibility(View.GONE);
        }

        if (quarters.size() >= 2) {
            holder.textViewQuarter2Value.setText(String.valueOf(quarters.get(1).getUsage()));

            if (quarters.get(1).getUsageGrowth() >= 0) {
                holder.textViewQuarter2.setTextColor(context.getResources().getColor(R.color.quarter_status_increase_color));
                holder.textViewQuarter2Value.setTextColor(context.getResources().getColor(R.color.quarter_status_increase_color));
            } else {
                holder.textViewQuarter2.setTextColor(context.getResources().getColor(R.color.quarter_status_decrease_color));
                holder.textViewQuarter2Value.setTextColor(context.getResources().getColor(R.color.quarter_status_decrease_color));
            }
        } else {
            holder.textViewQuarter2.setVisibility(View.GONE);
            holder.textViewQuarter2Value.setVisibility(View.GONE);
        }

        if (quarters.size() >= 3) {
            holder.textViewQuarter3Value.setText(String.valueOf(quarters.get(2).getUsage()));

            if (quarters.get(2).getUsageGrowth() >= 0) {
                holder.textViewQuarter3.setTextColor(context.getResources().getColor(R.color.quarter_status_increase_color));
                holder.textViewQuarter3Value.setTextColor(context.getResources().getColor(R.color.quarter_status_increase_color));
            } else {
                holder.textViewQuarter3.setTextColor(context.getResources().getColor(R.color.quarter_status_decrease_color));
                holder.textViewQuarter3Value.setTextColor(context.getResources().getColor(R.color.quarter_status_decrease_color));
            }
        } else {
            holder.textViewQuarter3.setVisibility(View.GONE);
            holder.textViewQuarter3Value.setVisibility(View.GONE);
        }

        if (quarters.size() >= 4) {
            holder.textViewQuarter4Value.setText(String.valueOf(quarters.get(3).getUsage()));

            if (quarters.get(3).getUsageGrowth() >= 0) {
                holder.textViewQuarter4.setTextColor(context.getResources().getColor(R.color.quarter_status_increase_color));
                holder.textViewQuarter4Value.setTextColor(context.getResources().getColor(R.color.quarter_status_increase_color));
            } else {
                holder.textViewQuarter4.setTextColor(context.getResources().getColor(R.color.quarter_status_decrease_color));
                holder.textViewQuarter4Value.setTextColor(context.getResources().getColor(R.color.quarter_status_decrease_color));
            }
        } else {
            holder.textViewQuarter4.setVisibility(View.GONE);
            holder.textViewQuarter4Value.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return yearList.size();
    }

    public void setYearList(List<Year> yearList) {
        this.yearList = yearList;
    }

    public class MobileDataViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvYear)
        TextView textViewYear;

        @BindView(R.id.tvUsage)
        TextView textViewUsage;

        @BindView(R.id.iBtnDetailedUsage)
        ImageButton imageButtonDetailedUsage;

        @BindView(R.id.llQuarterDetailsContainer)
        LinearLayout linearLayoutQuarterDetailsContainer;

        @BindView(R.id.tvQuarter1)
        TextView textViewQuarter1;

        @BindView(R.id.tvQuarter1Value)
        TextView textViewQuarter1Value;

        @BindView(R.id.tvQuarter2)
        TextView textViewQuarter2;

        @BindView(R.id.tvQuarter2Value)
        TextView textViewQuarter2Value;

        @BindView(R.id.tvQuarter3)
        TextView textViewQuarter3;

        @BindView(R.id.tvQuarter3Value)
        TextView textViewQuarter3Value;

        @BindView(R.id.tvQuarter4)
        TextView textViewQuarter4;

        @BindView(R.id.tvQuarter4Value)
        TextView textViewQuarter4Value;

        public MobileDataViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
