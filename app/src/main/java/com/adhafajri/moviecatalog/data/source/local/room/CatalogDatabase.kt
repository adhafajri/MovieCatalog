package com.adhafajri.moviecatalog.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.adhafajri.moviecatalog.data.source.local.entity.CatalogEntity
import com.adhafajri.moviecatalog.data.source.local.entity.JobEntity
import com.adhafajri.moviecatalog.data.source.local.entity.PersonEntity

@Database(
    entities = [CatalogEntity::class, PersonEntity::class, JobEntity::class],
    version = 1,
    exportSchema = false
)
abstract class CatalogDatabase : RoomDatabase() {
    abstract fun catalogDao(): CatalogDao

    companion object {
        @Volatile
        private var INSTANCE: CatalogDatabase? = null

        fun getInstance(context: Context): CatalogDatabase {
            if (INSTANCE == null) {
                synchronized(CatalogDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            CatalogDatabase::class.java, "Catalogs.db"
                        )
                            .build()
                    }
                }
            }
            return INSTANCE as CatalogDatabase
        }
    }
}