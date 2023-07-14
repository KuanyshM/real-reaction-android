package kz.kuanysh.realreaction.activities;
import kz.kuanysh.realreaction.R;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class SecondActivity extends AppCompatActivity {
    private YouTubePlayerView youtubePlayerView;
    private String videoId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        youtubePlayerView = findViewById(R.id.youtube_player_view);
        videoId = getIntent().getStringExtra("videoId");

        getLifecycle().addObserver(youtubePlayerView);
        youtubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(YouTubePlayer youTubePlayer) {
                youTubePlayer.loadVideo(videoId, 0f);
                youTubePlayer.play();
            }
        });
    }
}