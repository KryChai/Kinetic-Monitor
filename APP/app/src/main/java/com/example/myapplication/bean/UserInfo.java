package com.example.myapplication.bean;
public class UserInfo {
    private int userId;private String nickname;private String username;private String password;
    private String phoneNumber;private double height;private double weight;private double targetWeight;
    private int age;private String gender;private int pushUpGoal;private int squatGoal;
    private int pullUpGoal;public UserInfo() {
    }
    public UserInfo(int userId, String nickname, String username, String password, String phoneNumber, double height, double weight, double targetWeight, int age, String gender, int pushUpGoal, int squatGoal, int pullUpGoal) {
        this.userId=userId;this.nickname = nickname;
        this.username = username;this.password = password;
        this.phoneNumber = phoneNumber;this.height = height;
        this.weight = weight;this.targetWeight = targetWeight;
        this.age = age;this.gender = gender;
        this.pushUpGoal = pushUpGoal;this.squatGoal = squatGoal;
        this.pullUpGoal = pullUpGoal;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public double getHeight() {
        return height;
    }
    public void setHeight(double height) {
        this.height = height;
    }
    public double getWeight() {
        return weight;
    }
    public void setWeight(double weight) {
        this.weight = weight;
    }
    public double getTargetWeight() {
        return targetWeight;
    }
    public void setTargetWeight(double targetWeight) {
        this.targetWeight = targetWeight;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public int getPushUpGoal() {
        return pushUpGoal;
    }
    public void setPushUpGoal(int pushUpGoal) {
        this.pushUpGoal = pushUpGoal;
    }
    public int getSquatGoal() {
        return squatGoal;
    }
    public void setSquatGoal(int squatGoal) {
        this.squatGoal = squatGoal;
    }
    public int getPullUpGoal() {
        return pullUpGoal;
    }
    public void setPullUpGoal(int pullUpGoal) {
        this.pullUpGoal = pullUpGoal;
    }
    @Override
    public String toString() {
        return "UserInfo{" +
                "userId=" + userId +
                ", nickname='" + nickname + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                ", targetWeight=" + targetWeight +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", pushUpGoal=" + pushUpGoal +
                ", squatGoal=" + squatGoal +
                ", pullUpGoal=" + pullUpGoal +
                '}';
    }
}
