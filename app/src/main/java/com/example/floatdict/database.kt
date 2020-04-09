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
){
    public fun malayalamString(): String{
        return this.malayalam_definition
    }
}
//end of entity

@Dao
interface dataQuery{
    @Query("SELECT * FROM DictionaryWord WHERE english_word LIKE :title")
    fun getword(title:String):List<DictionaryWord>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDictionaryword(data:DictionaryWord)
}
//end of dao

@Database(entities = arrayOf(DictionaryWord::class),version = 1)
abstract class AppDatabase:RoomDatabase(){
    //initialising dao abstract methods
    abstract fun findMeaning():dataQuery
    companion object{
        fun getInstance(context: Context):AppDatabase{
            var instance = Room.databaseBuilder(context,AppDatabase::class.java,
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