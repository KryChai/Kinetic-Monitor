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

import java.time.LocalDateTime;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.format.DateTimeFormatter;

import android.media.MediaPlayer;



public class Waiting_DQ extends AppCompatActivity {
    private int duration;

    private TextView textView; // 声明TextView对象
    private String messageFromServer;

    private TextView textView_show;
    private String Receive_data;

    private VideoView videoView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting_dq);
//        textView = findViewById(R.id.dq_textView2); // 替换为您的TextView的ID
        textView_show=findViewById(R.id.dq_textView2);
//        new Thread(runnable).start();
        Thread thread = new Thread(runnable);// 启动线程
        thread.start();

        // 假设你已经有了VideoView的实例
        videoView = findViewById(R.id.dq_videoView);
        File videoFile = new File(Waiting_DQ.this.getExternalFilesDir(""), "dq.mp4");
        // 获取视频文件的绝对路径
        String videoPath = videoFile.getAbsolutePath();


        // 设置视频文件的路径
        Uri videoUri = Uri.parse(videoPath);

        videoView.setVideoURI(videoUri);


        // 准备播放视频
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
//                videoView.setScaleType(VideoView.ScaleType.CENTER_CROP);
                // 视频准备好了，开始播放
                videoView.start();
            }
        });

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                // 当视频播放完成时，重新开始播放
                videoView.start();
            }
        });

// 处理可能的错误
        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mediaPlayer, int what, int extra) {
                // 处理错误
                return false;
            }
        });

    }

    // 假设你已经有了Runnable的实例
    Runnable runnable = new Runnable() {
        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public void run() {
            File videoFile = new File(Waiting_DQ.this.getExternalFilesDir(""), "dq.mp4");
            FileInputStream fileInputStream = null;
            //ip地址
            String ip_server = getResources().getString(R.string.ip_server);

            try {
                fileInputStream = new FileInputStream(videoFile);
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (ContextCompat.checkSelfPermission(Waiting_DQ.this, Manifest.permission.INTERNET)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(Waiting_DQ.this, new String[]{Manifest.permission.INTERNET}, 1);
            }

            Log.i("TAG123132", "读取完毕");

            try {
                Socket socket = new Socket(ip_server, 5000);

                OutputStream outputStream = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(outputStream, true);

                // 发送文件名称
                String fileName = "dq.mp4";
                writer.print(fileName);
                writer.flush();

                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

                // 发送文件内容
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                    dos.write(buffer, 0, bytesRead);
                    Log.i("TAG123132","传输中");
                }
                dos.flush();
                Log.i("TAG123132","传输完成");

                // 关闭资源
                fileInputStream.close();
                socket.shutdownOutput();


                DataInputStream dis = new DataInputStream(socket.getInputStream());
//              接收文件
                File NewVideoFile = new File(Waiting_DQ.this.getExternalFilesDir(""), "Newdq.mp4");
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

                // 创建第二个Socket实例，用于接收count值
                Socket socket2 = new Socket(ip_server, 7777);
                DataInputStream dis2 = new DataInputStream(socket2.getInputStream());
                String count="";
                // 接收count值的代码
                try {
                    // 假设服务器发送的是UTF-8编码的字符串
                    count = dis2.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // 使用count值进行操作，例如打印或更新UI
                Log.i("TAG123132", "接收到的count值: " + count);
                dis2.close();
                socket2.close();

                // 获取当前系统时间
                LocalDateTime time_now=LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
                // 格式化日期时间
                String TimeNow = formatter.format(time_now);
                LocalDateTime dateTime2 = LocalDateTime.parse(TimeNow, formatter);


                String videoFilePath = newVideoFile; // 替换为您的视频文件路径

                MediaPlayer mediaPlayer = new MediaPlayer();

                mediaPlayer.setDataSource(videoFilePath);

                mediaPlayer.prepare();

                long time = mediaPlayer.getDuration();//获得了视频的时长（以毫秒为单位）
                duration=(int)(time/1000);
                Log.i("TAG123132", "视频时长 " + duration);

                int number = Integer.parseInt(count);
                System.out.println("Converted number: " + number);

                ExerciseRecordHelper helper=new ExerciseRecordHelper(getApplication());

                int recordId=helper.countRecord();
                recordId++;

//                写数据库
                ExerciseRecord record=new ExerciseRecord(recordId,1,2,dateTime2,duration,number);
//                ExerciseRecordHelper helper=new ExerciseRecordHelper(getApplication());
                helper.insertExerciseRecord(record);

                String text="本次锻炼时长："+duration+"秒\n蹲起个数："+number+"个";

                // 假设这是在子线程中执行的操作
                new Thread(() -> {
                    // 使用Handler将更新操作传递到主线程
                    runOnUiThread(() -> {
                        // 设置视频文件的路径
                        Uri videoUri = Uri.parse(newVideoFile);
                        videoView.setVideoURI(videoUri);
                        // 准备播放视频
                        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                // 视频准备好了，开始播放
                                videoView.start();
                            }
                        });
                        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                // 当视频播放完成时，重新开始播放
                                videoView.start();
                            }
                        });
                        // 处理可能的错误
                        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                            @Override
                            public boolean onError(MediaPlayer mediaPlayer, int what, int extra) {
                                // 处理错误
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
