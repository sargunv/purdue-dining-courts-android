package me.sargunvohra.android.diningcourts.dagger;

import dagger.Module;
import me.sargunvohra.android.diningcourts.feature.main.MainActivity;
import me.sargunvohra.android.diningcourts.feature.menu.DiningMenuFragment;

@Module(
        library = true,
        complete = false,
        injects = {MainActivity.class, DiningMenuFragment.class}
)
public class MainModule {
}