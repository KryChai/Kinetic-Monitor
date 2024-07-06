package com.example.myapplication;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.VideoView;
import com.example.myapplication.bean.ExerciseRecord;
import com.example.myapplication.db.ExerciseRecordHelper;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Waiting_FWC extends AppCompatActivity {
    private int duration;
    private TextView textView_show;
    private VideoView videoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting_fwc);
        textView_show=findViewById(R.id.fwc_textView2);
        videoView = findViewById(R.id.fwc_videoView);
        File videoFile = new File(Waiting_FWC.this.getExternalFilesDir(""), "fwc.mp4");
        String videoPath = videoFile.getAbsolutePath();
        Uri videoUri = Uri.parse(videoPath);
        videoView.setVideoURI(videoUri);
        Thread thread = new Thread(runnable);
        thread.start();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                videoView.start();
            }
        });
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                videoView.start();
            }
        });
        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mediaPlayer, int what, int extra) {
                return false;
            }
        });
    }
    Runnable runnable = new Runnable(){
        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public void run() {
            String ip_server = getResources().getString(R.string.ip_server);
            File videoFile = new File(Waiting_FWC.this.getExternalFilesDir(""), "fwc.mp4");
            FileInputStream fileInputStream = null;
            try {
                fileInputStream = new FileInputStream(videoFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (ContextCompat.checkSelfPermission(Waiting_FWC.this, Manifest.permission.INTERNET)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(Waiting_FWC.this, new String[]{Manifest.permission.INTERNET}, 1);
            }
            try {
                Socket socket = new Socket(ip_server, 5000);
                OutputStream outputStream = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(outputStream, true);
                String fileName = "fwc.mp4";
                writer.print(fileName);
                writer.flush();
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                    dos.write(buffer, 0, bytesRead);
                }
                dos.flush();
                fileInputStream.close();
                socket.shutdownOutput();
                DataInputStream dis = new DataInputStream(socket.getInputStream());
                File NewVideoFile = new File(Waiting_FWC.this.getExternalFilesDir(""), "Newfwc.mp4");
                String newVideoFile=NewVideoFile.getAbsolutePath();
                FileOutputStream newfos = new FileOutputStream(newVideoFile);
                byte[] Newbuffer = new byte[4096];
                int NewbytesRead;
                while ((NewbytesRead = dis.read(Newbuffer)) != -1) {
                    newfos.write(Newbuffer, 0, NewbytesRead);
                }
                dis.close();
                newfos.close();
                socket.close();

                Socket socket2 = new Socket(ip_server, 7777);
                DataInputStream dis2 = new DataInputStream(socket2.getInputStream());
                String count="";
                try {
                    count = dis2.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.i("TAG123132", "接收到的count值: " + count);
                dis2.close();
                socket2.close();
                LocalDateTime time_now=LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
                String TimeNow = formatter.format(time_now);
                LocalDateTime dateTime2 = LocalDateTime.parse(TimeNow, formatter);
                String videoFilePath = newVideoFile;
                MediaPlayer mediaPlayer = new MediaPlayer();
                mediaPlayer.setDataSource(videoFilePath);
                mediaPlayer.prepare();
                long time = mediaPlayer.getDuration();
                duration=(int)(time/1000);
                int number = Integer.parseInt(count);
                ExerciseRecordHelper helper=new ExerciseRecordHelper(getApplication());
                int recordId=helper.countRecord();
                recordId++;
                ExerciseRecord record=new ExerciseRecord(recordId,1,1,dateTime2,duration,number);
                helper.insertExerciseRecord(record);
                String text="本次锻炼时长："+duration+"秒\n俯卧撑个数："+number+"个";
                new Thread(() -> {
                    runOnUiThread(() -> {
                        Uri videoUri = Uri.parse(newVideoFile);
                        videoView.setVideoURI(videoUri);
                        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                videoView.start();
                            }
                        });
                        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                videoView.start();
                            }
                        });
                        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                            @Override
                            public boolean onError(MediaPlayer mediaPlayer, int what, int extra) {
                                return false;
                            }
                        });
                        textView_show.setText(text);
                    });
                }).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };
}