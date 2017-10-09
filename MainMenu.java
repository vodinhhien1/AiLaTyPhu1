package com.example.ductri.ailatyphu;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

public class MainMenu extends AppCompatActivity {

    Button btnPlay;
    Button btnHelp;
    Button btnExit;
    LinearLayout mh;
    MediaPlayer song;
    public void AnhXa(){
        mh = (LinearLayout)findViewById(R.id.menu);
        btnPlay = (Button)findViewById(R.id.btnplay);
        btnHelp = (Button)findViewById(R.id.btnhelp);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        AnhXa();
        // Nhạc nền
        song = MediaPlayer.create(getApplicationContext(),R.raw.backgroundmusic);
        song.start();

        //Sự kiện
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mhStart = new Intent(getApplicationContext(),MainStart.class);
                startActivity(mhStart);
            }
        });
        btnHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mhHelp = new Intent(getApplicationContext(),MainHelp.class);
                startActivity(mhHelp);
            }
        });
        }
    }
