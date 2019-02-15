package com.channa.mobiledatausageapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.channa.mobiledatausageapp.R;
import com.channa.mobiledatausageapp.model.Quarter;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MobileDataAdapter extends RecyclerView.Adapter<MobileDataAdapter.MobileDataViewHolder> {

    private List<Quarter> quarterList;

    @NonNull
    @Override
    public MobileDataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_mobile_data, parent, false);

        return new MobileDataViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MobileDataViewHolder holder, int position) {
        Quarter quarter = quarterList.get(position);
        holder.textViewYear.setText(String.valueOf(quarter.getYear()));
        holder.textViewUsage.setText(String.valueOf(quarter.getUsage()));
    }

    @Override
    public int getItemCount() {
        return quarterList.size();
    }

    public void setQuarterList(List<Quarter> quarterList) {
        this.quarterList = quarterList;
    }

    public class MobileDataViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvYear)
        TextView textViewYear;

        @BindView(R.id.tvUsage)
        TextView textViewUsage;

        public MobileDataViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
