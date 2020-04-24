package dev.hugozammit.roomdatabase

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Friend::class], version = 1)
abstract class FriendDatabase : RoomDatabase() {
    abstract fun friendDao(): FriendDao
}