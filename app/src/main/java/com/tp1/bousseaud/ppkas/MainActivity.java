package com.tp1.bousseaud.ppkas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.tp1.bousseaud.ppkas.model.News;
import com.tp1.bousseaud.ppkas.util.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.ButterKnife;
import butterknife.OnClick;
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
        ButterKnife.bind(this);

        String apiKey = Util.readTextFileFromAssets("api_key_news_api.txt", this);

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
                        // Save news from "News API" to the database
                        News.JSONObjectToNews(article).save();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @OnClick(R.id.btn_view_all_news)
    public void viewAllNews() {
        Intent intent = new Intent(this, NewsListActivity.class);
        startActivity(intent);
    }
}
