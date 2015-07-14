package me.sargunvohra.android.purduediningcourts.service;

import android.content.Context;

import java.util.Calendar;
import java.util.List;

import me.sargunvohra.android.purduediningcourts.R;
import me.sargunvohra.android.purduediningcourts.model.Day;
import me.sargunvohra.android.purduediningcourts.page.location.LocationClosedError;
import retrofit.RetrofitError;
import timber.log.Timber;

public class DiningServiceHelper {

    public static String getEndpoint() {
        return "http://api.hfs.purdue.edu/menus/v2";
    }

    public static String getFileUrl(String fileId) {
        return String.format("%s/file/%s", getEndpoint(), fileId);
    }

    public static String getErrorMessage(Context ctx, Throwable e) {
        if (e instanceof RetrofitError) {
            RetrofitError.Kind kind = ((RetrofitError) e).getKind();
            switch (kind) {
                case NETWORK:
                    return ctx.getString(R.string.network_error);
                case CONVERSION:
                    return ctx.getString(R.string.conversion_error);
                case HTTP:
                    return ctx.getString(R.string.http_error);
                case UNEXPECTED:
                    break;
                default:
                    Timber.wtf("Encountered unknown error type: %s", kind);
            }
        } else if (e instanceof LocationClosedError) {
            return ctx.getString(R.string.closed_error);
        }
        return ctx.getString(R.string.unexpected_error);
    }

    public static <D extends Day> D getToday(List<D> days) {
        Calendar c = Calendar.getInstance();
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        for (D day : days) {
            if (dayOfWeek == day.getDayOfWeek())
                return day;
        }
        return null;
    }
}
