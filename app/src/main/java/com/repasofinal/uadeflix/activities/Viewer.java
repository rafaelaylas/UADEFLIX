package com.repasofinal.uadeflix.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.VideoView;

import com.repasofinal.uadeflix.MainActivity;
import com.repasofinal.uadeflix.R;
import com.repasofinal.uadeflix.logic.Manager;

import java.net.PasswordAuthentication;

public class Viewer extends AppCompatActivity {

    private VideoView videoView;
    private MediaController mediaController;
    private ConstraintLayout clay_menu;
    private ImageButton ibtn_back;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewer);

        videoView = (VideoView) findViewById(R.id.viewer_videoView);
        clay_menu = (ConstraintLayout) findViewById(R.id.viewer_clay_menu);
        ibtn_back = (ImageButton) findViewById(R.id.viewer_ibtn_back);

        clay_menu.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { ShowBackBTN(); } });
        ibtn_back.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { Back(); } });

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        //videoView.setVideoPath("http://videocdn.bodybuilding.com/video/mp4/62000/62792m.mp4");
        //Log.d("Debug", MainActivity.manager.GetCurrentMovie().getVideoSrc());
        videoView.setVideoPath(MainActivity.manager.GetCurrentMovie().getVideoSrc());

        mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        mediaController.setMediaPlayer(videoView);
        videoView.setMediaController(mediaController);
        videoView.start();
    }

    private void ShowBackBTN() {
        ibtn_back.setVisibility(View.VISIBLE);
        if(!mediaController.isShowing()) { mediaController.show(3000); }
        new Handler().postDelayed(new Runnable() { public void run() { ibtn_back.setVisibility(View.GONE); } }, 3000);
    }
    private void Back() { videoView.pause(); finish(); }
}