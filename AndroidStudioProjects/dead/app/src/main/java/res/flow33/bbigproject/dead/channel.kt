/*
package res.flow33.bbigproject.dead

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.graphics.Color
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity.NOTIFICATION_SERVICE
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.view.WindowInsetsCompat

class channel {
    setContentView(R.layout.activity_main)
    ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v  , insets ->
        val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
        v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
        insets
    }
    //알림
    val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
    val builder: NotificationCompat.Builder

    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) { // 채널 생성
        val channelID = "one_channel"
        val channelName = "My Channel One"
        val channel =
            NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_DEFAULT)
        // 채널 정보
        channel.description = "My Channel One Description" // 채널 설명
        channel.setShowBadge(true) // 배지 표시
        val uri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val audioAttributes = AudioAttributes.Builder()
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .setUsage(AudioAttributes.USAGE_ALARM)
            .build()
        channel.setSound(uri, audioAttributes) // 알림음 재생
        channel.enableLights(true) // 불빛 표시
        channel.lightColor = Color.RED // 불빛?색상
        channel.enableVibration(true) // 진동 설정
        channel.vibrationPattern = longArrayOf(100, 200, 100, 200) // 진동 패턴

        manager.createNotificationChannel(channel) // 채널 등록
        // 채널 ID로 NotificationCompat.Builder 생성
        builder = NotificationCompat.Builder(this, channelID)
    }  else{
        builder = NotificationCompat.Builder(this)
    }
    // 알림 정보 생성
    builder.setSmallIcon(R.drawable.logo) // 아이콘
    builder.setWhen(System.currentTimeMillis()+300000) // 알림 시각
    builder.setContentTitle("일어나세요 제발") // 타이틀
    builder.setContentText("루틴 시작 3분전 알람입니다..") // 내용
    builder.setPriority(NotificationCompat.PRIORITY_DEFAULT) //
    builder.setShowWhen(true)
    builder.setUsesChronometer(true)

    val notification: Notification = builder.build()
    manager.notify(11, notification)
}*/
