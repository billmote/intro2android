package com.androidfu.intro2android;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.androidfu.intro2android.model.CurrentConditions;
import com.google.gson.Gson;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private TextView temperature;
    private TextView location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        temperature = (TextView) findViewById(R.id.tv_temperature);
        location = (TextView) findViewById(R.id.tv_location);
        Button update_temperature = (Button) findViewById(R.id.btn_update_temperature);
        update_temperature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetCurrentConditionsTask getCurrentConditionsTask = new GetCurrentConditionsTask();
                getCurrentConditionsTask.execute();
            }
        });
    }

    private class GetCurrentConditionsTask extends AsyncTask<Void, Void, CurrentConditions> {

        private String url = "http://api.wunderground.com/api/{APIKEY}/conditions/q/SC/Charleston.json";

        @Override
        protected void onPostExecute(CurrentConditions currentConditions) {
            super.onPostExecute(currentConditions);
            if (temperature == null || location == null) {
                return;
            }
            if (currentConditions != null) {
                temperature.setText(currentConditions.getCurrentObservation().getTemperatureString());
                location.setText(currentConditions.getCurrentObservation().getDisplayLocation().getFull());
            } else {
                temperature.setText(getString(R.string.error_something_went_wrong));
            }
        }

        @Override
        protected CurrentConditions doInBackground(Void... params) {
            if (getString(R.string.wu_api_key).equals("REPLACE_WITH_YOUR_WEATHER_UNDERGROUND_API_KEY")) {
                throw new RuntimeException("You must provide a WeatherUnderground API Key.");
            }

            InputStream source = retrieveStream(
                    url.replace("{APIKEY}",
                     /* you need to add an API key in strings */ getString(R.string.wu_api_key))
            );
            CurrentConditions response = null;
            if (source != null) {
                Gson gson = new Gson();
                Reader reader = new InputStreamReader(source);
                try {
                    response = gson.fromJson(reader, CurrentConditions.class);
                    reader.close();
                } catch (Exception e) {
                    Log.w(TAG, String.format("Error: %1$s for URL %2$s", e.getMessage(), url));
                }
            }
            if (!this.isCancelled()) {
                return response;
            } else {
                return null;
            }
        }

        private InputStream retrieveStream(String url) {
            DefaultHttpClient client = new DefaultHttpClient();
            HttpGet getRequest;
            try {
                getRequest = new HttpGet(url);
                HttpResponse getResponse = client.execute(getRequest);
                HttpEntity getResponseEntity = getResponse.getEntity();
                return getResponseEntity.getContent();
            } catch (Exception e) {
                Log.w(TAG, String.format("Error for URL %s in retrieveStream(): %s", url, e.getMessage()), e);
                return null;
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
