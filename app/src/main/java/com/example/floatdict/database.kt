package com.example.floatdict
import android.content.Context
import android.util.Log
import androidx.room.*

@Entity
data class DictionaryWord(
    @PrimaryKey(autoGenerate = true)
    var ID: Int ? =null,
    @ColumnInfo
    var english_word : String = "",
    var part_of_speech : String = "",
    var malayalam_definition : String = ""
) {}
//end of entity

@Dao
interface DataQuery {
    @Query("SELECT * FROM DictionaryWord WHERE english_word LIKE :title")
    fun getword(title:String):List<DictionaryWord>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDictionaryword(data:DictionaryWord)
}
//end of dao

@Database(entities = [DictionaryWord::class], version = 1)
abstract class AppDatabase:RoomDatabase(){
    //initialising dao abstract methods
    abstract fun findMeaning(): DataQuery
    companion object{
        fun getInstance(context: Context):AppDatabase{
            val instance = Room.databaseBuilder(
                context, AppDatabase::class.java,
                "TheDatabase2"
            ).
            createFromAsset("databases/dictionary.db")
                //to be removed once courutine is set nghaa
                .allowMainThreadQueries()
                .build()
            Log.i("MainActivity","database sync completed")
            return instance
        }
    }
}