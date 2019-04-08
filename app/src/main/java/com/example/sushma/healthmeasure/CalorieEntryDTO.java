package com.example.sushma.healthmeasure;

public class CalorieEntryDTO {

    private String Date ="";
    private String Breakfast = "";
    private float Breakfast_calorie = 0;
    private String Snack1 = "";
    private float Snack1_calorie = 0;
    private String Lunch = "";
    private float Lunch_calorie = 0;
    private String Snack2 = "";
    private float Snack2_calorie = 0;
    private String Dinner = "";
    private float Dinner_calorie = 0;

    public CalorieEntryDTO() {
    }

    public CalorieEntryDTO(String date) {
        Date = date;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getBreakfast() {
        return Breakfast;
    }

    public void setBreakfast(String breakfast) {
        Breakfast = breakfast;
    }

    public float getBreakfast_calorie() {
        return Breakfast_calorie;
    }

    public void setBreakfast_calorie(float breakfast_calorie) {
        Breakfast_calorie = breakfast_calorie;
    }

    public void setBreakfast_calorie(String breakfast_calorie) {
        Breakfast_calorie = Float.parseFloat(breakfast_calorie);
    }

    public String getSnack1() {
        return Snack1;
    }

    public void setSnack1(String snack1) {
        Snack1 = snack1;
    }

    public float getSnack1_calorie() {
        return Snack1_calorie;
    }

    public void setSnack1_calorie(float snack1_calorie) {
        Snack1_calorie = snack1_calorie;
    }

    public void setSnack1_calorie(String snack1_calorie) {
        Snack1_calorie = Float.parseFloat(snack1_calorie);
    }

    public String getLunch() {
        return Lunch;
    }

    public void setLunch(String lunch) {
        Lunch = lunch;
    }

    public float getLunch_calorie() {
        return Lunch_calorie;
    }

    public void setLunch_calorie(float lunch_calorie) {
        Lunch_calorie = lunch_calorie;
    }

    public void setLunch_calorie(String lunch_calorie) {
        Lunch_calorie = Float.parseFloat(lunch_calorie);
    }

    public String getSnack2() {
        return Snack2;
    }

    public void setSnack2(String snack2) {
        Snack2 = snack2;
    }

    public float getSnack2_calorie() {
        return Snack2_calorie;
    }

    public void setSnack2_calorie(float snack2_calorie) {
        Snack2_calorie = snack2_calorie;
    }

    public void setSnack2_calorie(String snack2_calorie) {
        Snack2_calorie = Float.parseFloat(snack2_calorie);
    }


    public String getDinner() {
        return Dinner;
    }

    public void setDinner(String dinner) {
        Dinner = dinner;
    }

    public float getDinner_calorie() {
        return Dinner_calorie;
    }

    public void setDinner_calorie(float dinner_calorie) {
        Dinner_calorie = dinner_calorie;
    }

    public void setDinner_calorie(String dinner_calorie) {
        Dinner_calorie = Float.parseFloat(dinner_calorie);
    }

    public float getTotal(){
        return (Breakfast_calorie+Snack1_calorie+Lunch_calorie+Snack2_calorie+Dinner_calorie);
    }
}
