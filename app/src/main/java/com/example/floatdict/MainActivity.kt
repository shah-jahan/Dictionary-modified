package com.example.floatdict

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.floatdict.AppDatabase.Companion.getInstance
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private val dictionaryAdapter = DictionaryAdapter(ArrayList<DictionaryWord>())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerWord.layoutManager = LinearLayoutManager(this)
        recyclerWord.adapter = dictionaryAdapter


        val dbInstance = getInstance(this)
        searchButton.setOnClickListener() {
            val key = keyInput.text.toString()
            if (key == "") {
                keyInput.hint = "Enter something"
            } else {
                search(key, dbInstance)
            }
        }
    }

    private fun search(key: String, instance: AppDatabase) {
        val result = instance.findMeaning().getword(key)
        if (result.size != 0) {
            dictionaryAdapter.newdata(result)
        } else {
            val falseData = DictionaryWord()
            dictionaryAdapter.newdata(listOf(falseData))
        }
    }

}


