package kz.kuanysh.realreaction.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import kz.kuanysh.realreaction.R;
import kz.kuanysh.realreaction.adapters.VideoAdapter;
import kz.kuanysh.realreaction.models.Video;
import kz.kuanysh.realreaction.network.ApiClient;
import kz.kuanysh.realreaction.network.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private ListView videoListView;
    private VideoAdapter videoAdapter;
    private List<Video> videoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoListView = findViewById(R.id.video_list_view);
        videoList = new ArrayList<>();
        videoAdapter = new VideoAdapter(this, videoList);
        videoListView.setAdapter(videoAdapter);

        // Set item click listener for the video list
        videoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                // Handle click event for the selected video
                Video video = videoList.get(position);
                Toast.makeText(MainActivity.this, "Clicked video: " + video.getTitle(), Toast.LENGTH_SHORT).show();

                // Open the second activity and pass the video data
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("videoId", "gHcbdZ98J7I");
                startActivity(intent);
            }
        });

        // Fetch the list of videos from the API
        fetchVideoList();
    }

    private void fetchVideoList() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Video>> call = apiService.getVideoList();

        call.enqueue(new Callback<List<Video>>() {
            @Override
            public void onResponse(Call<List<Video>> call, Response<List<Video>> response) {
                if (response.isSuccessful()) {
                    videoList.clear();
                    videoList.addAll(response.body());
                    videoAdapter.notifyDataSetChanged();
                } else {
                    Log.e(TAG, "Failed to fetch video list: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Video>> call, Throwable t) {
                Log.e(TAG, "Error fetching video list: " + t.getMessage());
            }
        });
    }
}
