package me.sargunvohra.android.purduediningcourts.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import butterknife.ButterKnife;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor(suppressConstructorProperties = true)
public abstract class BaseListAdapter<ListItem> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    @Getter
    @Setter
    protected List<ListItem> dataSet;

    @Getter
    @Setter
    protected OnClickListener<ListItem> onClickListener;

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        // activate click
        if (holder instanceof ViewHolder) {
            ((ViewHolder)holder).parentView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickListener.onClick(dataSet.get(position));
                }
            });
        }
    }

    public interface OnClickListener<T> {
        void onClick(T location);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View parentView;

        public ViewHolder(View parentView) {
            super(parentView);
            this.parentView = parentView;
            ButterKnife.inject(this, parentView);
        }
    }
}
