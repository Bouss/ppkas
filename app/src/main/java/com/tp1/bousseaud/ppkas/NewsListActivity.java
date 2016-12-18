package com.tp1.bousseaud.ppkas;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.tp1.bousseaud.ppkas.adapter.NewsAdapter;
import com.tp1.bousseaud.ppkas.model.News;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsListActivity extends Activity {
    @BindView(R.id.lv_news) ListView lvNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);
        ButterKnife.bind(this);

        lvNews.setAdapter(new NewsAdapter(this, News.getAll()));
    }
}
