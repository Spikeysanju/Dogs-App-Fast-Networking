package www.sanju.dogs

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.StringRequestListener
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.json.JSONObject
import www.sanju.dogs.Adapter.DogsAdapter
import www.sanju.dogs.Model.DogApi


class MainActivity : AppCompatActivity() {

    private val TAG = "MyApp"
    val imageList = ArrayList<DogApi>()


    private lateinit var dogsRV:RecyclerView
    private lateinit var searchText:EditText
    private lateinit var searchBtn:FloatingActionButton


    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //init views



        dogsRV = findViewById<RecyclerView>(R.id.dogsRV)
        searchText = findViewById<EditText>(R.id.searchDogs)
        searchBtn = findViewById<FloatingActionButton>(R.id.searchBtn)


        searchBtn.setOnClickListener {

            var name = searchText.text.toString()

            initDogs(name)
            Toast.makeText(this,name,Toast.LENGTH_LONG).show()
        }


        dogsRV.layoutManager = StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL)



        //initDogs()
    }

    private fun initDogs(name: String) {


        imageList.clear()

        AndroidNetworking.initialize(this)

        AndroidNetworking.get("https://dog.ceo/api/breed/$name/images")
            .setPriority(Priority.HIGH)
            .build()
            .getAsString(object : StringRequestListener {
                override fun onResponse(response: String?) {

                    Log.e("Dogs :", response.toString())


                    val res = JSONObject(response)
                    val one = res.getJSONArray("message")


                    for(i in 0 until one.length()){

                        val list = one.get(i)
                        imageList.add(
                            DogApi(list.toString())
                        )
                    }

                    dogsRV.adapter = DogsAdapter(this@MainActivity, imageList)


                }

                override fun onError(anError: ANError?) {
                    Log.i("_err", anError.toString())


                  //  Toast.makeText(this@MainActivity, " Output:$anError",Toast.LENGTH_LONG).show()

                }


            })
    }
}
