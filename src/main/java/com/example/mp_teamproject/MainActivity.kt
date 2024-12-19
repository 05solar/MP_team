package com.example.mp_teamproject

import android.content.Intent
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.cardview.widget.CardView
import android.widget.Button
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat

class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 툴바 설정
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // DrawerLayout 설정
        drawerLayout = findViewById(R.id.drawer_layout)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // 네비게이션 메뉴 이벤트 처리
        val navigationView: NavigationView = findViewById(R.id.navigation_view)
        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_home -> {
                    startActivity(Intent(this, HomeActivity::class.java))
                }
                R.id.menu_settings -> {
                    startActivity(Intent(this, SettingsActivity::class.java))
                }
                R.id.menu_about -> {
                    startActivity(Intent(this, AboutActivity::class.java))
                }
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }

        // 새 루틴 추가 버튼 클릭 이벤트
        findViewById<Button>(R.id.btnAddRoutine).setOnClickListener {
            startActivity(Intent(this, AddRoutineActivity::class.java))
        }

        // 루틴 1 카드 클릭 이벤트
        findViewById<CardView>(R.id.cardRoutine1).setOnClickListener {
            val intent = Intent(this, RoutineDetailActivity::class.java)
            intent.putExtra("routineId", 1)
            startActivity(intent)
        }

        // 루틴 2 카드 클릭 이벤트
        findViewById<CardView>(R.id.cardRoutine2).setOnClickListener {
            val intent = Intent(this, RoutineDetailActivity::class.java)
            intent.putExtra("routineId", 2)
            startActivity(intent)
        }

        // OnBackPressedDispatcher 설정
        setupBackPressedHandling()
    }

    // OnBackPressedDispatcher를 사용하여 뒤로가기 처리
    private fun setupBackPressedHandling() {
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START) // 네비게이션 닫기
                } else {
                    finish() // 액티비티 종료
                }
            }
        })
    }
}
