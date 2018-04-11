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
import com.alexbarral.movieapp.presentation.model.MovieModel;
import com.alexbarral.movieapp.presentation.util.ConfigurationModelUtil;
import com.alexbarral.movieapp.presentation.util.StringFormatter;
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

    private List<MovieModel> items;
    private ConfigurationModel configuration;
    private OnItemClickListener onItemClickListener;

    private StringFormatter stringFormatter;


    public HomeAdapter() {
        items = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        parent.getContext();
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_home, parent, false);

        stringFormatter = new StringFormatter();

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final MovieModel movieModel = items.get(position);

        holder.titleTextView.setText(movieModel.getName());
        holder.rb_rating.setRating(movieModel.getVote_average() / 2);
        holder.yearTextView.setText(stringFormatter.getReleaseYear(movieModel));
        holder.descriptionTextView.setText(movieModel.getOverview());

        String pictureUrl = ConfigurationModelUtil.getPosterUrl(configuration, movieModel);
        if (!pictureUrl.isEmpty()) {
            Glide.with(holder.imageView.getContext())
                    .load(pictureUrl)
                    .apply(RequestOptions.centerCropTransform())
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(holder.imageView);
        }

        holder.itemView.setOnClickListener(view -> HomeAdapter.this.onItemClickListener.onTvShowItemClicked(movieModel.getId()));
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    public void clear() {
        items.clear();
    }

    public void addAll(List<MovieModel> items) {
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
        @BindView(R.id.yearTextView)
        TextView yearTextView;
        @BindView(R.id.descriptionTextView)
        TextView descriptionTextView;
        @BindView(R.id.ratingBar)
        RatingBar rb_rating;

        ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            ButterKnife.bind(this, itemView);
        }
    }


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}