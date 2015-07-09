package me.sargunvohra.android.purduediningcourts;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.ObjectGraph;
import dagger.Provides;
import me.sargunvohra.android.purduediningcourts.activity.MainActivity;
import me.sargunvohra.android.purduediningcourts.service.DiningService;
import retrofit.RestAdapter;
import retrofit.converter.Converter;
import retrofit.converter.GsonConverter;

@Module(
        injects = {
                MainActivity.class
        }
)
public class DaggerModule {

    private static ObjectGraph graph;

    @Provides @Singleton
    Bus provideBus() {
        return new Bus();
    }

    @Provides @Singleton
    Gson provideGson() {
        return new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
    }

    @Provides @Singleton
    Converter provideConverter(Gson gson) {
        return new GsonConverter(gson);
    }

    @Provides @Singleton
    RestAdapter provideRestAdapter(Converter converter) {
        return new RestAdapter.Builder()
                .setEndpoint("http://api.hfs.purdue.edu/menus/v2/")
                .setLogLevel(RestAdapter.LogLevel.NONE)
                .setConverter(converter)
                .build();
    }

    @Provides @Singleton
    DiningService provideDiningService(RestAdapter adapter) {
        return adapter.create(DiningService.class);
    }

    public static ObjectGraph getObjectGraph() {
        if (graph == null) {
            graph = ObjectGraph.create(new DaggerModule());
        }
        return graph;
    }
}