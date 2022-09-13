package hr.algebra.futurama.dao

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import hr.algebra.futurama.model.Item


private const val DB_NAME = "items.db"
private const val DB_VERSION = 1
private const val ITEMS = "items"

private val CREATE =
    "create table $ITEMS(" +
            "${Item::_id.name} integer primary key autoincrement, " +
            "${Item::Species.name}text not null, " +
            "${Item::Age.name}text not null, " +
            "${Item::Planet.name}text not null, " +
            "${Item::PicUrl.name}text not null, " +
            "${Item::Name.name}text not null, " +
            "${Item::read.name}integer not null " +
            ")"

private const val DROP = "drop table $ITEMS"

class FuturamaSqlHelper(
    context: Context?,
) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION), FuturamaRepository {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(DROP)
        onCreate(db)
    }

    override fun delete(selection: String?, selectionArgs: Array<String>?) =
        writableDatabase.delete(ITEMS, selection, selectionArgs)

    override fun update(
        values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?
    ) = writableDatabase.update(ITEMS, values, selection, selectionArgs)

    override fun insert(values: ContentValues?) = writableDatabase.insert(ITEMS, null, values)

    override fun query(
        projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor? =
        readableDatabase.query(ITEMS, projection, selection, selectionArgs, null, null, sortOrder)
}