package me.sargunvohra.android.purduediningcourts.page.location;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

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
            boolean isTwentyFourHourFormat = DateFormat.is24HourFormat(vh.parentView.getContext());
            String status = loc.getTimings(isTwentyFourHourFormat);
            if (status != null && !loc.getTimings(isTwentyFourHourFormat).equals("Closed")) {
                vh.status.setText(Html.fromHtml(status));
                vh.status.setTextColor(vh.status.getResources().getColor(R.color.secondary_text));
            } else {
                vh.status.setText(R.string.closed);
                vh.status.setTextColor(vh.status.getResources().getColor(R.color.closed));
            }

            // set card image
            Glide.with(vh.image.getContext())
                    .load(DiningServiceHelper.getFileUrl(loc.getTileImage()))
                    .centerCrop()
                    .placeholder(R.drawable.placeholder)
                    .crossFade()
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