package com.example.kms

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onButton1Clicked(v: View) {
        Toast.makeText(this, "확인1 버튼이 눌렸습니다.", Toast.LENGTH_LONG).show()
    }
    fun onButton2Clicked(v: View) {
        var myIntent = Intent(Intent.ACTION_VIEW, Uri.parse("http://m.naver.com"))
        startActivity(myIntent)
    }

    fun onButton3Clicked(v: View) {
        var myIntent = Intent(Intent.ACTION_VIEW, Uri.parse("tel:010-1000-1004"))
        startActivity(myIntent)
    }
}