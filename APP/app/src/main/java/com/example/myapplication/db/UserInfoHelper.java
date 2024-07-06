package com.example.myapplication.db;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import com.example.myapplication.bean.UserInfo;
import java.util.ArrayList;
import java.util.List;
public class UserInfoHelper extends SQLiteOpenHelper {
    public UserInfoHelper(@Nullable Context context) {super(context, "sport.db", null, 1);}
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql1="CREATE TABLE UserInfo (\n" +
                "    userId INT PRIMARY KEY,\n" + "    nickname VARCHAR(50),\n" + "    username VARCHAR(50) UNIQUE,\n" +
                "    password VARCHAR(50),\n" + "    phoneNumber VARCHAR(20),\n" +
                "    height DOUBLE,\n" + "    weight DOUBLE,\n" +
                "    targetWeight DOUBLE,\n" + "    age INT,\n" + "    gender VARCHAR(10),\n" +
                "    pushUpGoal INT,\n" + "    squatGoal INT,\n" + "    pullUpGoal INT\n" + ");";
        String sql2="CREATE TABLE Exercise_Type (\n" +
                "    exercise_id INTEGER PRIMARY KEY,\n" +
                "    exercise_name VARCHAR(100) UNIQUE\n" +
                ");";
        String sql3="CREATE TABLE Exercise_Record (\n" +
                "    record_id INTEGER PRIMARY KEY,\n" +
                "    user_id INTEGER NOT NULL,\n" +
                "    exercise_id INTEGER NOT NULL,\n" +
                "    exercise_datetime DATETIME NOT NULL,\n" +
                "    duration INTEGER,  \n" +
                "    quantity INTEGER,\n" +
                "    FOREIGN KEY (user_id) REFERENCES UserInfo(userId),\n" +
                "    FOREIGN KEY (exercise_id) REFERENCES Exercise_Type(exercise_id)\n" +
                ");";
        db.execSQL(sql1);db.execSQL(sql2);db.execSQL(sql3);}
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
    public String insertUserInfo(UserInfo userInfo)
    {
        ContentValues cv=new ContentValues();
        cv.put("userId",userInfo.getUserId());
        cv.put("nickname",userInfo.getNickname());
        cv.put("username",userInfo.getUsername());
        cv.put("password", userInfo.getPassword());
        cv.put("phoneNumber", userInfo.getPhoneNumber());
        cv.put("height", userInfo.getHeight());
        cv.put("weight", userInfo.getWeight());
        cv.put("targetWeight", userInfo.getTargetWeight());
        cv.put("age", userInfo.getAge());
        cv.put("gender", userInfo.getGender());
        cv.put("pushUpGoal", userInfo.getPushUpGoal());
        cv.put("squatGoal", userInfo.getSquatGoal());
        cv.put("pullUpGoal", userInfo.getPullUpGoal());
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        long insert=sqLiteDatabase.insert("UserInfo","userId",cv);
        if(insert==-1) {return "fail";}sqLiteDatabase.close();return "success";
    }
    public String deleteUserInfo(UserInfo userInfo)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        int delete=sqLiteDatabase.delete("UserInfo","userId"+"=?",new String[]{String.valueOf(userInfo.getUserId())});
        sqLiteDatabase.close();
        if(delete==0) return "false";return "success";}
    public String updateUserInfo(UserInfo userInfo)
    {
        ContentValues cv=new ContentValues();
        cv.put("userId",userInfo.getUserId());cv.put("nickname",userInfo.getNickname());
        cv.put("username",userInfo.getUsername());cv.put("password", userInfo.getPassword());
        cv.put("phoneNumber", userInfo.getPhoneNumber());cv.put("height", userInfo.getHeight());
        cv.put("weight", userInfo.getWeight());cv.put("targetWeight", userInfo.getTargetWeight());
        cv.put("age", userInfo.getAge());cv.put("gender", userInfo.getGender());
        cv.put("pushUpGoal", userInfo.getPushUpGoal());
        cv.put("squatGoal", userInfo.getSquatGoal());cv.put("pullUpGoal", userInfo.getPullUpGoal());
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        int update= sqLiteDatabase.update("UserInfo",cv,"userId"+"=?",new String[]{String.valueOf(userInfo.getUserId())});
        if(update==0) {return "fail";}sqLiteDatabase.close();return "success";
    }
    public List<UserInfo> selectAllUserInfo(){
        List<UserInfo>list=new ArrayList<>();String sql="SELECT * from UserInfo;";
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery(sql,null);UserInfo userinfo;
        int userIdIndex=cursor.getColumnIndex("userId");
        int nicknameIndex=cursor.getColumnIndex("nickname");
        int usernameIndex=cursor.getColumnIndex("username");
        int passwordIndex=cursor.getColumnIndex("password");
        int phoneNumberIndex=cursor.getColumnIndex("phoneNumber");
        int heightIndex=cursor.getColumnIndex("height");
        int weightIndex=cursor.getColumnIndex("weight");
        int targetWeightIndex=cursor.getColumnIndex("targetWeight");
        int ageIndex=cursor.getColumnIndex("age");
        int genderIndex=cursor.getColumnIndex("gender");
        int pushUpGoalIndex=cursor.getColumnIndex("pushUpGoal");
        int squatGoalIndex=cursor.getColumnIndex("squatGoal");
        int pullUpGoalIndex=cursor.getColumnIndex("pullUpGoal");
        while(cursor.moveToNext()){
            userinfo=new UserInfo(cursor.getInt(userIdIndex),cursor.getString(nicknameIndex),
                    cursor.getString(usernameIndex),cursor.getString(passwordIndex),cursor.getString(phoneNumberIndex),
                    cursor.getDouble(heightIndex),cursor.getDouble(weightIndex),cursor.getDouble(targetWeightIndex),
                    cursor.getInt(ageIndex),cursor.getString(genderIndex),cursor.getInt(pushUpGoalIndex),
                    cursor.getInt(squatGoalIndex),cursor.getInt(pullUpGoalIndex));
            list.add(userinfo);}
        sqLiteDatabase.close();
        return list;}}