package com.alexbarral.movieapp.presentation.view.home;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.alexbarral.movieapp.R;
import com.alexbarral.movieapp.presentation.model.ConfigurationModel;
import com.alexbarral.movieapp.presentation.model.TvShowModel;
import com.alexbarral.movieapp.presentation.util.ConfigurationModelUtil;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by alejandrobarral on 4/4/18.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    public interface OnItemClickListener {
        void onTvShowItemClicked(long id);
    }

    private List<TvShowModel> items;
    private ConfigurationModel configuration;
    private OnItemClickListener onItemClickListener;


    public HomeAdapter() {
        items = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        parent.getContext();
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_home, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final TvShowModel tvShowModel = items.get(position);

        holder.titleTextView.setText(tvShowModel.getName());
        holder.rb_rating.setRating(tvShowModel.getVote_average()/2);
        String pictureUrl = ConfigurationModelUtil.getPosterUrl(configuration, tvShowModel);
        if (!pictureUrl.isEmpty()) {
            Glide.with(holder.imageView.getContext())
                    .load(pictureUrl)
                    .apply(RequestOptions.centerCropTransform())
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(holder.imageView);
        }

        holder.itemView.setOnClickListener(view -> HomeAdapter.this.onItemClickListener.onTvShowItemClicked(tvShowModel.getId()));
    }



    @Override
    public int getItemCount() {
        return items.size();
    }

    public void clear() {
        items.clear();
    }

    public void addAll(List<TvShowModel> items) {
        this.items.addAll(items);
    }

    public void setConfiguration(ConfigurationModel configuration) {
        this.configuration = configuration;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        View itemView;
        @BindView(R.id.backgroundImageView)
        ImageView imageView;
        @BindView(R.id.titleTextView)
        TextView titleTextView;
        @BindView(R.id.ratingBar)
        RatingBar rb_rating;

        ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            ButterKnife.bind(this, itemView);
        }
    }


    public void setOnItemClickListener (OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}