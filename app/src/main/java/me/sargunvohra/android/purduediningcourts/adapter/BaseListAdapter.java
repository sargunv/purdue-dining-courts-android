package me.sargunvohra.android.purduediningcourts.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import butterknife.ButterKnife;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor(suppressConstructorProperties = true)
public abstract class BaseListAdapter<ListItem, VH extends BaseListAdapter.ViewHolder> extends RecyclerView.Adapter<VH> {

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View parentView;

        public ViewHolder(View parentView) {
            super(parentView);
            this.parentView = parentView;
            ButterKnife.inject(this, parentView);
        }
    }

    public interface OnClickListener<T> {
        void onClick(T location);
    }

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
    public void onBindViewHolder(VH holder, final int position) {
        // activate click
        holder.parentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickListener.onClick(dataSet.get(position));
            }
        });
    }
}
