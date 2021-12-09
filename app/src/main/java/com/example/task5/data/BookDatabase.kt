package com.example.task5.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Book::class,Author::class], version = 1, exportSchema = false)
abstract class BookDatabase: RoomDatabase(){
    abstract fun bookDao():BookDao
    companion object
    {
        @Volatile
        private var INSTANCE:BookDatabase?=null

        fun getDatabase(context: Context):BookDatabase
        {
            val tempInstance= INSTANCE
            if(tempInstance!=null)
            {
                return  tempInstance
            }
            synchronized(this)
            {
                val instance= Room.databaseBuilder(
                    context.applicationContext,
                    BookDatabase::class.java,
                    "task5"
                ).build()
                INSTANCE=instance
                return instance
            }
        }
    }
}