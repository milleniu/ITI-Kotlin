package dev.hugozammit.roomdatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
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

        var allFriends = database.friendDao().getAllFriends()

        val viewManager = LinearLayoutManager(this)
        val viewAdapter = FriendListAdapter(allFriends)
        fun refreshAdapter() = viewAdapter.updateData(database.friendDao().getAllFriends())

        viewAdapter.setEventListener(
            object: FriendListAdapter.EventListener {
                override fun onFriendEdit(friend: Friend) {
                    database.friendDao().updateFriend(friend)
                    refreshAdapter()
                }

                override fun onFriendDelete(friend: Friend) {
                    database.friendDao().deleteFriend(friend)
                    refreshAdapter()
                }
            }
        )

        findViewById<RecyclerView>(R.id.recyclerView).apply {
                setHasFixedSize(true)
                layoutManager = viewManager
                adapter = viewAdapter
        }

        findViewById<Button>(R.id.bAdd).setOnClickListener {
            database.friendDao().insertFriend(Friend(firstName = "", rating = 0))
            refreshAdapter()
        }
    }
}
