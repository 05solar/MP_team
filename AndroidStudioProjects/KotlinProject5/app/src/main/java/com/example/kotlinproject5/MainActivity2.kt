package com.example.kotlinproject5

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
        insets}
        bind()
    }

    private fun bind() {
        var idx= 0

        val container1: ConstraintLayout = findViewById(R.id.container1)
        val container2: ConstraintLayout = findViewById(R.id.container2)

        val Btn1 : Button = findViewById(R.id.button4)
        val Btn2 : Button = findViewById(R.id.button5)
        val Btn3 : Button = findViewById(R.id.button6)
        val Btn4 : Button = findViewById(R.id.button7)
        val Btnd1 : Button = findViewById(R.id.button2)
        val Btnd2 : Button = findViewById(R.id.button3)

        Btn1.setOnClickListener{
            container2.visibility = View.INVISIBLE
            container1.visibility = View.VISIBLE
            Btnd1.setOnClickListener{ finish() }
        }
        Btn2.setOnClickListener{
            container1.visibility = View.INVISIBLE
            container2.visibility = View.VISIBLE
            Btnd2.setOnClickListener{ finish() }
        }
        Btn3.setOnClickListener{
            container2.visibility = View.INVISIBLE
            container1.visibility = View.VISIBLE
            Btnd1.setOnClickListener{ finish() }

        }
        Btn4.setOnClickListener{
            container2.visibility = View.INVISIBLE
            container1.visibility = View.VISIBLE
            Btnd1.setOnClickListener{ finish() }
        }
    }
}