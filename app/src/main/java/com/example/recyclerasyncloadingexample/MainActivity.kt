package com.example.recyclerasyncloadingexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            button.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE
            recyclerView.layoutManager = LinearLayoutManager(this)
//        recyclerView.adapter = RecyclerViewAdapter(createTestItems())
            recyclerView.adapter = RecyclerViewAsyncAdapter(createTestItems())
        }
    }

    private fun createTestItems(): List<TestItem> = mutableListOf<TestItem>().apply {
        repeat((1..7).count()) {
            add(
                TestItem(it - 1, "description $it", it, "title $it", it, it)
            )
        }
    }
}