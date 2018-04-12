package com.example.alvaroortega.convertidordeunidades

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
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

    var binario=false
    var binario2=false

    var hexadecimal=false
    var hexadecimal2=false

    var octal=false
    var octal2=false

    var decimal=false
    var decimal2=false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        botones1= arrayListOf( Binario, Octal,Hexadecimal,Decimal)
        botones2= arrayListOf( Binario2, Octal2,Hexadecimal2,Decimal2)
        botones()
        getJson()

        Covertir.setOnClickListener{
            verificar()
        }

    }

    fun verificar(){
        val aConvertir = resultado.getText().toString()
        if(binario && octal2){
            bin_oct(aConvertir)

        }else if(binario && hexadecimal2){
            bin_hex(aConvertir)

        }else if(binario && decimal2){
            bin_dec(aConvertir)

        }else if(octal && binario2){
            oct_bin(aConvertir)

        }else if(octal && hexadecimal2){
            oct_hex(aConvertir)

        }else if(octal && decimal2) {
            oct_dec(aConvertir)

        }else if(hexadecimal && binario2){
            hex_bin(aConvertir)

        }else if(hexadecimal && octal2){
            hex_oct(aConvertir)

        }else if(hexadecimal && decimal2){
            hex_dec(aConvertir)

        }else if(decimal && binario2){
            dec_bin(aConvertir)

        }else if(decimal && octal2){
            dec_oct(aConvertir)

        }else if(decimal && hexadecimal2){
            dec_hex(aConvertir)

        }
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

            binario=true
            hexadecimal=false
            octal=false
            decimal=false
        }
        Binario2.setOnClickListener{
            Binario2.setBackgroundColor(Color.GREEN)
            Hexadecimal2.setBackgroundColor(Color.GRAY)
            Octal2.setBackgroundColor(Color.GRAY)
            Decimal2.setBackgroundColor(Color.GRAY)

            binario2=true
            hexadecimal2=false
            octal2=false
            decimal2=false
        }
        Hexadecimal.setOnClickListener{
            Binario.setBackgroundColor(Color.GRAY)
            Hexadecimal.setBackgroundColor(Color.GREEN)
            Octal.setBackgroundColor(Color.GRAY)
            Decimal.setBackgroundColor(Color.GRAY)

            binario=false
            hexadecimal=true
            octal=false
            decimal=false
        }
        Hexadecimal2.setOnClickListener{
            Binario2.setBackgroundColor(Color.GRAY)
            Hexadecimal2.setBackgroundColor(Color.GREEN)
            Octal2.setBackgroundColor(Color.GRAY)
            Decimal2.setBackgroundColor(Color.GRAY)

            binario2=false
            hexadecimal2=true
            octal2=false
            decimal2=false

        }
        Octal.setOnClickListener{
            Binario.setBackgroundColor(Color.GRAY)
            Hexadecimal.setBackgroundColor(Color.GRAY)
            Octal.setBackgroundColor(Color.GREEN)
            Decimal.setBackgroundColor(Color.GRAY)

            binario=false
            hexadecimal=false
            octal=true
            decimal=false

        }
        Octal2.setOnClickListener{
            Binario2.setBackgroundColor(Color.GRAY)
            Hexadecimal2.setBackgroundColor(Color.GRAY)
            Octal2.setBackgroundColor(Color.GREEN)
            Decimal2.setBackgroundColor(Color.GRAY)

            binario2=false
            hexadecimal2=false
            octal2=true
            decimal2=false

        }
        Decimal.setOnClickListener{
            Binario.setBackgroundColor(Color.GRAY)
            Hexadecimal.setBackgroundColor(Color.GRAY)
            Octal.setBackgroundColor(Color.GRAY)
            Decimal.setBackgroundColor(Color.GREEN)

            binario=false
            hexadecimal=false
            octal=false
            decimal=true
        }
        Decimal2.setOnClickListener{
            Binario2.setBackgroundColor(Color.GRAY)
            Hexadecimal2.setBackgroundColor(Color.GRAY)
            Octal2.setBackgroundColor(Color.GRAY)
            Decimal2.setBackgroundColor(Color.GREEN)

            binario2=false
            hexadecimal2=false
            octal2=false
            decimal2=true
        }
    }
    fun getJson() {
        val random = Random()
        var num = random.nextInt(90 -1)+1
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

    fun dec_bin(valor: String) {
        var valor = valor
        val dec = Integer.parseInt(valor)
        valor = Integer.toBinaryString(dec)
        prueba.text="Resultado: "+valor.toString()
    }

    fun dec_hex(valor: String) {
        var valor = valor
        val dec = Integer.parseInt(valor)
        valor = Integer.toHexString(dec)
        prueba.text="Resultado: "+valor.toString()
    }

    fun dec_oct(valor: String) {
        var valor = valor
        val dec = Integer.parseInt(valor)
        valor = Integer.toOctalString(dec)
        prueba.text="Resultado: "+valor.toString()
    }

    fun bin_dec(valor: String) {

        val dec = Integer.parseInt(valor, 2)
        prueba.text="Resultado: "+dec.toString()
    }

    fun bin_hex(valor: String) {
        var valor = valor
        val dec = Integer.parseInt(valor, 2)
        valor = Integer.toHexString(dec)
        prueba.text="Resultado: "+valor.toString()
    }

    fun bin_oct(valor: String) {
        var valor = valor
        val dec = Integer.parseInt(valor, 2)
        valor = Integer.toOctalString(dec)
        prueba.text="Resultado: "+valor.toString()
    }

    fun hex_dec(valor: String) {
        val dec = Integer.parseInt(valor, 16)
        prueba.text="Resultado: "+valor.toString()
    }

    fun hex_bin(valor: String) {
        var valor = valor
        val dec = Integer.parseInt(valor, 16)
        valor = Integer.toBinaryString(dec)
        prueba.text="Resultado: "+valor.toString()
    }

    fun hex_oct(valor: String) {
        var valor = valor
        val dec = Integer.parseInt(valor, 16)
        valor = Integer.toOctalString(dec)
        prueba.text="Resultado: "+valor.toString()
    }

    fun oct_dec(valor: String) {
        val dec = Integer.parseInt(valor, 8)

        prueba.text="Resultado: "+valor.toString()
    }

    fun oct_bin(valor: String) {
        var valor = valor
        val dec = Integer.parseInt(valor, 8)
        valor = Integer.toBinaryString(dec)
        prueba.text="Resultado: "+valor.toString()
    }

    fun oct_hex(valor: String) {
        var valor = valor
        val dec = Integer.parseInt(valor, 8)
        valor = Integer.toHexString(dec)
        prueba.text="Resultado: "+valor.toString()
    }
}
