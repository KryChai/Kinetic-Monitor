package com.example.myapplication.db;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.example.myapplication.bean.ExerciseRecord;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
public class ExerciseRecordHelper extends SQLiteOpenHelper {
    public ExerciseRecordHelper(@Nullable Context context) {
        super(context, "sport.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {}
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
    public String insertExerciseRecord(ExerciseRecord exerciseRecord)
    {
        ContentValues cv=new ContentValues();
        cv.put("user_Id",exerciseRecord.getUserId());
        cv.put("exercise_Id", exerciseRecord.getExerciseId());
        cv.put("exercise_Datetime", exerciseRecord.getExerciseDatetime().toString());
        cv.put("quantity", exerciseRecord.getQuantity());
        cv.put("duration", exerciseRecord.getDuration());
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        long insert=sqLiteDatabase.insert("Exercise_Record",null,cv);
        if(insert==-1)
        {
            return "fail";
        }
        sqLiteDatabase.close();
        return "success";
    }
    public String deleteExerciseRecord(ExerciseRecord exerciseRecord)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        int delete=sqLiteDatabase.delete("Exercise_Record","user_Id"+"=?",new String[]{String.valueOf(exerciseRecord.getUserId())});
        sqLiteDatabase.close();
        if(delete==0)
            return "false";
        return "success";
    }
    public String updateExerciseRecord(ExerciseRecord exerciseRecord)
    {
        ContentValues cv=new ContentValues();
        cv.put("user_Id",exerciseRecord.getUserId());
        cv.put("record_Id",exerciseRecord.getRecordId());
        cv.put("exercise_Id", exerciseRecord.getExerciseId());
        cv.put("exercise_Datetime", exerciseRecord.getExerciseDatetime().toString());
        cv.put("quantity", exerciseRecord.getQuantity());
        cv.put("duration", exerciseRecord.getDuration());
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        int update= sqLiteDatabase.update("Exercise_Record",cv,"record_Id"+"=?",new String[]{String.valueOf(exerciseRecord.getRecordId())});
        if(update==0)
        {
            return "fail";
        }
        sqLiteDatabase.close();
        return "success";
    }
    public int countRecord(){
        int num=0;
        String sql="SELECT count() from Exercise_Record;";
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery(sql,null);
        if (cursor.moveToFirst()) {
            num = cursor.getInt(0);
        }
        cursor.close();
        sqLiteDatabase.close();
        return num;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public List<ExerciseRecord> selectAllExerciseRecord(){
        List<ExerciseRecord>list=new ArrayList<>();
        String sql="SELECT * from Exercise_Record;";
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery(sql,null);
        ExerciseRecord exerciseRecord;
        int recordIdIndex = cursor.getColumnIndex("record_id");
        int userIdIndex = cursor.getColumnIndex("user_id");
        int exerciseIdIndex = cursor.getColumnIndex("exercise_id");
        int exerciseDatetimeIndex = cursor.getColumnIndex("exercise_datetime");
        int durationIndex = cursor.getColumnIndex("duration");
        int quantityIndex = cursor.getColumnIndex("quantity");
        String pattern = "yyyy-MM-dd'T'HH:mm";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        while(cursor.moveToNext()){
            exerciseRecord=new ExerciseRecord(cursor.getInt(recordIdIndex),
                    cursor.getInt(userIdIndex),cursor.getInt(exerciseIdIndex),LocalDateTime.parse(cursor.getString(exerciseDatetimeIndex),formatter),
                    cursor.getInt(durationIndex),cursor.getInt(quantityIndex)
            );
            list.add(exerciseRecord);
        }
        sqLiteDatabase.close();
        return list;
    }
}
