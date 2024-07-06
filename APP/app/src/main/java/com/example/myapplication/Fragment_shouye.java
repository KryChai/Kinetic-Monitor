package com.example.myapplication;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;
public class Fragment_shouye extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private VideoView videoView;
    private VideoView videoView_ywqz;
    private VideoView videoView_dq;

    public Fragment_shouye() {
    }
    public static Fragment_shouye newInstance(String param1, String param2) {
        Fragment_shouye fragment = new Fragment_shouye();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_shouye, container, false);
        videoView=view.findViewById(R.id.videoView);
        videoView_dq=view.findViewById(R.id.videoView_dq);
        videoView_ywqz=view.findViewById(R.id.videoView_ywqz);
        String videoPath = "android.resource://" + getActivity().getPackageName() + "/" + R.raw.fwc_video;
        videoView.setVideoURI(Uri.parse(videoPath));
        String videoPath_dq = "android.resource://" + getActivity().getPackageName() + "/" + R.raw.dq_video;
        videoView_dq.setVideoURI(Uri.parse(videoPath_dq));
        String videoPath_ywqz = "android.resource://" + getActivity().getPackageName() + "/" + R.raw.ywqz_video;
        videoView_ywqz.setVideoURI(Uri.parse(videoPath_ywqz));
        videoView.start();
        videoView_dq.start();
        videoView_ywqz.start();
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                videoView.start();
            }
        });
        videoView_ywqz.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                videoView_ywqz.start();
            }
        });

        videoView_dq.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                videoView_dq.start();
            }
        });

        return view;
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
        }
    }
}