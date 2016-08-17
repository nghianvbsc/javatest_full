package bscenter.tracnghiemlaptrinh.model.database;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by NIT Admin on 02/06/2016
 */

public class DBConfig extends SQLiteAssetHelper {
    private static final String DATABASE_NAME = "tnlt.db";
    private static final int DATABASE_VERSION = 1;

    public DBConfig(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
}
