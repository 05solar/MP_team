package com.example.mp_teamproject

import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar

class AddRoutineActivity : AppCompatActivity() {

    private lateinit var tvStartTime: TextView
    private lateinit var tvEndTime: TextView
    private lateinit var btnConfirm: Button

    // SharedPreferences 키 설정
    private val sharedPrefs by lazy { getSharedPreferences("RoutinePrefs", MODE_PRIVATE) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_addroutine)

        // 시간 선택 TextView 참조
        tvStartTime = findViewById(R.id.tvStartTime)
        tvEndTime = findViewById(R.id.tvEndTime)
        btnConfirm = findViewById(R.id.btnConfirmRoutine)

        // 시작 시간 선택
        tvStartTime.setOnClickListener {
            showTimePicker { hour, minute ->
                val time = String.format("%02d:%02d", hour, minute)
                tvStartTime.text = time
            }
        }

        // 종료 시간 선택
        tvEndTime.setOnClickListener {
            showTimePicker { hour, minute ->
                val time = String.format("%02d:%02d", hour, minute)
                tvEndTime.text = time
            }
        }

        // Confirm 버튼 클릭 시 시간 저장
        btnConfirm.setOnClickListener {
            saveTimeToLocalStorage()
        }

        // 저장된 시간 불러오기
        loadSavedTime()
    }

    // TimePickerDialog 표시
    private fun showTimePicker(onTimeSelected: (hour: Int, minute: Int) -> Unit) {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val timePicker = TimePickerDialog(this, { _, selectedHour, selectedMinute ->
            onTimeSelected(selectedHour, selectedMinute)
        }, hour, minute, false)

        timePicker.show()
    }

    // SharedPreferences에 시간 저장
    private fun saveTimeToLocalStorage() {
        val startTime = tvStartTime.text.toString()
        val endTime = tvEndTime.text.toString()

        if (startTime.isNotBlank() && endTime.isNotBlank()) {
            val editor = sharedPrefs.edit()
            editor.putString("startTime", startTime)
            editor.putString("endTime", endTime)
            editor.apply()

            Toast.makeText(this, "Time saved successfully!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Please select both times!", Toast.LENGTH_SHORT).show()
        }
    }

    // SharedPreferences에서 시간 불러오기
    private fun loadSavedTime() {
        val startTime = sharedPrefs.getString("startTime", "Start Time")
        val endTime = sharedPrefs.getString("endTime", "End Time")

        tvStartTime.text = startTime
        tvEndTime.text = endTime
    }
}
