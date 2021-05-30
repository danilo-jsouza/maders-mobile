package dev.danilo.maders

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dev.danilo.maders.dao.PortionDAO
import dev.danilo.maders.model.Portion

class DatabaseManager(private val context: Context) {

    private val dbInstance: MadersDatabase by lazy {
        Room.databaseBuilder(
            context.applicationContext,
            MadersDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    fun getPortionDAO(): PortionDAO {
        return dbInstance.disciplinaDAO()
    }

    companion object {
        private const val DATABASE_NAME = "maders.sqlite"
    }
}

@Database(entities = [Portion::class], version = 1)
abstract class MadersDatabase : RoomDatabase() {
    abstract fun disciplinaDAO(): PortionDAO
}
