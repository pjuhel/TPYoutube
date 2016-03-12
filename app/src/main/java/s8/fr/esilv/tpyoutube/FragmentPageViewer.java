package s8.fr.esilv.tpyoutube;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import s8.fr.esilv.tpyoutube.Objects.Items;

/**
 * Created by juhel on 12/03/2016.
 */
public class FragmentPageViewer extends android.support.v4.app.Fragment {

    private Items video;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.pageviewer_fragment_view, container, false);
        Bundle bundle = getArguments();
        Gson gson = new Gson();
        video = gson.fromJson(bundle.getString("video"), Items.class);
        ImageView imageView = (ImageView)rootView.findViewById(R.id.imagePageViewer);
        Picasso.with(rootView.getContext()).load(video.getSnippet().getThumbnails().getMedium().getUrl()).into(imageView);
        ((TextView) rootView.findViewById(R.id.description)).setText(video.getSnippet().getDescription());
        ((TextView) rootView.findViewById(R.id.videoTitle)).setText(video.getSnippet().getTitle());
        ((TextView) rootView.findViewById(R.id.publishedAt)).setText(video.getSnippet().getPublishedAt());
        ((TextView) rootView.findViewById(R.id.channelTitle)).setText(video.getSnippet().getChanelid());
        return rootView;
    }
}
