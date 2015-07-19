package me.sargunvohra.android.purduediningcourts.page.location.dining;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import me.sargunvohra.android.purduediningcourts.R;
import me.sargunvohra.android.purduediningcourts.base.BaseListAdapter;
import me.sargunvohra.android.purduediningcourts.model.dining.item.Item;

public class DiningMenuListAdapter extends BaseListAdapter<Item> {

    public DiningMenuListAdapter(List<Item> dataSet, OnClickListener<Item> onClickListener) {
        super(dataSet, onClickListener);
    }

    public static class ViewHolder extends BaseListAdapter.ViewHolder {

        @InjectView(R.id.text)
        TextView text;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_single_line, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        if (holder instanceof ViewHolder) {
            ViewHolder vh = (ViewHolder) holder;
            Resources res = vh.parentView.getContext().getResources();
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(vh.parentView.getContext());
            Item i = dataSet.get(position);
            vh.text.setText(i.getName());
            if (i.getIsVegetarian() && prefs.getBoolean("pref_isVegetarian", false)) {
                vh.parentView.setBackgroundColor(res.getColor(R.color.vegetarian_bg));
            } else {
                vh.parentView.setBackgroundColor(res.getColor(R.color.window_background));
            }
        }
    }
}
