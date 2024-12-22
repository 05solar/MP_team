package com.example.mp_teamproject

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.cardview.widget.CardView
import android.widget.Button
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat

class HomeActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_home)

        // 툴바 설정
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // ActionBar 타이틀 제거
        supportActionBar?.setDisplayShowTitleEnabled(false)

        // DrawerLayout 설정
        drawerLayout = findViewById(R.id.drawer_layout)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // 드롭다운 메뉴 설정
        val menuIcon = findViewById<ImageView>(R.id.ivMenu)
        menuIcon.setOnClickListener {
            showPopupMenu(menuIcon)
        }

        // 새 루틴 추가 버튼 클릭 이벤트
        findViewById<Button>(R.id.btnAddRoutine).setOnClickListener {
            startActivity(Intent(this, AddRoutineActivity::class.java))
        }

        // 루틴 1 카드 클릭 이벤트
        findViewById<CardView>(R.id.cardRoutine1).setOnClickListener {
            // Intent를 사용하여 HomeActivity로 이동
            val intent = Intent(this, DetailActivity::class.java)
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

    private fun showPopupMenu(anchor: ImageView) {
        val popupMenu = PopupMenu(this, anchor)
        popupMenu.menuInflater.inflate(R.menu.menu_dropdown, popupMenu.menu)

        popupMenu.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.menu_home -> {
                    Toast.makeText(this, "Settings selected", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, SettingsActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.menu_about -> {
                    Toast.makeText(this, "About selected", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, AboutActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }

        popupMenu.show()
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
