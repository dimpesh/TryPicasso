package com.movie.app.trypicasso;

import com.movie.app.trypicasso.R;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import com.movie.app.trypicasso.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ThreadTask extends AsyncTask<Void , Void ,JSONArray>
{
    JSONArray jsonArray;

/*
   @Override
    protected JSONArray onPostExecute(ArrayList<String> arrayList) {
            if(arrayList!=null)
            {
                ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>();
            }
    }

*/
    public String [] str;
    @Override
    protected JSONArray doInBackground(Void... voids) {

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String forecastJSONStr = null;
        try {
            URL url = new URL("http://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=69323240f26aaa3f0ed513e2fd344a5f");

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                forecastJSONStr = null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }
            if (buffer.length() == 0) {
                forecastJSONStr = null;
            }
            forecastJSONStr = buffer.toString();
           // Log.v("MY JSON OUTPUT", forecastJSONStr);
            String title="title";
            String vote_average="vote_avergae";
            String overview="overview";
            JSONObject forcastObject=new JSONObject(forecastJSONStr);
            jsonArray=forcastObject.optJSONArray("results");
            ArrayList <String>str=new ArrayList<String>();
            for(int i=0;i<jsonArray.length();i++)
            {
                JSONObject jsonObject=jsonArray.getJSONObject(i);
                String Jtitle=jsonObject.optString("title").toString();
                String JOverview=jsonObject.optString("overview").toString();
                String JPosterPath=jsonObject.optString("poster_path").toString();
                String JPoster=jsonObject.getString("poster_path").toString();
                str.add(JPosterPath);
            }

            for(String s : str)
            {
                Log.v("MY POSTER",s+"\n");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonArray;
    }
}


