package ssjk.cafein;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.widget.ThemedSpinnerAdapter;
import android.util.Log;

/**
 * Created by wqe13 on 2016-11-24.
 */

class SuggestionsDatabase{

    private final static String TABLE_SUGGESTION = "TABLE_SUGGESTION";
    private final static String FIELD_ID = "_id";
    final static String FIELD_SUGGESTION = "FIELD_SUGGESTION";
    final static String FIELD_TYPE = "FIELD_TYPE";

    DatabaseOpenHelper database = new DatabaseOpenHelper();
    SuggestionsDatabase(Context context){

    }
    //when I want to add a new suggestion
/*
    public long insertSuggestion(String text)
    {
        ContentValues values = new ContentValues();
        values.put(FIELD_SUGGESTION, text);
        return db.insert(TABLE_SUGGESTION, null, values);
    }
*/
    Cursor getSuggestions(String text)
    {
        return database.db.query(TABLE_SUGGESTION, new String[] {FIELD_ID, FIELD_TYPE, FIELD_SUGGESTION},
                FIELD_SUGGESTION+" LIKE '"+ text +"%'", null, null, null, null);
    }


}