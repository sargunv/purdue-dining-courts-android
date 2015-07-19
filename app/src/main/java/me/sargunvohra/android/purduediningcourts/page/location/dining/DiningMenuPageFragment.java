package me.sargunvohra.android.purduediningcourts.page.location.dining;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hannesdorfmann.fragmentargs.annotation.Arg;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import me.sargunvohra.android.purduediningcourts.R;
import me.sargunvohra.android.purduediningcourts.SectionedRecyclerViewAdapter;
import me.sargunvohra.android.purduediningcourts.base.BaseFragment;
import me.sargunvohra.android.purduediningcourts.base.BaseListAdapter;
import me.sargunvohra.android.purduediningcourts.model.dining.Meal;
import me.sargunvohra.android.purduediningcourts.model.dining.Station;
import me.sargunvohra.android.purduediningcourts.model.dining.item.Item;

import static me.sargunvohra.android.purduediningcourts.SectionedRecyclerViewAdapter.Section;

public class DiningMenuPageFragment extends BaseFragment implements BaseListAdapter.OnClickListener<Item> {

    @Arg
    Meal meal;

    @InjectView(R.id.recyclerView)
    RecyclerView recyclerView;

    private DiningMenuListAdapter adapter;
    private SectionedRecyclerViewAdapter sectionAdapter;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Context ctx = getActivity();

        List<Station> stations = meal.getStations();
        List<Item> items = new ArrayList<>();
        List<Section> sections = new ArrayList<>();

        assert stations != null;

        int count = 0;
        for (Station s : stations) {
            sections.add(new Section(count, s.getName()));
            for (Item i : s.getItems()) {
                ++count;
                items.add(i);
            }
        }

        adapter = new DiningMenuListAdapter(items, this);
        sectionAdapter = new SectionedRecyclerViewAdapter(ctx, R.layout.section, R.id.section_title, adapter);
        sectionAdapter.setSections(sections.toArray(new Section[sections.size()]));

        recyclerView.setAdapter(sectionAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ctx));
    }

    public int getLayoutRes() {
        return R.layout.fragment_dining_menu;
    }

    @Override
    public void onClick(Item item) {
        startActivity(new DiningItemActivityIntentBuilder(item.getID()).build(getActivity()));
    }
}
