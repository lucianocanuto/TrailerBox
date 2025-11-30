package com.btcodans.trailerbox.data.local

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MovieDatabase(context: Context)
    : SQLiteOpenHelper(context,"movie.db",null,1){
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(
            """
            CREATE TABLE favorites (
                id INTEGER PRIMARY KEY,
                title TEXT,
                poster_path TEXT,
                overview TEXT,
                vote_average TEXT
            )
            """
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS favorites")
        onCreate(db)
    }
}