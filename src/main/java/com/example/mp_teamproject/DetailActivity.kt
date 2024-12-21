package com.example.mp_teamproject

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_detail) // 연결된 레이아웃 파일

        // 원형 ProgressBar에 애니메이션 적용
        val progressCircle = findViewById<ProgressBar>(R.id.progressCircle)
        animateProgress(progressCircle, 70) // 목표값 설정

        // 하단 Home 아이콘 클릭 이벤트
        val homeImageView = findViewById<ImageView>(R.id.ivHome)
        homeImageView.setOnClickListener {
            // MainActivity로 이동
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish() // HomeActivity 종료 (필요에 따라 추가)
        }

        // 드롭다운 메뉴 설정
        val menuIcon = findViewById<ImageView>(R.id.ivMenu)
        menuIcon.setOnClickListener {
            showPopupMenu(menuIcon)
        }
    }

    private fun animateProgress(progressBar: ProgressBar, targetProgress: Int) {
        val animator = ObjectAnimator.ofInt(progressBar, "progress", 0, targetProgress)
        animator.duration = 1000 // 1초 동안 애니메이션
        animator.start()
    }

    private fun showPopupMenu(anchor: ImageView) {
        val popupMenu = PopupMenu(this, anchor)
        popupMenu.menuInflater.inflate(R.menu.menu_dropdown, popupMenu.menu)

        popupMenu.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.menu_home -> {
                    Toast.makeText(this, "Settings selected", Toast.LENGTH_SHORT).show()
                    // SettingsActivity로 이동 (예시)
                    val intent = Intent(this, SettingsActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.menu_about -> {
                    Toast.makeText(this, "About selected", Toast.LENGTH_SHORT).show()
                    // AboutActivity로 이동 (예시)
                    val intent = Intent(this, AboutActivity::class.java)
                    startActivity(intent)
                    true
                }

                else -> false
            }
        }

        popupMenu.show()
    }
}
