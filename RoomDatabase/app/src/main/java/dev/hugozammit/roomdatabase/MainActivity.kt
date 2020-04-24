package dev.hugozammit.roomdatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import java.util.*
import kotlin.random.Random

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

        // Register a new user every time we start the app
        val newFriend = Friend(
            firstName = UUID.randomUUID().toString().slice(0..12 ),
            rating = 0
        )
        database.friendDao().insertFriend(newFriend)

        var allFriends = database.friendDao().getAllFriends()

        val viewManager = LinearLayoutManager(this)
        val viewAdapter = FriendListAdapter(allFriends)
        viewAdapter.setEventListener(
            object: FriendListAdapter.EventListener {
                override fun onFriendEdit(friend: Friend) {
                    database.friendDao().updateFriend(friend)
                    updateAdapter()
                }

                override fun onFriendDelete(friend: Friend) {
                    database.friendDao().deleteFriend(friend)
                    updateAdapter()
                }

                private fun updateAdapter() {
                    allFriends = database.friendDao().getAllFriends()
                    viewAdapter.updateData(allFriends)
                }
            }
        )

        findViewById<RecyclerView>(R.id.recylerView).apply {
                setHasFixedSize(true)
                layoutManager = viewManager
                adapter = viewAdapter
        }
    }
}
