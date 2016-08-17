package bscenter.tracnghiemlaptrinh.model.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import bscenter.tracnghiemlaptrinh.model.Level;
import bscenter.tracnghiemlaptrinh.model.objects.Question;

/**
 * Created by NIT Admin on 05/06/2016
 */

public class DatabaseAccess {

    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;

    /**
     * Private constructor to aboid object creation from outside classes.
     *
     * @param context
     */
    private DatabaseAccess(Context context) {
        this.openHelper = new DBConfig(context);
    }

    /**
     * Return a singleton instance of DatabaseAccess.
     *
     * @param context the Context
     * @return the instance of DabaseAccess
     */
    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    /**
     * Open the database connection.
     */
    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    /**
     * Close the database connection.
     */
    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    /**
     * Read all quotes from the database.
     *
     * @return a List of quotes
     */
    public List<Question> getGetQuestion(Level levelPackage) {
        List<Question> questions = new ArrayList<>();
        int limit = levelPackage == Level.easy ? 10 : levelPackage == Level.medium ? 15 : 20;
        Cursor cursor = database.rawQuery("SELECT * FROM question ORDER BY countQuery ASC LIMIT " + limit, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String question = cursor.getString(cursor.getColumnIndex("question"));
            String answerA = cursor.getString(cursor.getColumnIndex("answerA"));
            String answerB = cursor.getString(cursor.getColumnIndex("answerB"));
            String answerC = cursor.getString(cursor.getColumnIndex("answerC"));
            String answerD = cursor.getString(cursor.getColumnIndex("answerD"));
            String trueAnswer = cursor.getString(cursor.getColumnIndex("trueAnswer"));
            int level = cursor.getInt(cursor.getColumnIndex("level"));
//            int countQuery = cursor.getInt(cursor.getColumnIndex("countQuery"));
            Question aQuestion = new Question(id, question, answerA, answerB, answerC, answerD, trueAnswer, level);
            questions.add(aQuestion);
            cursor.moveToNext();
        }
        cursor.close();
        return questions;
    }

    public void addQuery(int questionId) {
        database.execSQL("UPDATE question SET countQuery = countQuery + 1 WHERE id = " + questionId);
        Cursor cursor = database.rawQuery("SELECT * FROM question WHERE id = " + questionId, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int countQuery = cursor.getInt(cursor.getColumnIndex("countQuery"));
            Log.d("NGHIA", "update view add = " + countQuery);
            cursor.moveToNext();
        }
        cursor.close();
    }
}