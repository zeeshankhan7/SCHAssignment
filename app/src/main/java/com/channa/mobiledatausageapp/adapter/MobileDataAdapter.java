package com.channa.mobiledatausageapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.channa.mobiledatausageapp.R;
import com.channa.mobiledatausageapp.data.model.Year;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MobileDataAdapter extends RecyclerView.Adapter<MobileDataAdapter.MobileDataViewHolder> {

    private List<Year> yearList = new ArrayList<>();

    @NonNull
    @Override
    public MobileDataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_mobile_data, parent, false);

        return new MobileDataViewHolder(itemView);
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
        else
            holder.imageButtonDetailedUsage.setVisibility(View.VISIBLE);

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

        public MobileDataViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
