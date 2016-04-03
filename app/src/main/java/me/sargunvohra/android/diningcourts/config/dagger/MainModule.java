package me.sargunvohra.android.diningcourts.config.dagger;

import dagger.Module;
import me.sargunvohra.android.diningcourts.main.MainActivity;

@Module(
        library = true,
        complete = false,
        injects = {MainActivity.class}
)
public class MainModule {
}