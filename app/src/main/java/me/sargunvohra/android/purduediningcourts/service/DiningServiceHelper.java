package me.sargunvohra.android.purduediningcourts.service;

public class DiningServiceHelper {

    public static String getEndpoint() {
        return "http://api.hfs.purdue.edu/menus/v2";
    }

    public static String getFileUrl(String fileId) {
        return String.format("%s/file/%s", getEndpoint(), fileId);
    }
}
