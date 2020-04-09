package com.example.floatdict

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.floatdict.AppDatabase
import com.example.floatdict.AppDatabase.Companion.getInstance
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var answer:String = ""
        var instace = getInstance(this)
        searchButton.setOnClickListener(){
            var key = keyInput.text
            Log.i("MainActivity","Button clicked")
            if(key != null){
            var data = instace.findMeaning().getword(key.toString())
                answer=""
                for (items in data){
                    if(items.malayalam_definition==""){
                        answer="Not Found"
                    }
                answer=items.malayalam_definition+", "
            }
            ResultView.text=answer
            }
        }

        }

}
