package s8.fr.esilv.tpyoutube;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.google.gson.Gson;

import s8.fr.esilv.tpyoutube.Objects.Videos;

public class YoutubePlayerActivity extends FragmentActivity implements YouTubePlayer.OnInitializedListener {

    /**
     * The number of pages to show by default.
     */
    private static int NUM_PAGES = 5;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager mPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter mPagerAdapter;

    private Videos videos;
    private int startingItem;
    private YouTubePlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_player);
        Intent intent = getIntent();
        //Instantiate videos
        setVideos(intent.getStringExtra("jsonVideos"));
        startingItem = intent.getIntExtra("position", 0);
        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager(), videos);
        mPager.setAdapter(mPagerAdapter);
        mPager.setCurrentItem(startingItem);

        final YouTubePlayerFragment youTubePlayerFragment = (YouTubePlayerFragment) getFragmentManager().findFragmentById(R.id.youtube_fragment);
        youTubePlayerFragment.initialize(getString(R.string.youtubeKeyAPI), this);

        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                String videoId = videos.get(position).getId().getVideoId();
                player.cueVideo(videoId);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        if (!b) {
            player = youTubePlayer;
            player.cueVideo(videos.get(startingItem).getId().getVideoId());
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }

    /**
     * Pager adapter that display all the video retrieve by the search
     */
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

        private Videos videos;
        public ScreenSlidePagerAdapter(FragmentManager fm, Videos videos) {
            super(fm);
            this.videos = videos;
        }

        @Override
        public Fragment getItem(int position) {
            FragmentPageViewer fragment = new FragmentPageViewer();
            Gson gson = new Gson();
            Bundle bundle = new Bundle(0);
            bundle.putString("video", gson.toJson(videos.get(position)));
            fragment.setArguments(bundle);
            return fragment;
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }

    public void setVideos(String json){
        Gson gson = new Gson();
        videos = gson.fromJson(json, Videos.class);
        NUM_PAGES = videos.size();
    }
}
