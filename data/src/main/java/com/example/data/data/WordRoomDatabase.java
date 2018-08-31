package com.example.data.data;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;
import android.support.annotation.NonNull;

import com.example.data.data.dao.WordDao;
import com.example.data.models.Word;

//When you modify the database schema, you'll need to update the version number and define how to handle migrations.
@Database(entities = {Word.class}, version = 5, exportSchema = false)
public abstract class WordRoomDatabase extends RoomDatabase {

    //list all the DAOs abstract function here so that they can be used in Repository
    public abstract WordDao wordDao();

    //make this class a Singleton class so that only one instance of this class can be used fot the database operation
    private static WordRoomDatabase INSTANCE;

    //return the same singleton instance every time
    public static WordRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (WordRoomDatabase.class) {
                if (INSTANCE == null) {
                    // Create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            WordRoomDatabase.class, "word_database")
//                            .addCallback(roomDatabaseCallback)
//                            .fallbackToDestructiveMigration()
                            .addMigrations(MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4, MIGRATION_4_5)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
//            database.execSQL("ALTER TABLE word_table " + " ADD COLUMN word_length INTEGER");
        }
    };


    static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
//            database.execSQL("ALTER TABLE word_table ADD COLUMN word_length INTEGER");
        }
    };

    static final Migration MIGRATION_3_4 = new Migration(3, 4) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE word_table ADD COLUMN word_length INTEGER NOT NUll DEFAULT -1");
        }
    };

    static final Migration MIGRATION_4_5 = new Migration(4, 5) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("DROP TABLE book_table");
        }
    };

//    private static RoomDatabase.Callback roomDatabaseCallback =
//            new RoomDatabase.Callback() {
//                @Override
//                public void onCreate(@NonNull SupportSQLiteDatabase db) {
//                    super.onCreate(db);
//                }
//
//                @Override
//                public void onOpen(@NonNull SupportSQLiteDatabase db) {
//                    super.onOpen(db);
////                    new PopulateDbAsync(INSTANCE).execute();
//                }
//            };
//
//    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {
//
//        private final WordDao wordDao;
//
//        PopulateDbAsync(WordRoomDatabase db) {
//            wordDao = db.wordDao();
//        }
//
//        @Override
//        protected Void doInBackground(final Void... params) {
////            wordDao.insert(new Word("Hello", 5));
////            wordDao.insert(new Word("World", 5));
////            wordDao.insert(new Word("India", 8));
//            return null;
//        }
//    }


}
// ROOM operation cannot be executed on the UI thread so AsyncTask executes them on different threads.

/*
Note that the queries you write in the Migration.migrate implementation are not compiled at run time,
unlike the queries from your DAOs. Make sure that you’re implementing tests for your migrations.
 */