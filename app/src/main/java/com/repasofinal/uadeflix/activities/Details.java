package com.repasofinal.uadeflix.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.repasofinal.uadeflix.MainActivity;
import com.repasofinal.uadeflix.R;
import com.repasofinal.uadeflix.logic.Movie;
import com.squareup.picasso.Picasso;

public class Details extends AppCompatActivity {

    private ImageButton ibtn_back;
    private ImageView iv_image;
    private ImageView iv_play;

    private TextView txt_title;
    private TextView txt_description;
    private TextView txt_year;
    private TextView txt_duration;
    private TextView txt_director;
    private TextView txt_cast;
    private TextView txt_writer;
    private TextView txt_genres;
    private TextView txt_rating;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Movie movie = MainActivity.manager.GetCurrentMovie();

        ibtn_back = (ImageButton) findViewById(R.id.details_ibtn_back);
        iv_image = (ImageView) findViewById(R.id.details_iv_image);
        iv_play = (ImageView) findViewById(R.id.details_iv_play);

        txt_title = (TextView) findViewById(R.id.details_txt_titleInfo);
        txt_description = (TextView) findViewById(R.id.details_txt_descriptionInfo);
        txt_year = (TextView) findViewById(R.id.details_txt_yearInfo);
        txt_duration = (TextView) findViewById(R.id.details_txt_durationInfo);
        txt_director = (TextView) findViewById(R.id.details_txt_directorInfo);
        txt_cast = (TextView) findViewById(R.id.details_txt_castInfo);
        txt_writer = (TextView) findViewById(R.id.details_txt_writerInfo);
        txt_genres = (TextView) findViewById(R.id.details_txt_genresInfo);
        txt_rating = (TextView) findViewById(R.id.details_txt_ratingInfo);

        ibtn_back.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { finish(); } });
        iv_play.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { startActivity(new Intent(Details.this, Viewer.class)); } });

        Picasso.get().load(movie.getImageSrc()).placeholder(R.drawable.ic_movie_placeholder).into(iv_image);
        txt_title.setText(movie.getTitle());
        txt_description.setText(movie.getDescription());
        txt_year.setText(movie.getYear());
        txt_duration.setText(movie.getDuration());
        txt_cast.setText(movie.getCast());
        txt_writer.setText(movie.getWriter());
        txt_genres.setText(movie.getGenre());
        txt_rating.setText(movie.getRank());
    }
}