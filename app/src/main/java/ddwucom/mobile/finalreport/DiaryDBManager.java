package ddwucom.mobile.finalreport;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class DiaryDBManager {
    DiaryDBHelper diaryDBHelper = null;
    Cursor cursor = null;

    LocalDate localDate;
    DateTimeFormatter formatter;

    public DiaryDBManager(Context context) {
        diaryDBHelper = new DiaryDBHelper(context);
    }

    //Create
    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean addNewDiary(Diary newDiary) {
        SQLiteDatabase db = diaryDBHelper.getWritableDatabase();
        ContentValues val = new ContentValues();

        val.put(diaryDBHelper.COL_TITLE, newDiary.getTitle());
        val.put(diaryDBHelper.COL_FEELING, newDiary.getFeeling());
        val.put(diaryDBHelper.COL_WEATHER, newDiary.getWeather());
        val.put(diaryDBHelper.COL_DETAIL, newDiary.getDetail());
        val.put(diaryDBHelper.COL_PIC, newDiary.getPicture());

        localDate = LocalDate.now();
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = localDate.format(formatter);
        val.put(diaryDBHelper.COL_DATE, date);

        long count = db.insert(diaryDBHelper.TABLE_NAME, null, val);

        if(count > 0) return true;
        else return false;
    }

    //Read
    @RequiresApi(api = Build.VERSION_CODES.O)
    public ArrayList<Diary> getAllDiary() {
        ArrayList<Diary> diaryArrayList = new ArrayList<>();
        SQLiteDatabase db = diaryDBHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + diaryDBHelper.TABLE_NAME, null);

        while (cursor.moveToNext()) {
            long id = cursor.getInt(0);
            String title = cursor.getString(1);
            String feeling = cursor.getString(2);
            String weather = cursor.getString(3);
            String date = cursor.getString(4);
            String detail = cursor.getString(5);
            int picture = cursor.getInt(6);

            diaryArrayList.add(new Diary(id, title, feeling, weather, date, detail, picture));
        }

        cursor.close();
        diaryDBHelper.close();
        return diaryArrayList;
    }
    //Update
    public boolean modifyDiary(Diary diary) {
        SQLiteDatabase db = diaryDBHelper.getWritableDatabase();
        ContentValues row = new ContentValues();

        row.put(diaryDBHelper.COL_TITLE, diary.getTitle());
        row.put(diaryDBHelper.COL_FEELING, diary.getFeeling());
        row.put(diaryDBHelper.COL_WEATHER, diary.getWeather());
        row.put(diaryDBHelper.COL_DETAIL, diary.getDetail());
        row.put(diaryDBHelper.COL_PIC, diary.getPicture());
        row.put(diaryDBHelper.COL_DATE, diary.getDate());

        String whereClause = diaryDBHelper.COL_ID + "=?";
        String[] whereArgs = new String[] { String.valueOf(diary.get_id()) };

        int result = db.update(diaryDBHelper.TABLE_NAME, row, whereClause, whereArgs);

        diaryDBHelper.close();

        if(result > 0) return true;
        else return false;
    }

    //Delete
    public boolean removeDiary(long _id) {
        SQLiteDatabase db = diaryDBHelper.getWritableDatabase();

        String whereClause = diaryDBHelper.COL_ID + "=?";
        String[] whereArgs = new String[] { String.valueOf(_id) };

        int result = db.delete(diaryDBHelper.TABLE_NAME, whereClause, whereArgs);

        diaryDBHelper.close();

        if(result > 0) return true;
        else return false;
    }
}
