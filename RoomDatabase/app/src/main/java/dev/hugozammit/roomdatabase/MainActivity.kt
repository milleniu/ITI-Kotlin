package dev.hugozammit.roomdatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_main.*
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
            firstName = UUID.randomUUID().toString(),
            rating = Random.Default.nextInt(10000)
        )
        database.friendDao().insertFriend(newFriend)

        var allFriends = database.friendDao().getAllFriends()

        val viewManager = LinearLayoutManager(this)
        val viewAdapter = FriendListAdapter(allFriends)
        viewAdapter.setItemClickListener(
            object: FriendListAdapter.ItemClickListener {
                override fun onFriendDelete(view: View, position: Int) {
                    val toRemove = viewAdapter.dataset[position]
                    database.friendDao().deleteFriend(toRemove)
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
