package com.tp1.bousseaud.ppkas.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tp1.bousseaud.ppkas.R;
import com.tp1.bousseaud.ppkas.model.News;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsAdapter extends ArrayAdapter<News> {

    public NewsAdapter(Context context, List<News> newsList) {
        super(context, 0, newsList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_news, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }

        News news = getItem(position);

        // Load the picture of the news from its URL
        Picasso.with(getContext()).load(news.getPicture()).into(holder.thumbnail);
        holder.title.setText(news.getTitle());

        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.img_thumbnail) ImageView thumbnail;
        @BindView(R.id.tv_title) TextView title;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
