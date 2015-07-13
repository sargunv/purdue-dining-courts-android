package me.sargunvohra.android.purduediningcourts.page.location.dining;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.hannesdorfmann.fragmentargs.annotation.FragmentArgsInherited;

import butterknife.InjectView;
import me.sargunvohra.android.purduediningcourts.R;
import me.sargunvohra.android.purduediningcourts.base.BaseFragment;

@FragmentArgsInherited
public class DiningMenuPageFragment extends BaseFragment {

    @InjectView(R.id.temporary)
    TextView temp;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public int getLayoutRes() {
        return R.layout.fragment_dining_menu;
    }
}
