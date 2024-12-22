package com.example.bbbbbbbbbbbbbbbbigproject

// 필요한 Android 라이브러리 임포트
import android.app.NotificationChannel // NotificationChannel 클래스를 사용하기 위해 임포트
import android.app.NotificationManager // NotificationManager 클래스를 사용하기 위해 임포트
import android.content.Context // Context를 사용하기 위해 임포트
import android.os.Bundle // onCreate 등의 메서드에서 사용하는 Bundle 임포트
import android.widget.Button // 버튼 UI 요소를 사용하기 위해 임포트
import androidx.activity.enableEdgeToEdge // 엣지 투 엣지 UI 설정 관련
import androidx.appcompat.app.AppCompatActivity // AppCompatActivity 기반으로 액티비티를 정의하기 위해 임포트
import androidx.core.view.ViewCompat // 뷰 호환성 관련
import androidx.core.view.WindowInsetsCompat // 윈도우 인셋 관련

// 메인 액티비티 정의: 앱 실행 시 첫 번째로 실행되는 화면
class MainActivity : AppCompatActivity() {

    // Notification을 처리하는 클래스의 인스턴스를 저장하기 위한 변수 선언
    private lateinit var myNotification: MyNotification

    // 버튼 참조 변수 정의 (lazy 사용으로 버튼 접근 시 초기화됨)
    private val btn: Button by lazy { findViewById(R.id.btn) }

    // onCreate: 액티비티가 처음 생성될 때 호출되는 메서드
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState) // 부모 클래스의 onCreate 호출
        setContentView(R.layout.activity_main) // 액티비티의 레이아웃 설정

        // MyNotification 클래스 초기화
        myNotification = MyNotification(this)
    }

    // onResume: 액티비티가 화면에 보이기 직전에 호출되는 메서드
    override fun onResume() {
        super.onResume() // 부모 클래스의 onResume 호출

        // 버튼 클릭 이벤트 리스너 설정
        btn.setOnClickListener {
            // 버튼이 클릭되면 MyNotification 클래스의 deliverNotification 호출
            myNotification.deliverNotification()
        }
    }
}

// Notification을 처리하는 별도의 클래스 정의
class MyNotification(private val context: Context) {

    // NotificationManager 초기화: 알림 관련 작업을 관리하는 시스템 서비스
    private var notificationManager: NotificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    // 초기화 블록: 클래스 인스턴스 생성 시 호출됨
    init {
        // 알림 채널 생성
        createNotificationChannel()
    }

    FirebaseMessaging.getInstance().getToken()

    // 알림 채널 생성 메서드
    private fun createNotificationChannel() {
        // NotificationChannel 생성
        val notificationChannel =
            NotificationChannel(
                CHANNEL_ID, // 채널 ID
                CHANNEL_NAME, // 채널 이름
                NotificationManager.IMPORTANCE_HIGH // 알림 중요도 (높음)
            ).apply {
                enableVibration(true) // 진동 활성화
                description = "description" // 채널 설명
            }

        // NotificationManager를 사용해 채널 등록
        notificationManager.createNotificationChannel(notificationChannel)
    }

    // 알림을 표시하는 메서드 (아직 구현되지 않음)
    fun deliverNotification() {
        // 알림 표시 로직 추가 필요
    }

    // 동반 객체: 클래스의 상수 선언
    companion object {
        const val CHANNEL_ID = "channel_id" // 알림 채널의 고유 ID
        const val CHANNEL_NAME = "channel_name" // 알림 채널의 이름
        const val NOTIFICATION_ID = 0 // 알림 ID (알림을 구분하는 데 사용)
    }
}
