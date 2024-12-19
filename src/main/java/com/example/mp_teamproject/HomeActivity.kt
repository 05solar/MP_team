package com.example.mp_teamproject

import android.animation.ObjectAnimator
import android.os.Bundle
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_home) // 연결된 레이아웃 파일

        // ProgressBar에 애니메이션 효과 추가
        val progressCircle = findViewById<ProgressBar>(R.id.progressCircle) // XML에서 ProgressBar의 ID와 연결
        setProgressWithAnimation(progressCircle, 50) // 70% 진행도 설정
    }

    /**
     * ProgressBar에 애니메이션을 적용하는 메서드
     *
     * @param progressBar 대상 ProgressBar
     * @param progress 목표 진행도 (0~max)
     */
    private fun setProgressWithAnimation(progressBar: ProgressBar, progress: Int) {
        val animator = ObjectAnimator.ofInt(progressBar, "progress", 0, progress) // 애니메이션 설정
        animator.duration = 1000 // 애니메이션 지속 시간 (1초)
        animator.start() // 애니메이션 시작
    }
}
