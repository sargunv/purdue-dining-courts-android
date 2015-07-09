package me.sargunvohra.android.purduediningcourts.service;


import me.sargunvohra.android.purduediningcourts.model.dining.DayMenu;
import me.sargunvohra.android.purduediningcourts.model.dining.DiningLocations;
import me.sargunvohra.android.purduediningcourts.model.dining.SearchResults;
import me.sargunvohra.android.purduediningcourts.model.dining.item.Item;
import me.sargunvohra.android.purduediningcourts.model.dining.item.ItemSchedule;
import me.sargunvohra.android.purduediningcourts.model.retail.RetailLocations;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

public interface DiningService {

    @GET("/locations/")
    void getDiningLocations(Callback<DiningLocations> callback);

    @GET("/retail/")
    void getRetailLocations(Callback<RetailLocations> callback);

    @GET("/locations/{location}/{date}/")
    void getDiningMenu(@Path("location") String location, @Path("date") String date, Callback<DayMenu> callback);

    @GET("/items/search/{terms}/")
    void searchItems(@Path("terms") String searchTerms, Callback<SearchResults> callback);

    @GET("/items/searchUpcoming/{terms}/")
    void searchUpcomingItems(@Path("terms") String searchTerms, Callback<SearchResults> callback);

    @GET("/items/{guid}/")
    void getItem(@Path("guid") String guid, Callback<Item> callback);

    @GET("/items/{guid}/schedule/")
    void getItemSchedule(@Path("guid") String guid, Callback<ItemSchedule> callback);
}
