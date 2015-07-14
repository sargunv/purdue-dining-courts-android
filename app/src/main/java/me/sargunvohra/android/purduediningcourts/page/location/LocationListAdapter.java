package me.sargunvohra.android.purduediningcourts.page.location;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.InjectView;
import me.sargunvohra.android.purduediningcourts.R;
import me.sargunvohra.android.purduediningcourts.base.BaseListAdapter;
import me.sargunvohra.android.purduediningcourts.model.Location;
import me.sargunvohra.android.purduediningcourts.service.DiningServiceHelper;

public class LocationListAdapter<ListItem extends Location> extends BaseListAdapter<ListItem> {

    public LocationListAdapter(List<ListItem> dataSet, OnClickListener<ListItem> onClickListener) {
        super(dataSet, onClickListener);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_location, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        super.onBindViewHolder(holder, position);

        if (holder instanceof ViewHolder) {
            ViewHolder vh = (ViewHolder) holder;

            Location loc = dataSet.get(position);

            vh.parentView.setId(loc.hashCode());

            // set card title
            vh.title.setText(loc.getFullName());

            // set card status
            String status = loc.getCurrentStatus();
            if (status != null && !loc.getCurrentStatus().equals("Closed")) {
                vh.status.setText(R.string.open);
                vh.status.setTextColor(vh.status.getResources().getColor(R.color.open));
            } else {
                vh.status.setText(R.string.closed);
                vh.status.setTextColor(vh.status.getResources().getColor(R.color.closed));
            }

            // set card image
            Picasso.with(vh.image.getContext())
                    .load(DiningServiceHelper.getFileUrl(loc.getTileImage()))
                    .placeholder(R.drawable.placeholder)
                    .resize(500, 400)
                    .centerCrop()
                    .into(vh.image);
        }
    }

    public static class ViewHolder extends BaseListAdapter.ViewHolder {
        @InjectView(R.id.title)
        public TextView title;

        @InjectView(R.id.status)
        public TextView status;

        @InjectView(R.id.image)
        public ImageView image;

        public ViewHolder(View parentView) {
            super(parentView);
        }
    }

}
