package res.flow33.bbigproject.dead

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        enableEdgeToEdge()
        val AddButton : ImageButton = findViewById(R.id.AddButton)
        val menuButton : ImageButton = findViewById(R.id.menubutton)
//        val calendarButton : ImageButton = findViewById(R.id.calendarButton)
        AddButton.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
            finish()
        }

/*
        val sharedPreferences = getSharedPreferences("AlarmPreferences", MODE_PRIVATE)
        val selectedDays = sharedPreferences.getStringSet("selectedDays", emptySet()) ?: emptySet()
        val hour = sharedPreferences.getInt("hour", -1)
        val minute = sharedPreferences.getInt("minute", -1)
*/

        val hour = intent.getIntExtra("hour", -1)
        val minute = intent.getIntExtra("minute", -1)
        val selectedDays = intent.getIntegerArrayListExtra("selectedDays") ?: listOf()

        Log.d("MainActivity", "받은 데이터: 시간=$hour, 분=$minute, 선택된 요일=$selectedDays")

        // UI 요소에 값 설정
        val editTextView = findViewById<TextView>(R.id.editTextTime)

/*
        daysTextView.text = "Selected Days: ${selectedDays.joinToString(", ")}"
*/
        editTextView.text = "Time: $hour:$minute"

       menuButton.setOnClickListener{
        }
    }
}