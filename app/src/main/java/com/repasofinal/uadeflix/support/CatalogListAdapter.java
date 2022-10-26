package com.repasofinal.uadeflix.support;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.repasofinal.uadeflix.MainActivity;
import com.repasofinal.uadeflix.R;
import com.repasofinal.uadeflix.activities.Details;
import com.repasofinal.uadeflix.logic.Catalog;
import com.repasofinal.uadeflix.logic.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CatalogListAdapter extends ArrayAdapter<Catalog> {

    Context context;
    int resource;
    ArrayList<Catalog> items;

    public CatalogListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Catalog> objects) {
        super(context, resource, objects);

        this.context = context;
        this.resource = resource;
        this.items = objects;
    }

    @NonNull @Override public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(resource, null);
        Catalog catalog = items.get(position);


        TextView title = (TextView) view.findViewById(R.id.lviewCatalog_txt_title);
        title.setText(catalog.getTitle());

        LinearLayout hView = (LinearLayout) view.findViewById(R.id.hsviewCatalog_lview_movies);
        for (Movie movie:catalog.getMovies() ) { hView.addView(createMovieView(movie, inflater)); }

        return view;
    }

    private View createMovieView(Movie movie, LayoutInflater inflater) {
        View newMovieView = inflater.inflate(R.layout.list_view_movie, null);
        ImageView image = (ImageView) newMovieView.findViewById(R.id.lviewMovie_iv_image);
        Picasso.get().load(movie.getImagePosterSrc()).placeholder(R.drawable.ic_movie_placeholder).into(image);

        newMovieView.findViewById(R.id.lviewMovie_btn_select).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                Intent intent = new Intent(context, Details.class);
                MainActivity.manager.SetCurrentMovie(movie);
                context.startActivity(intent);
            }
        });

        return newMovieView;
    }
}
