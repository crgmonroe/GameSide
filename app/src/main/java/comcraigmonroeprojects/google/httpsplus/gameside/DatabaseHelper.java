package comcraigmonroeprojects.google.httpsplus.gameside;

import android.content.ContentValues;
import android.content.Context;
import android.content.ContentValues.*;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.nfc.Tag;
import android.util.Log;
import android.util.StringBuilderPrinter;

/**
 * Created by cmonroe on 9/29/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "teams.db";
    public static final String TABLE_NAME = "teams_table";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TEAM_NAME = "team_name";
    public static final String COLUMN_TEAM_COLOR = "team_color";
    public static final int VERSION = 2;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //db.execSQL("create table " + TABLE_NAME +"(ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, COLOR TEXT)");
        //db.execSQL("create table " + TABLE_NAME +"(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_TEAM_NAME +" TEXT," + COLUMN_TEAM_COLOR + " TEXT)");
        String SQL_String = "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_TEAM_NAME + " TEXT," + COLUMN_TEAM_COLOR + " TEXT" + ");";
        Log.e("MyActivity","SQL String: " + SQL_String);
        db.execSQL(SQL_String);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
    onCreate(db);
    }

    //
    public boolean addTeam (String _team_name , String _team_color) {

        if(!_team_name.equals("")) {

            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues rowValues = new ContentValues();
            rowValues.put(COLUMN_TEAM_NAME, _team_name);
            rowValues.put(COLUMN_TEAM_COLOR, _team_color);
            long result = -1; // fill in the fail condition as default
            result = db.insert(TABLE_NAME, null, rowValues);

            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }
        return false;

    }

    // list teams
    public Cursor getAllTeams() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * FROM " + TABLE_NAME,null);
        return res;

    }


}
