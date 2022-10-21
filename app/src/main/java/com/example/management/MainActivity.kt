package com.example.management

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.management.adapter.ItemViewAdapter
import com.example.management.model.requestItem

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView: RecyclerView = findViewById(R.id.list_item)
        var mydataset = mutableListOf<requestItem>()
        val ob1 = requestItem("Arpit", "Liquid","Industry","12/03/2022", R.drawable.gh,"16kg","library")
                mydataset.add(ob1)
        recyclerView.adapter = ItemViewAdapter(
            this,
            mydataset
        )
        recyclerView?.layoutManager =
            LinearLayoutManager(
               this,
                LinearLayoutManager.VERTICAL,
                false
            )

    }
}