package com.tp1.bousseaud.ppkas;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.tp1.bousseaud.ppkas.model.News;
import com.tp1.bousseaud.ppkas.utils.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String apiKey = Utils.readTextFileFromAssets("api_key_news_api.txt", this);

        OkHttpClient client = new OkHttpClient();

        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://newsapi.org/v1/articles").newBuilder();
        urlBuilder.addQueryParameter("source", "techcrunch");
        urlBuilder.addQueryParameter("apiKey", apiKey);
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder().url(url).build();

        // Retrieve JSON data from "News API" asynchronously (temporary)
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                try {
                    String responseData = response.body().string();
                    JSONObject json = new JSONObject(responseData);
                    JSONArray articles = json.getJSONArray("articles");
                    JSONObject article;

                    for (int i=0; i < articles.length(); i++) {
                        article = articles.getJSONObject(i);
                        News.JSONObjectToNews(article).save();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        // Test retrieve the news from the database
        Log.v("LOG", News.getAll().toString());
    }
}
