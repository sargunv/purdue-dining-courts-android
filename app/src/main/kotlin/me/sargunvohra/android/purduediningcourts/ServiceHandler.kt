package me.sargunvohra.android.purduediningcourts

import android.os.AsyncTask
import android.util.Log
import com.github.kevinsawicki.http.HttpRequest
import org.json.simple.JSONObject
import org.json.simple.JSONValue
import org.json.simple.parser.JSONParser
import org.json.simple.parser.ParseException
import java.io.InputStream
import java.text.SimpleDateFormat
import java.util.*

public object ServiceHandler {

    public fun getLocationInfo(location: DiningLocation, date: Date, callback: (JSONObject) -> Unit) {
        val task = ServiceTask(callback)
        val locationArg = location.toString()
        val dateArg = SimpleDateFormat("MM-dd-yyyy").format(date)
        task.execute("http://api.hfs.purdue.edu/menus/v1/locations/${locationArg}/${dateArg}")
    }

    private class ServiceTask(callback: (JSONObject) -> Unit) : AsyncTask<String, String, JSONObject>() {
        val callback = callback

        override fun doInBackground(vararg params: String?): JSONObject? {
            try {
                val url = params[0]
                Log.i("ServiceHandler", "Requesting from ${url}...")
                val request = HttpRequest.get(url)
                if (request.ok()) {
                    Log.i("ServiceHandler", "Request succeeded from ${url}")
                    val data = JSONValue.parse(request.reader()) as JSONObject
                    Log.v("ServiceHandler", "From ${url} parsed response ${data}")
                    return data
                } else {
                    Log.e("ServiceHandler", "Request is not ok! Code ${request.code()}")
                    return null
                }
            } catch (e: HttpRequest.HttpRequestException) {
                Log.e("ServiceHandler", "Request failed!", e)
                return null
            } catch (e: ParseException) {
                Log.e("ServiceHandler", "Failed to parse response!", e)
                return null
            }
        }

        override fun onPostExecute(result: JSONObject?) {
            if (result != null)
                callback(result)
            else
                throw ServiceException()
        }
    }

    public class ServiceException : RuntimeException()
}