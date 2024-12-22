package res.flow33.bbigproject.dead

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import android.widget.CheckBox
import android.widget.PopupMenu
import android.widget.Switch
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import java.util.Calendar
import kotlin.random.Random

// BroadcastReceiver는 알람 수신 시 동작을 처리하는 클래스
class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        // 알림을 보내는 NotificationManager 객체
        lateinit var notificationManager: NotificationManager

        // 알람이 울릴 때 호출되는 메소드
        fun onReceive(context: Context, intent: Intent) {
            notificationManager = context.getSystemService(
                Context.NOTIFICATION_SERVICE
            ) as NotificationManager

            // 인텐트에서 알람 정보를 가져옵니다
            val id = intent.getIntExtra("ID", 0)
            val text = intent.getStringExtra("text")
            val hour = intent.getIntExtra("hour", -1)
            val minute = intent.getIntExtra("minute", -1)
            val temp = intent.getSerializableExtra("calendar")

            // 알림을 만들어서 보냅니다
            notificationManager.sendNotification(id, context)

            // 알람 매니저 객체 생성
            val alarmManager = context.getSystemService(AppCompatActivity.ALARM_SERVICE) as AlarmManager

            // 알람이 울릴 시간 설정 (다음날로 알람 시간 설정)
            val calendar: Calendar = Calendar.getInstance().apply {
                timeInMillis = System.currentTimeMillis()
                set(Calendar.HOUR_OF_DAY, hour)
                set(Calendar.MINUTE, minute)
            }
            calendar.add(Calendar.DAY_OF_MONTH, 1)

            // 알람이 울리면 실행될 PendingIntent 설정
            val pendingIntent = PendingIntent.getBroadcast(
                context, id, intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )

            // 알람매니저에 다음 알람을 예약합니다.
            alarmManager.setExactAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
                calendar.timeInMillis,
                pendingIntent
            )
        }
    }

    // NotificationManager를 사용해 알림을 보내는 함수
    fun NotificationManager.sendNotification(notification_id: Int, applicationContext: Context) {
        val stringArray: Array<String> = arrayOf(
            "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o"
        )
        val random = Random
        val num = random.nextInt(15)  // 랜덤한 메시지 선택

        // 알림 빌더 설정
        val builder = NotificationCompat.Builder(
            applicationContext,
            "Notification_Channel_id"
        )
            .setSmallIcon(R.drawable.logo)  // 알림 아이콘
            .setContentTitle(notification_id.toString() + " Make a Smile")  // 알림 제목
            .setContentText(stringArray[num])  // 랜덤 알림 내용
            .setPriority(NotificationCompat.PRIORITY_HIGH)  // 높은 우선순위
            .setAutoCancel(true)  // 클릭하면 자동으로 알림이 사라지도록 설정

        // 알림을 보냄
        notify(notification_id, builder.build())
    }
}

class MainActivity2 : AppCompatActivity() {

    private lateinit var alarmManager: AlarmManager

    @SuppressLint("MissingInflatedId", "UseSwitchCompatOrMaterialCode")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        // 시간 선택을 위한 TimePicker 초기화
        val timePicker = findViewById<TimePicker>(R.id.timepicker)
        timePicker.setIs24HourView(false)  // 12시간 형식으로 시간 표시

        // 알람 매니저 초기화
        alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

        // 버튼과 스위치 초기화
        val completeButton: Button = findViewById<Button>(R.id.CompleteButton)
        val viberateButton: Switch = findViewById<Switch>(R.id.viberateButton)
        val sendButton: Switch = findViewById<Switch>(R.id.sendbutton)

        // 요일 체크박스 초기화
        val cbSun = findViewById<CheckBox>(R.id.cb_sun)
        val cbMon = findViewById<CheckBox>(R.id.cb_mon)
        val cbTue = findViewById<CheckBox>(R.id.cb_tue)
        val cbWed = findViewById<CheckBox>(R.id.cb_wed)
        val cbThr = findViewById<CheckBox>(R.id.cb_thr)
        val cbFri = findViewById<CheckBox>(R.id.cb_fri)
        val cbSat = findViewById<CheckBox>(R.id.cb_sat)

        // 요일과 체크 여부를 매핑한 리스트
        val week = listOf(
            cbSun.isChecked,
            cbMon.isChecked,
            cbTue.isChecked,
            cbWed.isChecked,
            cbThr.isChecked,
            cbFri.isChecked,
            cbSat.isChecked
        )

        // 선택된 요일을 필터링하여 인덱스 반환
        val selectedDays = week.mapIndexedNotNull { index, isChecked ->
            if (isChecked) index else null
        }

