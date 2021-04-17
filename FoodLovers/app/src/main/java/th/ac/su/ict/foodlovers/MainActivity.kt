package th.ac.su.ict.foodlovers

import android.content.Intent
import th.ac.su.ict.foodlovers.Utils.getJsonDataFromAsset
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*
import th.ac.su.ict.foodlovers.data.Food
import th.ac.su.ict.foodlovers.data.FoodAdapter

class MainActivity : AppCompatActivity() {

    var itemList:ArrayList<Food> = ArrayList<Food>()
    lateinit var arrayAdapter:ArrayAdapter<Food>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val jsonFileString =
                getJsonDataFromAsset(applicationContext,fileName = "food_data.json")

        //Log.i("ghjk", jsonFileString!!)

        val gson = Gson()
        val listItemType = object :TypeToken<ArrayList<Food>>(){}.type

        var FoodList : ArrayList<Food> = gson.fromJson(jsonFileString,listItemType)

//        Log.i("dataxx", FoodList[0].FoodName)

        itemList = FoodList

        val adapter = FoodAdapter(this@MainActivity,itemList)

        listMenu.adapter = adapter

        listMenu.setOnItemClickListener { parent, view, position, id ->

            var intent = Intent(this@MainActivity,DetailActivity2::class.java)
            intent.putExtra("title",itemList[position].Food)
            intent.putExtra("caption",itemList[position].Store)
            intent.putExtra("image",itemList[position].image)
            intent.putExtra("Foodinfo",itemList[position].Foodinfo)
            intent.putExtra("star",itemList[position].star)


            startActivity(intent)
        }

    }
}
