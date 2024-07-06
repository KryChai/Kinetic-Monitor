package com.example.myapplication.db;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.example.myapplication.bean.ExerciseType;
import java.util.ArrayList;
import java.util.List;
public class ExerciseTypeHelper extends SQLiteOpenHelper {
    public ExerciseTypeHelper(@Nullable Context context) {
        super(context, "sport.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
    public String insertExerciseType(ExerciseType exerciseType)
    {
        ContentValues cv=new ContentValues();
        cv.put("exercise_id",exerciseType.getTypeId());
        cv.put("exercise_name",exerciseType.getTypeName());
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        long insert=sqLiteDatabase.insert("Exercise_Type","exercise_id",cv);
        if(insert==-1)
        {return "fail";}
        sqLiteDatabase.close();
        return "success";
    }
    public String deleteExerciseType(ExerciseType exerciseType)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        int delete=sqLiteDatabase.delete("Exercise_Type","exercise_id"+"=?",new String[]{String.valueOf(exerciseType.getTypeId())});
        sqLiteDatabase.close();

        if(delete==0)
            return "false";
        return "success";
    }
    public String updateExerciseType(ExerciseType exerciseType)
    {
        ContentValues cv=new ContentValues();
        cv.put("exercise_id",exerciseType.getTypeId());
        cv.put("exercise_name",exerciseType.getTypeName());
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        int update= sqLiteDatabase.update("Exercise_Type",cv,"exercise_id"+"=?",new String[]{String.valueOf(exerciseType.getTypeId())});
        if(update==0)
        {
            return "fail";
        }
        sqLiteDatabase.close();
        return "success";
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public List<ExerciseType> selectAllExerciseType(){
        List<ExerciseType>list=new ArrayList<>();
        String sql="SELECT * from Exercise_Type;";
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery(sql,null);
        ExerciseType exerciseType;
        int recordIdIndex = cursor.getColumnIndex("exercise_id");
        int userIdIndex = cursor.getColumnIndex("exercise_name");
        while(cursor.moveToNext()){
            exerciseType=new ExerciseType(cursor.getInt(recordIdIndex),cursor.getString(userIdIndex)
            );
            list.add(exerciseType);
        }
        sqLiteDatabase.close();
        return list;
    }
}
