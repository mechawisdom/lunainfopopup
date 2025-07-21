package com.mechawisdom.sample

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.graphics.toColorInt
import com.mechawisdom.lunapopupinfo.LunaInfoPopup
import com.mechawisdom.lunapopupinfo.PopupWidth


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val textView=findViewById<TextView>(R.id.textView)

        val popup = LunaInfoPopup(this, lifecycle)
            .setCornerRadius(22f)
            .setBackgroundColor("#AA3333".toColorInt())
            .setTextColor(Color.WHITE)
            .setWidth(PopupWidth.LARGE)
            .setPaddingDp(36)
            .setTextSizeSp(16f)
            .setFontFamily(Typeface.SANS_SERIF)

        textView.setOnClickListener {
            popup.showPopup("text here", textView)
        }
    }
}