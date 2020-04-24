package dev.hugozammit.roomdatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val database = Room
            .databaseBuilder(
                this,
                FriendDatabase::class.java, "friend_database"
            ).allowMainThreadQueries()
             .build()

        val allFriends = database.friendDao().getAllFriends()
        val viewManager = LinearLayoutManager( this )
        val viewAdapter = FriendListAdapter( allFriends );
        val recyclerView = findViewById<RecyclerView>(R.id.recylerView).apply {
                setHasFixedSize(true)
                layoutManager = viewManager
                adapter = viewAdapter
        }

        database.friendDao().insertFriend(Friend(firstName = "Bob", rating = 1000))
        d("room-database", "allFriend size? ${allFriends.size}")
    }
}
