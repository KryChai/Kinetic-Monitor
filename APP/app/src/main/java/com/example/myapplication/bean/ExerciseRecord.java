package com.example.myapplication.bean;
import java.time.LocalDateTime;
public class ExerciseRecord {
    private int recordId;
    private int userId;
    private int exerciseId;
    private LocalDateTime exerciseDatetime;
    private int duration;
    private int quantity;
    public ExerciseRecord() {
    }
    public ExerciseRecord(int recordId, int userId, int exerciseId, LocalDateTime exerciseDatetime, int duration, int quantity) {
        this.recordId = recordId;
        this.userId = userId;
        this.exerciseId = exerciseId;
        this.exerciseDatetime = exerciseDatetime;
        this.duration = duration;
        this.quantity = quantity;
    }
    public int getRecordId() {
        return recordId;
    }
    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getExerciseId() {
        return exerciseId;
    }
    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }
    public LocalDateTime getExerciseDatetime() {
        return exerciseDatetime;
    }
    public void setExerciseDatetime(LocalDateTime exerciseDatetime) {
        this.exerciseDatetime = exerciseDatetime;
    }
    public int getDuration() {
        return duration;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    @Override
    public String toString() {
        return "ExerciseRecord{" +
                "recordId=" + recordId +
                ", userId=" + userId +
                ", exerciseId=" + exerciseId +
                ", exerciseDatetime=" + exerciseDatetime +
                ", duration=" + duration +
                ", quantity=" + quantity +
                '}';
    }
}
