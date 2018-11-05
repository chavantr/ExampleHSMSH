
package com.passion.hindismsm.Locally;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteQueryBuilder;

import java.util.ArrayList;
import java.util.List;


public class MyDatabase extends SQLiteAssetHelper {

    public MyDatabase(Context context, String name, CursorFactory factory,
                      int version) {
        super(context, name, factory, version);
    }

    public List<String> getQuates(String category) {
        List<String> lstQuotes = null;
        Cursor cursor = null;
        SQLiteDatabase db = getReadableDatabase();
        String[] where = {category.trim()};
        SQLiteQueryBuilder query = new SQLiteQueryBuilder();
        query.setTables("TblMalvaniSMS");
        if (!category.equalsIgnoreCase("0")) {
            cursor = query.query(db, null,
                    "cat"
                            + "=?", where, null, null, null);
        } else {
            cursor = query.query(db, null,
                    null
                    , null, null, null, null);
        }
        if (null != cursor && cursor.getCount() > 0) {
            lstQuotes = new ArrayList<>();
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                lstQuotes.add(cursor.getString(cursor.getColumnIndex("sSuvichar1")));
                cursor.moveToNext();
            }
        }
        return lstQuotes;
    }

    public int getCategoryId(String strCategory) {
        Cursor cursor = null;
        SQLiteDatabase db = getReadableDatabase();
        String[] where = {strCategory.trim()};
        SQLiteQueryBuilder query = new SQLiteQueryBuilder();
        query.setTables("phaktprem");
        cursor = query.query(db, null,
                "name"
                        + "=?", where, null, null, null);
        if (null != cursor && cursor.getCount() > 0) {
            cursor.moveToFirst();
            return cursor.getInt(cursor.getColumnIndex("cat"));
        }
        return 0;
    }

    public int saveMessage(String category, String message) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nSrNo", getLastInserted());
        values.put("sSuvichar1", message);
        values.put("cat", getCategoryId(category));
        long inserted = db.insert("TblMalvaniSMS", null, values);
        return (int) inserted;
    }


    public int getLastInserted() {
        SQLiteDatabase db = getReadableDatabase();
        int lastId = 0;
        String query = "SELECT nSrNo from TblMalvaniSMS order by nSrNo DESC limit 1";
        Cursor c = db.rawQuery(query, null);
        if (c != null && c.moveToFirst()) {
            lastId = (int) c.getLong(0);
        }
        return lastId + 1;
    }
}
