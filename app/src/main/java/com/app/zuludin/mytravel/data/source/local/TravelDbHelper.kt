package com.app.zuludin.mytravel.data.source.local

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.app.zuludin.mytravel.data.model.local.Favourite
import org.jetbrains.anko.db.*

class TravelDbHelper(context: Context) : ManagedSQLiteOpenHelper(context, "travelfav.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(
            Favourite.FAVORITE_TABLE, true,
            Favourite.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            Favourite.DATA_ID to TEXT + UNIQUE,
            Favourite.NAME to TEXT,
            Favourite.THUMBNAIL to TEXT,
            Favourite.CATEGORY to TEXT

        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(Favourite.FAVORITE_TABLE, true)
    }

    companion object {
        private var INSTANCE: TravelDbHelper? = null

        @Synchronized
        fun getInstance(context: Context): TravelDbHelper {
            if (INSTANCE == null) {
                INSTANCE = TravelDbHelper(context)
            }
            return INSTANCE as TravelDbHelper
        }
    }
}