        // 선택된 요일이 없으면 토스트 메시지 출력
        if (selectedDays.isEmpty()) {
            Toast.makeText(this, "요일을 선택해 주세요.", Toast.LENGTH_SHORT).show()
            return
        }

        // TimePicker에서 선택한 시간 가져오기
        val hour = timePicker.hour
        val minute = timePicker.minute

        // 선택된 요일별로 알람 설정
        for (day in selectedDays) {
            setAlarmForDay(day, hour, minute)
        }

        // 버튼 클릭 리스너 설정 (추후 기능 추가 예정)
        sendButton.setOnClickListener {
            // 기능 추가 예정
        }

        viberateButton.setOnClickListener {
            // 진동 설정 기능 추가 예정
        }

        completeButton.setOnClickListener{

            // TimePicker에서 시간과 분 가져오기
            val hour = timePicker.hour
            val minute = timePicker.minute
            Log.d("CompleteButton", "TimePicker 시간: $hour, 분: $minute")

            // 요일 데이터 필터링
            val week = listOf(cbSun.isChecked, cbMon.isChecked, cbTue.isChecked, cbWed.isChecked, cbThr.isChecked, cbFri.isChecked, cbSat.isChecked)
            Log.d("CompleteButton", "요일 선택 상태: $week")  // 선택 상태 로그 출력
            val selectedDays = week.mapIndexedNotNull { index, isChecked -> if (isChecked) index else null }

            if (selectedDays.isEmpty()) {
                Toast.makeText(this, "요일을 선택해 주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            Log.d("CompleteButton", "선택된 요일: $selectedDays")

            // SharedPreferences에 저장
            saveAlarmSettings(selectedDays, hour, minute)

            // Intent로 데이터 전달
            val intent = Intent(this, MainActivity::class.java).apply {
                putExtra("hour", hour)
                putExtra("minute", minute)
                putIntegerArrayListExtra("selectedDays", ArrayList(selectedDays))
            }
            startActivity(intent)
            finish()
        }
    }

    // 알람 설정 함수 (요일별로 알람 설정)
    private fun setAlarmForDay(dayOfWeek: Int, hour: Int, minute: Int) {
        // 알람 시간을 계산합니다.
        val calendar = Calendar.getInstance().apply {
            set(Calendar.DAY_OF_WEEK, dayOfWeek + 1)  // Calendar.DAY_OF_WEEK는 일요일부터 1로 시작
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, minute)
            set(Calendar.SECOND, 0)

            // 과거 시간이라면 다음 주로 설정
            if (timeInMillis < System.currentTimeMillis()) {
                add(Calendar.WEEK_OF_YEAR, 1)
            }
        }

        // 알람을 처리할 PendingIntent 설정
        val intent = Intent(this, AlarmReceiver::class.java)  // 알람을 받을 BroadcastReceiver 설정
        val pendingIntent = PendingIntent.getBroadcast(
            this,
            dayOfWeek,  // 요일별로 고유 PendingIntent 사용
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        // 알람 설정
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            pendingIntent
        )
    }

    // 알람 설정값을 SharedPreferences에 저장
    private fun saveAlarmSettings(selectedDays: List<Int>, hour: Int, minute: Int) {
        val sharedPreferences = getSharedPreferences("AlarmPreferences", MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        // 선택된 요일을 Set으로 저장
        editor.putStringSet("selectedDays", selectedDays.map { it.toString() }.toSet())
        editor.putInt("hour", hour)  // 선택한 시간 저장
        editor.putInt("minute", minute)  // 선택한 분 저장
        editor.apply()  // 저장 완료

        Log.d("saveAlarmSettings", "저장된 요일: ${selectedDays.map { it.toString() }}, 시간: $hour, 분: $minute")
    }
    // 알림 음향 선택을 위한 팝업 메뉴
    private fun showPopupMenu(anchor: android.view.View) {
        val popupMenu = PopupMenu(this, anchor)

        // 팝업 메뉴에 항목 추가
        popupMenu.menuInflater.inflate(R.menu.popup_menu, popupMenu.menu)

        // 메뉴 항목 클릭 리스너 설정
        popupMenu.setOnMenuItemClickListener { item: MenuItem ->
            when (item.itemId) {
                R.id.option1 -> {
                    showToast("옵션 1 선택됨")
                    true
                }
                R.id.option2 -> {
                    showToast("옵션 2 선택됨")
                    true
                }
                R.id.option3 -> {
                    showToast("옵션 3 선택됨")
                    true
                }
                else -> false
            }
        }
        popupMenu.show()  // 팝업 메뉴 표시
    }

    // 토스트 메시지 표시 함수
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}

