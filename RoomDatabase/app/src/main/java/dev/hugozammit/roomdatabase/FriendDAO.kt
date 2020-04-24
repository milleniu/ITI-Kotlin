package dev.hugozammit.roomdatabase

import androidx.room.*

@Dao
interface FriendDao {

    @Query("select * from friend")
    fun getAllFriends() : List<Friend>

    @Insert
    fun insertFriend(friend: Friend)

    @Update
    fun updateFriend(friend: Friend)

    @Delete
    fun deleteFriend(friend: Friend)
}