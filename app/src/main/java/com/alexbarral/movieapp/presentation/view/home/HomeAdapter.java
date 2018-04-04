package com.alexbarral.movieapp.presentation.view.home;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alexbarral.movieapp.R;
import com.alexbarral.movieapp.presentation.model.TvShowModel;
import com.alexbarral.movieapp.presentation.presenter.HomePresenter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.annotations.NonNull;

/**
 * Created by alejandrobarral on 4/4/18.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private final HomePresenter presenter;
    private List<TvShowModel> items;


    public HomeAdapter(@NonNull HomePresenter presenter) {
        this.presenter = presenter;
        items = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        parent.getContext();
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final TvShowModel tvShowModel = items.get(position);

        holder.titleTextView.setText(tvShowModel.getName());
        String pictureUrl = getPictureUrl(tvShowModel);
        if (!pictureUrl.isEmpty()) {
            Glide.with(holder.imageView.getContext())
                    .load(pictureUrl)
                    .apply(RequestOptions.centerCropTransform())
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(holder.imageView);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //presenter.onItemClicked(tvShowModel);
            }
        });
    }

    private String getPictureUrl(TvShowModel item) {
        if (item.getPoster_path() != null && !item.getPoster_path().isEmpty()) {
            return item.getPoster_path();
        } else {
            return "";
        }
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

    static class ViewHolder extends RecyclerView.ViewHolder {
        View itemView;
        @BindView(R.id.backgroundImageView)
        ImageView imageView;
        @BindView(R.id.titleTextView)
        TextView titleTextView;

        ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            ButterKnife.bind(this, itemView);
        }
    }
}