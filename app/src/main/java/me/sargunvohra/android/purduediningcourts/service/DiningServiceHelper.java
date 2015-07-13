package me.sargunvohra.android.purduediningcourts.service;

import android.content.Context;

import me.sargunvohra.android.purduediningcourts.R;
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
        }
        return ctx.getString(R.string.unexpected_error);
    }
}
