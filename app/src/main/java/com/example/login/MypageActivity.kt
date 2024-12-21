package com.example.login

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import com.example.login.R

class MypageActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_mypage)

        // TextViews for user details
        val tvUserID = findViewById<TextView>(R.id.userid)
        val tvUseremail = findViewById<TextView>(R.id.useremail)
        val tvUsername = findViewById<TextView>(R.id.username) // Assuming you use this to show the nickname

        // Load saved data from SharedPreferences
        val sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)
        val userId = sharedPreferences.getString("userID", "N/A")
        val useremail = sharedPreferences.getString("useremail", "N/A")
        val username = sharedPreferences.getString("username", "N/A")

        // Display user data
        tvUserID.text = "ID: $userId"
        tvUseremail.text = "Email: $useremail"
        tvUsername.text = "$username"

        // Buttons to navigate to other activities
        val logout = findViewById<Button>(R.id.logout)
        val gotoalarm = findViewById<ImageButton>(R.id.gotoalarm)
        val gotorecord = findViewById<ImageButton>(R.id.gotorecord)
        val gotocalendar = findViewById<ImageButton>(R.id.gotocalendar)
        val gotohome = findViewById<ImageButton>(R.id.gotohome)

        val menuButton: ImageButton = findViewById(R.id.menu)

        //아래는 그림들 네개 클릭하면 어디로 갈 지 거든?
        //잘 몰겠으면 언제든 카톡 줘 - jsh
        gotoalarm.setOnClickListener {
            val intent = Intent(this, AlarmActivity::class.java)
            startActivity(intent)
        }

        gotorecord.setOnClickListener {
            val intent = Intent(this, RecordActivity::class.java)
            startActivity(intent)
        }

        gotocalendar.setOnClickListener {
            val intent = Intent(this, CalendarActivity::class.java)
            startActivity(intent)
        }

        gotohome.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)


        // 메뉴 버튼 클릭 이벤트
        menuButton.setOnClickListener { v: View ->
            // PopupMenu 생성
            val popupMenu = PopupMenu(this, v)
            val inflater: MenuInflater = popupMenu.menuInflater
            inflater.inflate(R.menu.menu_dropdown, popupMenu.menu)

            // 메뉴 아이템 클릭 이벤트 처리
            popupMenu.setOnMenuItemClickListener { item: MenuItem ->
                when (item.itemId) {
                    R.id.menu_home -> {
                        // Home 동작
                        Toast.makeText(this, "Home Selected", Toast.LENGTH_SHORT).show()
                        true
                    }

                    R.id.menu_about -> {
                        // MyPage 동작
                        Toast.makeText(this, "MyPage Selected", Toast.LENGTH_SHORT).show()
                        true
                    }

                    else -> false
                }
            }

            // PopupMenu 표시
            popupMenu.show()
        }
        logout.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            Toast.makeText(this, "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show()
            startActivity(intent)
        }
    }
}
