package dev.hugozammit.roomdatabase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FriendDao {

    @Query("select * from friend")
    fun getAllFriends() : List<Friend>

    @Insert
    fun insertFriend(friend: Friend)

    @Delete
    fun deleteFriend(friend: Friend)
}