package s8.fr.esilv.tpyoutube;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends Activity {

    private ArrayList<Video> videos;
    private ListView listview;
    private EditText editText;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.search_box);
        listview = (ListView) findViewById(R.id.listView);

        handler = new Handler();

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_DONE){
                    performRequest(v.getText().toString());
                    return false;
                }
                return true;
            }
        });


    }


    public void performRequest(final String keywords){
        new Thread(){
            public void run(){
                YoutubeConnector youtubeConnector = new YoutubeConnector(MainActivity.this);
                videos = youtubeConnector.search(keywords);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        updateVideosFound();
                    }
                });
            }
        }.start();
    }

    private void updateVideosFound(){
        ArrayAdapter<Video> adapter = new ArrayAdapter<Video>(getApplicationContext(), R.layout.video_layout, videos){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if(convertView == null){
                    convertView = getLayoutInflater().inflate(R.layout.video_layout, parent, false);
                }
                ImageView thumbnail = (ImageView)convertView.findViewById(R.id.video_thumbnail);
                TextView title = (TextView)convertView.findViewById(R.id.video_title);
                TextView description = (TextView)convertView.findViewById(R.id.video_description);

                Video searchResult = videos.get(position);

                Picasso.with(getApplicationContext()).load(searchResult.getThumbnails()).into(thumbnail);
                title.setText(searchResult.getTitle());
                description.setText(searchResult.getDescription());
                return convertView;
            }
        };
        listview.setAdapter(adapter);
    }
}
