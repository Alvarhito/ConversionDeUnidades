package com.example.alvaroortega.convertidordeunidades

import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Button
import com.github.kittinunf.fuel.android.core.Json
import kotlinx.android.synthetic.main.activity_main.*
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import org.json.JSONException
import org.json.JSONObject
import java.util.*

class MainActivity : AppCompatActivity() {
    var botones1=arrayListOf<Button>()
    var botones2=arrayListOf<Button>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        botones1= arrayListOf( Binario, Octal,Hexadecimal,Decimal)
        botones2= arrayListOf( Binario2, Octal2,Hexadecimal2,Decimal2)
        botones()
        getJson()

    }

    fun botones(){
        Binario.setBackgroundColor(Color.GRAY)
        Hexadecimal.setBackgroundColor(Color.GRAY)
        Octal.setBackgroundColor(Color.GRAY)
        Decimal.setBackgroundColor(Color.GRAY)

        Binario2.setBackgroundColor(Color.GRAY)
        Hexadecimal2.setBackgroundColor(Color.GRAY)
        Octal2.setBackgroundColor(Color.GRAY)
        Decimal2.setBackgroundColor(Color.GRAY)


        Binario.setOnClickListener{
            Binario.setBackgroundColor(Color.GREEN)
            Hexadecimal.setBackgroundColor(Color.GRAY)
            Octal.setBackgroundColor(Color.GRAY)
            Decimal.setBackgroundColor(Color.GRAY)
        }
        Binario2.setOnClickListener{
            Binario2.setBackgroundColor(Color.GREEN)
            Hexadecimal2.setBackgroundColor(Color.GRAY)
            Octal2.setBackgroundColor(Color.GRAY)
            Decimal2.setBackgroundColor(Color.GRAY)
        }
        Hexadecimal.setOnClickListener{
            Binario.setBackgroundColor(Color.GRAY)
            Hexadecimal.setBackgroundColor(Color.GREEN)
            Octal.setBackgroundColor(Color.GRAY)
            Decimal.setBackgroundColor(Color.GRAY)
        }
        Hexadecimal2.setOnClickListener{
            Binario2.setBackgroundColor(Color.GRAY)
            Hexadecimal2.setBackgroundColor(Color.GREEN)
            Octal2.setBackgroundColor(Color.GRAY)
            Decimal2.setBackgroundColor(Color.GRAY)

        }
        Octal.setOnClickListener{
            Binario.setBackgroundColor(Color.GRAY)
            Hexadecimal.setBackgroundColor(Color.GRAY)
            Octal.setBackgroundColor(Color.GREEN)
            Decimal.setBackgroundColor(Color.GRAY)

        }
        Octal2.setOnClickListener{
            Binario2.setBackgroundColor(Color.GRAY)
            Hexadecimal2.setBackgroundColor(Color.GRAY)
            Octal2.setBackgroundColor(Color.GREEN)
            Decimal2.setBackgroundColor(Color.GRAY)

        }
        Decimal.setOnClickListener{
            Binario.setBackgroundColor(Color.GRAY)
            Hexadecimal.setBackgroundColor(Color.GRAY)
            Octal.setBackgroundColor(Color.GRAY)
            Decimal.setBackgroundColor(Color.GREEN)
        }
        Decimal2.setOnClickListener{
            Binario2.setBackgroundColor(Color.GRAY)
            Hexadecimal2.setBackgroundColor(Color.GRAY)
            Octal2.setBackgroundColor(Color.GRAY)
            Decimal2.setBackgroundColor(Color.GREEN)
        }
    }
    fun getJson() {
        val random = Random()
        var num = random.nextInt(20 -1)+1
        var url="https://jsonplaceholder.typicode.com/posts/"
        url+=num
        //an extension over string (support GET, PUT, POST, DELETE with httpGet(), httpPut(), httpPost(), httpDelete())
        url.httpGet().responseJson{ request, response, result ->
            //do something with response
            when (result) {
                is Result.Failure -> {
                    prueba.text="No hay internet"
                }
                is Result.Success -> {
                    val data = result.get()
                    Organizar(data)

                    //

                }
            }
        }
    }
    fun Organizar(info: Json){
        var aux: JSONObject = JSONObject()
        
        aux=info.obj()
        try {
            var auxi1=aux.getString("title")
            var auxi2=aux.getString("body")
            prueba.text=auxi1+": "+auxi2

        }catch (e: JSONException){
            e.printStackTrace()
        }
    }
}
