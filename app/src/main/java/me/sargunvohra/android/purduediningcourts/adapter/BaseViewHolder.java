package me.sargunvohra.android.purduediningcourts.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

public class BaseViewHolder extends RecyclerView.ViewHolder {
    public View parentView;

    public BaseViewHolder(View parentView) {
        super(parentView);
        this.parentView = parentView;
        ButterKnife.inject(this, parentView);
    }
}
