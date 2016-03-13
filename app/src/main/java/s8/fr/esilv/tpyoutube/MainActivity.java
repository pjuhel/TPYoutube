package s8.fr.esilv.tpyoutube;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import s8.fr.esilv.tpyoutube.Objects.Videos;
import s8.fr.esilv.tpyoutube.Objects.Youtube;

public class MainActivity extends Activity {

    private RecyclerView recyclerView;
    private VideosAdapter videosAdapter;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        videosAdapter = new VideosAdapter(new Videos());
        recyclerView.setAdapter(videosAdapter);

        Button search_button = (Button) findViewById(R.id.search_button);
        search_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                initiateSearch();
            }
        });
    }

    public void initiateSearch(){
        EditText editText = (EditText)findViewById(R.id.search_box);
        String keywords = editText.getText().toString();
        keywords = keywords.replace(' ', '+');
        sendRequest(keywords);
    }
    public void sendRequest(String keywords){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://www.googleapis.com/youtube/v3/search?part=snippet&type=video&q="+keywords+"&key="+getString(R.string.youtubeKeyAPI);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        retrieveSearch(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });
        queue.add(stringRequest);
    }

    public void retrieveSearch(String response){
        Gson gson = new Gson();
        Youtube youtube = gson.fromJson(response, Youtube.class);
        if(youtube != null){
            Videos videos = new Videos();
            for(int i = 0; i < youtube.getItems().size(); i++){
                videos.add(youtube.getItems().get(i));
            }
            videosAdapter.setVideos(videos);
            videosAdapter.notifyDataSetChanged();
        }


    }
}
