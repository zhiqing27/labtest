package sqliteDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import model.DBmodel;

public class DBCalculator extends SQLiteOpenHelper {


    public static final String dbName = "dbMyCalculator";
    public static final String tblNameCalculator = "calculator";
    public static final String colSubCode = "Subject_code";
    public static final String colSubName = "Subject_name";
    public static final String colCreditHour = "Credit_hour";
    public static final String colGrade = "Grade";
    public static final String colSubId = "subject_id";

    public static final String strCrtTblCalculator = "CREATE TABLE " + tblNameCalculator + "(" + colSubId +" INTEGER PRIMARY KEY, "+ colSubCode +
            " TEXT," + colSubName + "TEXT," + colCreditHour + "INTEGRR" + colGrade + "TEXT)";
    public static final String strDrpTblCalculator = "DROP TABLE IF EXISTS " + tblNameCalculator;


    public DBCalculator(Context context) {
        super(context, dbName, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(strCrtTblCalculator);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(strDrpTblCalculator);
        onCreate(db);

    }

    public float fnInsertCalculator(DBmodel myCalcuator) {
        float retresult = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(colSubCode, myCalcuator.getStrSubjectCode());
        values.put(colSubName, myCalcuator.getStrSubjectName());
        values.put(colCreditHour, myCalcuator.getStrCreditHour());
        values.put(colGrade, myCalcuator.getStrGrade());

        retresult = db.insert(tblNameCalculator, null, values);
        return retresult;


    }

    public int fnUpdateCalculator(DBmodel myCalculator) {
        int retResult = 0;
        ContentValues values = new ContentValues();
        values.put(colSubName, myCalculator.getStrSubjectName());
        values.put(colCreditHour, myCalculator.getStrCreditHour());
        values.put(colGrade, myCalculator.getStrGrade());
        String[] argg = {String.valueOf(myCalculator.getStrSubjectCode())};
        this.getWritableDatabase().update(tblNameCalculator, values, colSubCode + "=?", argg);
        return retResult;


    }

    public DBmodel fnGetCalculator(int intSubCode) {
        DBmodel modelDb = new DBmodel();
        String strSelQry = "Select * from" + tblNameCalculator + "where" + colSubCode + "=" + intSubCode;
        Cursor cursor = this.getReadableDatabase().rawQuery(strSelQry, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        modelDb.setStrSubjectName(cursor.getString(cursor.getColumnIndex(colSubName)));
        modelDb.setStrCreditHour(cursor.getInt(cursor.getColumnIndex(colCreditHour)));
        modelDb.setStrGrade(cursor.getString(cursor.getColumnIndex(colGrade)));

        return modelDb;

    }


    public List<DBmodel> fnGetAllCalculator() {
        List<DBmodel> listCal = new ArrayList<>();
        String strSelAll = "Select *from  " + tblNameCalculator;
        Cursor cursor = this.getReadableDatabase().rawQuery(strSelAll, null);
        if (cursor.moveToFirst()) {
            do {
                DBmodel dBmodel = new DBmodel();
                dBmodel.setStrSubjectName(cursor.getString(cursor.getColumnIndex(colSubName)));
                dBmodel.setStrCreditHour(cursor.getInt(cursor.getColumnIndex(colCreditHour)));
                dBmodel.setStrGrade(cursor.getString(cursor.getColumnIndex(colGrade)));

            }
            while (cursor.moveToNext());

        }
        return listCal;
    }
}


