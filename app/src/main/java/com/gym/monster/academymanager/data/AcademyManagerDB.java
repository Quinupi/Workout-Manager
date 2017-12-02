package com.gym.monster.academymanager.data;

import android.database.sqlite.SQLiteDatabase;
import android.content.Context;

/**
 * Created by victor on 19/03/17.
 */

public class AcademyManagerDB {
    private SQLiteDatabase db;

    public AcademyManagerDB(Context context){
        AcademyManagerCreateDB createDB = new AcademyManagerCreateDB(context);

        db = createDB.getWritableDatabase();
    }

    public SQLiteDatabase getDb(){
        return db;
    }
}
