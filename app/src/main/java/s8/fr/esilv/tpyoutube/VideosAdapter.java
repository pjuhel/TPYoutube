package s8.fr.esilv.tpyoutube;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import s8.fr.esilv.tpyoutube.Objects.Videos;

/**
 * Created by juhel on 11/03/2016.
 */
public class VideosAdapter extends RecyclerView.Adapter<VideosAdapter.ViewHolder>{

    private static Videos videos;

    public VideosAdapter(Videos videos){
        this.videos = videos;
    }

    public void setVideos(Videos videos) {
        this.videos = videos;
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView title;
        public ImageView imageView;
        public ViewHolder(View v) {
            super(v);

            title = (TextView) itemView.findViewById(R.id.title);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            v.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    Intent intent = new Intent(v.getContext(), YoutubePlayerActivity.class);
                    int position = getAdapterPosition();
                    intent.putExtra("jsonVideos", new Gson().toJson(videos));
                    intent.putExtra("position", position);
                    v.getContext().startActivity(intent);
                }
            });
        }
    }

    // Create new views (invoked by the layout manager)
    @Override
    public VideosAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.items_view,parent, false);
        // set the view's size, mar
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Picasso.with(holder.itemView.getContext()).load(videos.get(position).getSnippet().getThumbnails().getMedium().getUrl()).into(holder.imageView);
        holder.title.setText(videos.get(position).getSnippet().getTitle());
    }
    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return videos.size();
    }
}
