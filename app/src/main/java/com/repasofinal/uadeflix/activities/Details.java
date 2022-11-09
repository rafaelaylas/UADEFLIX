package com.repasofinal.uadeflix.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.repasofinal.uadeflix.MainActivity;
import com.repasofinal.uadeflix.R;
import com.repasofinal.uadeflix.logic.Manager;
import com.repasofinal.uadeflix.logic.Movie;
import com.repasofinal.uadeflix.support.ActionV;
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


    private TextView txt_error;
    private ConstraintLayout clay_loadingScreen;

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

        txt_error = (TextView) findViewById(R.id.details_txt_error);
        clay_loadingScreen = (ConstraintLayout) findViewById(R.id.details_clay_loadingScreen);

        ibtn_back.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { finish(); } });
        iv_play.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { WatchMovie(); } });

        Picasso.get().load(movie.getImageWideSrc()).placeholder(R.drawable.ic_movie_placeholder).into(iv_image);
        txt_title.setText(movie.getTitle());
        txt_description.setText(movie.getDescription());
        txt_year.setText(movie.getYear());
        txt_duration.setText(movie.getDuration());
        txt_cast.setText(movie.getCast());
        txt_writer.setText(movie.getWriter());
        txt_genres.setText(movie.getGenre());
        txt_rating.setText(movie.getRank());

        clay_loadingScreen.setVisibility(View.GONE);
        txt_error.setVisibility(View.GONE);
    }

    private void WatchMovie(){
        clay_loadingScreen.setVisibility(View.VISIBLE);
        MainActivity.manager.CanViewCurrentMovie(
                new ActionV() { @Override public void Invoke() {
                    clay_loadingScreen.setVisibility(View.GONE);
                    txt_error.setVisibility(View.GONE);
                    startActivity(new Intent(Details.this, Viewer.class));
                }},
                new ActionV() { @Override public void Invoke() {
                    clay_loadingScreen.setVisibility(View.GONE);
                    txt_error.setText("Please check your subscriptions to watch this movie");
                    txt_error.setVisibility(View.VISIBLE);
                } },
                new ActionV() { @Override public void Invoke() {
                    clay_loadingScreen.setVisibility(View.GONE);
                    txt_error.setText("Connection Error");
                    txt_error.setVisibility(View.VISIBLE);
                } },
                new ActionV() { @Override public void Invoke() {
                    clay_loadingScreen.setVisibility(View.GONE);
                    txt_error.setText("Connection Fail");
                    txt_error.setVisibility(View.VISIBLE);
                } }
        );
    }
}