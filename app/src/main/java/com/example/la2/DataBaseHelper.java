package com.example.la2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {


    public static final String STUDENT_TABLE = "STUDENT_TABLE";
    public static final String COLUMN_STUDENT_NAME = "STUDENT_NAME";
    public static final String COLUMN_STUDENT_SURNAME = "STUDENT_SURNAME";
    public static final String COLUMN_STUDENT_AGE = "STUDENT_AGE";
    public static final String COLUMN_STUDENT_PARENTNAME = "STUDENT_PARENTNAME";
    public static final String COLUMN_STUDENT_PARENTPHONE = "STUDENT_PARENTPHONE";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_LESSON_ID = "LESSON_ID";
    public static final String COLUMN_LESSON_STUDENT_NAME = "LESSON_STUDENT_NAME";
    public static final String COLUMN_LESSON_DATE = "LESSON_DATE";
    public static final String COLUMN_LESSON_TIME = "LESSON_TIME";
    public static final String LESSON_TABLE = "LESSON_TABLE";
    public static final String SCHEDULE_TABLE = "SCHEDULE_TABLE";
    public static final String COLUMN_SCHEDULE_ID = "SCHEDULE_ID";
    public static final String COLUMN_SCHEDULE_TIME = "SCHEDULE_TIME";
    public static final String COLUMN_SCHEDULE_DAY = "SCHEDULE_DAY";
    public static final String COLUMN_SCHEDULE_STUDENT = "SCHEDULE_STUDENT";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "Lessons.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTableStatement = "CREATE TABLE " + STUDENT_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_STUDENT_NAME + " TEXT," + COLUMN_STUDENT_SURNAME + " TEXT," + COLUMN_STUDENT_AGE + " INTEGER," + COLUMN_STUDENT_PARENTNAME + " TEXT," + COLUMN_STUDENT_PARENTPHONE + " INTEGER)";
        sqLiteDatabase.execSQL(createTableStatement);
        String createTableStatement2 = "CREATE TABLE " + LESSON_TABLE + " (" + COLUMN_LESSON_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_LESSON_STUDENT_NAME + " TEXT, " + COLUMN_LESSON_DATE + " TEXT, " + COLUMN_LESSON_TIME + " TEXT)";
        sqLiteDatabase.execSQL(createTableStatement2);
        String createTableStatement3 = "CREATE TABLE " + SCHEDULE_TABLE + " (" + COLUMN_SCHEDULE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_SCHEDULE_STUDENT + " TEXT, " + COLUMN_SCHEDULE_DAY + " TEXT, " + COLUMN_SCHEDULE_TIME + " TEXT)";
        sqLiteDatabase.execSQL(createTableStatement3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addOneToSchedule(ScheduleClass scheduleClass){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_SCHEDULE_STUDENT,scheduleClass.getStudentName());
        cv.put(COLUMN_SCHEDULE_DAY,scheduleClass.getDay());
        cv.put(COLUMN_SCHEDULE_TIME,scheduleClass.getTime());

        long insert = db.insert(SCHEDULE_TABLE, null, cv);
        if (insert == -1){
            return false;
        }
        else {
            return true;
        }

    }

    public boolean addOneStudent(Student student){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_STUDENT_NAME, student.getName());
        cv.put(COLUMN_STUDENT_SURNAME, student.getSurname());
        cv.put(COLUMN_STUDENT_AGE, student.getAge());
        cv.put(COLUMN_STUDENT_PARENTNAME, student.getParentName());
        cv.put(COLUMN_STUDENT_PARENTPHONE, student.getParentPhone());

        long insert = db.insert(STUDENT_TABLE, null, cv);
        if (insert == -1){
            return false;
        }
        else {
            return true;
        }

    }

    public boolean addOneLesson(Lesson lesson){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_LESSON_STUDENT_NAME, lesson.getStudentName());
        cv.put(COLUMN_LESSON_DATE, lesson.getDate());
        cv.put(COLUMN_LESSON_TIME, lesson.getTime());

        long insert = db.insert(LESSON_TABLE, null, cv);
        if (insert == -1){
            return false;
        }
        else {
            return true;
        }

    }

    public List<Student> getEveryone(){
        List<Student> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + STUDENT_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString,null);

        if (cursor.moveToFirst()){
            do {
                int studentID = cursor.getInt(0);
                String studentName = cursor.getString(1);
                String studentSurname = cursor.getString(2);
                int studentAge = cursor.getInt(3);
                String parentName = cursor.getString(4);
                long parentPhone = cursor.getLong(5);

                Student newStudent = new Student(studentID,studentName,studentSurname,studentAge, parentName, parentPhone);
                returnList.add(newStudent);
            } while (cursor.moveToNext());
        }
        else {
            //Something if failed
        }
        cursor.close();
        db.close();
        return returnList;
    }

    public List<Lesson> getEveryLesson(){
        List<Lesson> returnList1 = new ArrayList<>();

        String queryString = "SELECT * FROM " + LESSON_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString,null);

        if (cursor.moveToFirst()){
            do {
                int lessonID = cursor.getInt(0);
                String studentName = cursor.getString(1);
                String date = cursor.getString(2);
                String time = cursor.getString(3);

                Lesson newLesson = new Lesson(lessonID,studentName,date,time);
                returnList1.add(newLesson);
            } while (cursor.moveToNext());
        }
        else {
            //Something if failed
        }
        cursor.close();
        db.close();
        return returnList1;
    }

    public List<ScheduleClass> getEveryScheduleForDelete(String dayz){
        List<ScheduleClass> returnList2 = new ArrayList<>();

        String queryString = "SELECT * FROM " + SCHEDULE_TABLE+" WHERE "+ COLUMN_SCHEDULE_DAY + " = " + dayz + " ORDER BY " + COLUMN_SCHEDULE_TIME + " ASC";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString,null);

        if (cursor.moveToFirst()){
            do {
                int ScheduleID = cursor.getInt(0);
                String studentName = cursor.getString(1);
                String day = cursor.getString(2);
                String time = cursor.getString(3);

                ScheduleClass newSchedule = new ScheduleClass(ScheduleID,studentName,day,time);
                returnList2.add(newSchedule);
            } while (cursor.moveToNext());
        }
        else {
            //Something if failed
        }
        cursor.close();
        db.close();
        return returnList2;
    }

    public boolean DeleteOneLesson ( int index ){
        //finds customerModel in the DB. if found and deleted returns true, in not returns false

        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + LESSON_TABLE + " WHERE " + COLUMN_LESSON_ID + " = " +index;

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean DeleteOneSchedule ( int index ){
        //finds customerModel in the DB. if found and deleted returns true, in not returns false

        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + SCHEDULE_TABLE + " WHERE " + COLUMN_SCHEDULE_ID + " = " +index;

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean DeleteOneStudent ( int index ){
        //finds customerModel in the DB. if found and deleted returns true, in not returns false

        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + STUDENT_TABLE + " WHERE " + COLUMN_ID + " = " +index;

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()){
            return true;
        }
        else {
            return false;
        }
    }

    public String[] getSpinnerData(){
        List<String> returnList = new ArrayList<>();
        String queryString = "SELECT * FROM " + STUDENT_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString,null);

        if (cursor.moveToFirst()){
            do {
                int studentID = cursor.getInt(0);
                String studentName = cursor.getString(1);
                String studentSurname = cursor.getString(2);
                int studentAge = cursor.getInt(3);
                String parentName = cursor.getString(4);
                long parentPhone = cursor.getLong(5);

                returnList.add(studentName);
            } while (cursor.moveToNext());
        }
        else {
            //Something if failed
        }

        cursor.close();
        db.close();
        return returnList.toArray(new String[returnList.size()]);
    }

    public String getScheduleByDay(String day){
        List<ScheduleClass> returnList = new ArrayList<>();
        String result = "";

        String queryString = "SELECT * FROM " + SCHEDULE_TABLE+" WHERE "+ COLUMN_SCHEDULE_DAY + " = " + day + " ORDER BY " + COLUMN_SCHEDULE_TIME + " ASC";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()){
            do {
                int ID = cursor.getInt(0);
                String studentName = cursor.getString(1);
                String Day = cursor.getString(2);
                String time = cursor.getString(3);

                ScheduleClass newSchedule = new ScheduleClass(ID,studentName,Day,time);
                returnList.add(newSchedule);
                result = result.concat(newSchedule.toString());
            } while (cursor.moveToNext());
        }
        else {
            //Something if failed
        }
        cursor.close();
        db.close();

        return result.toString();
    }
}
