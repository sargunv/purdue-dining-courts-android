package me.sargunvohra.android.purduediningcourts.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.InjectView;
import me.sargunvohra.android.purduediningcourts.R;
import me.sargunvohra.android.purduediningcourts.model.Location;
import me.sargunvohra.android.purduediningcourts.service.DiningServiceHelper;

public class LocationListAdapter<ListItem extends Location> extends BaseListAdapter<ListItem, LocationListAdapter.ViewHolder> {

    public LocationListAdapter(List<ListItem> dataSet, OnClickListener<ListItem> onClickListener) {
        super(dataSet, onClickListener);
    }

    public static class ViewHolder extends BaseListAdapter.ViewHolder {
        @InjectView(R.id.title)
        public TextView title;

        @InjectView(R.id.image)
        public ImageView image;

        public ViewHolder(View parentView) {
            super(parentView);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_location, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        super.onBindViewHolder(holder, position);

        Location loc = dataSet.get(position);

        holder.parentView.setId(loc.hashCode());

        // set card title
        holder.title.setText(loc.getFullName());

        // set card image
        Picasso.with(holder.image.getContext())
                .load(DiningServiceHelper.getFileUrl(loc.getTileImage()))
                .placeholder(R.drawable.placeholder)
                .resize(500, 400)
                .centerCrop()
                .into(holder.image);
    }

}
