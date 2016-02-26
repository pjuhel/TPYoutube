package s8.fr.esilv.tpyoutube;

import android.content.Context;
import android.util.Log;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by juhel on 26/02/2016.
 */
public class YoutubeConnector {

    private YouTube.Search.List search;
    private YouTube youtube;
    public static final String apiKey = "AIzaSyAj3FPl9YBPsLGCwugZVu5JTA9dcYF6O-E";


    public YoutubeConnector(Context context) {
        youtube = new YouTube.Builder(new NetHttpTransport(), new JacksonFactory(), new HttpRequestInitializer() {
            @Override
            public void initialize(HttpRequest request) throws IOException {
            }
        }).setApplicationName("TPyoutube").build();
        try {
            search = youtube.search().list("id,snippet");
            search.setKey(apiKey);
            search.setType("video");
            search.setFields("items(id/kind,id/videoId,snippet/title,snippet/thumbnails/default/url)");
        }catch (IOException e){
            Log.d("YC", "Could not initialize: "+e);
        }
    }

    public ArrayList<Video> search(String keywords){

        search.setQ(keywords);
        try {
            SearchListResponse searchResponse = search.execute();
            List<SearchResult> results = searchResponse.getItems();

            ArrayList<Video> videos = new ArrayList<Video>();

            for (SearchResult result : results) {
                Video video = new Video(result.getSnippet().getDescription(),
                        result.getSnippet().getTitle(),
                        result.getSnippet().getThumbnails().getDefault().getUrl(),
                        result.getId().getVideoId());
                videos.add(video);
            }
            return videos;
        }catch (IOException e) {
            Log.d("YC", "Could not search: " + e);
            return null;
        }
    }

}

