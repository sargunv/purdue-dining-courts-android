package me.sargunvohra.android.purduediningcourts.service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.pushtorefresh.storio.sqlite.SQLiteTypeMapping;
import com.pushtorefresh.storio.sqlite.StorIOSQLite;
import com.pushtorefresh.storio.sqlite.impl.DefaultStorIOSQLite;
import com.pushtorefresh.storio.sqlite.operations.delete.DefaultDeleteResolver;
import com.pushtorefresh.storio.sqlite.operations.get.DefaultGetResolver;
import com.pushtorefresh.storio.sqlite.operations.put.DefaultPutResolver;
import com.pushtorefresh.storio.sqlite.queries.DeleteQuery;
import com.pushtorefresh.storio.sqlite.queries.InsertQuery;
import com.pushtorefresh.storio.sqlite.queries.Query;
import com.pushtorefresh.storio.sqlite.queries.UpdateQuery;

import me.sargunvohra.android.purduediningcourts.model.dining.item.Item;

public class StorageHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "dining_storage";
    private static final int DB_VERSION = 1;

    private static final String TABLE_FAVORITES = "favorites";

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";

    private StorIOSQLite db;

    public StorageHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        db = DefaultStorIOSQLite.builder()
                .sqliteOpenHelper(this)
                .addTypeMapping(Item.class, SQLiteTypeMapping.<Item>builder()
                        .putResolver(new DefaultPutResolver<Item>() {
                            @NonNull
                            @Override
                            protected InsertQuery mapToInsertQuery(@NonNull Item object) {
                                return InsertQuery.builder()
                                        .table(TABLE_FAVORITES)
                                        .build();
                            }

                            @NonNull
                            @Override
                            protected UpdateQuery mapToUpdateQuery(@NonNull Item object) {
                                return UpdateQuery.builder()
                                        .table(TABLE_FAVORITES)
                                        .where(KEY_ID + " = ?")
                                        .whereArgs(object.getID())
                                        .build();
                            }

                            @NonNull
                            @Override
                            protected ContentValues mapToContentValues(@NonNull Item object) {
                                final ContentValues contentValues = new ContentValues();
                                contentValues.put(KEY_ID, object.getID());
                                contentValues.put(KEY_NAME, object.getName());
                                return contentValues;
                            }
                        })
                        .getResolver(new DefaultGetResolver<Item>() {
                            @NonNull
                            @Override
                            public Item mapFromCursor(@NonNull Cursor cursor) {
                                Item i = new Item();
                                i.setID(cursor.getString(0));
                                i.setName(cursor.getString(1));
                                return i;
                            }
                        })
                        .deleteResolver(new DefaultDeleteResolver<Item>() {
                            @NonNull
                            @Override
                            protected DeleteQuery mapToDeleteQuery(@NonNull Item object) {
                                return DeleteQuery.builder()
                                        .table(TABLE_FAVORITES)
                                        .where(KEY_ID + " = ?")
                                        .whereArgs(object.getID())
                                        .build();
                            }
                        })
                        .build())
                .build();
    }

    @Override
    public void onCreate(@NonNull SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE =String.format(
                "CREATE TABLE %s (%s, %s)",
                TABLE_FAVORITES,
                KEY_ID + " TEXT PRIMARY KEY",
                KEY_NAME + " TEXT"
        );
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(@NonNull SQLiteDatabase db, int oldVersion, int newVersion) {}

    public boolean isFavorite(String itemId) {
        return db.get()
                .listOfObjects(Item.class)
                .withQuery(Query.builder()
                        .table(TABLE_FAVORITES)
                        .where(KEY_ID + " = ?")
                        .whereArgs(itemId)
                        .build())
                .prepare()
                .executeAsBlocking()
                .size() > 0;
    }

    public void setFavorite(Item item) {
        db.put().object(item).prepare().executeAsBlocking();
    }

    public void unsetFavorite(Item item) {
        db.delete().object(item).prepare().executeAsBlocking();
    }
}
