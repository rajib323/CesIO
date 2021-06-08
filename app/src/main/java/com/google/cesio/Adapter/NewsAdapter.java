package com.google.cesio.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.cesio.MainActivity;
import com.google.cesio.Model.Article;
import com.google.cesio.R;
import com.google.cesio.pageviewActivity;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>{

    Context mCon;
    List<Article> mArt;

    public NewsAdapter(Context mCon, List<Article> mArt) {
        this.mArt=mArt;
        this.mCon=mCon;
    }

    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(mCon).inflate(R.layout.news_item,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder( NewsAdapter.ViewHolder holder, int position) {
        holder.headline.setText(mArt.get(position).getTitle());
        holder.summary.setText(mArt.get(position).getDescription());
        Glide.with(mCon).load(mArt.get(position).getUrlToImage()).into(holder.img);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(mCon, pageviewActivity.class);
                intent.putExtra("url",mArt.get(position).getUrl());
                mCon.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mArt.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView headline,summary;
        ImageView img;
        public ViewHolder(View view) {
            super(view);
            headline=view.findViewById(R.id.headline);
            summary=view.findViewById(R.id.summary);
            img=view.findViewById(R.id.image);
        }
    }
}
