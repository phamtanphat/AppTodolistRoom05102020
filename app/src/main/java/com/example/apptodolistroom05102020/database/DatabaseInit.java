package com.example.apptodolistroom05102020.database;


import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {WordEntity.class}, version = 1)
public abstract class DatabaseInit extends RoomDatabase {
    public abstract WordDao wordDao;
    private static DatabaseInit databaseInit = null;

    public synchronized static DatabaseInit createDatabase(Context context) {
        if (databaseInit == null) {
            databaseInit = Room.databaseBuilder(
                    context,
                    DatabaseInit.class,
                    "Quanlytuvung.sql"
            ).build();
        }
        return databaseInit;
    }

}
