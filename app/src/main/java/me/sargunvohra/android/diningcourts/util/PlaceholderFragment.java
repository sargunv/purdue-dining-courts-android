package me.sargunvohra.android.diningcourts.util;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hannesdorfmann.fragmentargs.FragmentArgs;
import com.hannesdorfmann.fragmentargs.annotation.Arg;
import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs;

import me.sargunvohra.android.diningcourts.R;

@FragmentWithArgs
public class PlaceholderFragment extends Fragment {

    @Arg
    String text;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        FragmentArgs.inject(this);
        View v = inflater.inflate(R.layout.fragment_placeholder, container, false);
        ((TextView) v.findViewById(R.id.text)).setText(text);
        return v;
    }
}
