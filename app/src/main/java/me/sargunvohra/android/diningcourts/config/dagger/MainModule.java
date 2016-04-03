package me.sargunvohra.android.diningcourts.config.dagger;

import dagger.Module;
import me.sargunvohra.android.diningcourts.main.MainActivity;
import me.sargunvohra.android.diningcourts.menu.DiningMenuFragment;

@Module(
        library = true,
        complete = false,
        injects = {MainActivity.class, DiningMenuFragment.class}
)
public class MainModule {
}