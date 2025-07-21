package com.mechawisdom.lunapopupinfo

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Rect
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupWindow
import android.widget.TextView
import androidx.core.view.doOnPreDraw
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.mechawisdom.lunainfopopup.R

class LunaInfoPopup(private val context: Context, lifecycle: Lifecycle? = null) {
    private var popupWindow: PopupWindow? = null
    private var popupWidthRatio: Float = PopupWidth.MEDIUM.ratio
    private val colors = LunaInfoPopupColors(context)
    private var backgroundDrawable: Drawable? = null
    private var padding: Int = 0
    private var cornerRadiusDp: Float = 0f
    private var textColor: Int = colors.textColor
    private var backgroundColor: Int = colors.backgroundColor
    private var textSizeSp: Float = 14f
    private var typeface: Typeface? = null

    init {
        lifecycle?.addObserver(object : DefaultLifecycleObserver {
            override fun onDestroy(owner: LifecycleOwner) {
                dismiss()
            }
        })
    }

    fun setWidth(width: PopupWidth): LunaInfoPopup {
        popupWidthRatio = width.ratio
        return this
    }

    fun setCornerRadius(radius: Float): LunaInfoPopup {
        cornerRadiusDp = radius
        updateBackground()
        return this
    }

    fun setBackgroundColor(color: Int): LunaInfoPopup {
        backgroundColor = color
        updateBackground()
        return this
    }

    fun setFontFamily(font: Typeface): LunaInfoPopup {
        typeface = font
        return this
    }

    fun setPaddingDp(dp: Int): LunaInfoPopup {
        padding = (dp * context.resources.displayMetrics.density).toInt()
        return this
    }

    fun setTextColor(color: Int): LunaInfoPopup {
        textColor = color
        return this
    }

    fun setTextSizeSp(sp: Float): LunaInfoPopup {
        textSizeSp = sp
        return this
    }

    private fun updateBackground() {
        backgroundDrawable = GradientDrawable().apply {
            shape = GradientDrawable.RECTANGLE
            cornerRadius = cornerRadiusDp * context.resources.displayMetrics.density
            setColor(backgroundColor)
        }
    }


    @SuppressLint("InflateParams")
    fun showPopup(infoText: String, anchorView: View) {
        val screenWidth = context.resources.displayMetrics.widthPixels
        val screenHeight = context.resources.displayMetrics.heightPixels
        val popupWidth = (screenWidth * popupWidthRatio).toInt()


        val contentView =
            LayoutInflater.from(context).inflate(R.layout.popup_info_layout, null, false)


        val textViewContent = contentView.findViewById<TextView>(R.id.textViewContent)
        val imageViewTriangleUp = contentView.findViewById<ImageView>(R.id.imageViewTriangleUp)
        val imageViewTriangleDown = contentView.findViewById<ImageView>(R.id.imageViewTriangleDown)

        imageViewTriangleUp.post {
            val halfHeight = imageViewTriangleUp.height / 2f
            imageViewTriangleUp.translationY = halfHeight
            imageViewTriangleUp.setColorFilter(backgroundColor)
        }

        imageViewTriangleDown.post {
            val halfHeight = imageViewTriangleDown.height / 2f
            imageViewTriangleDown.translationY = -halfHeight
            imageViewTriangleDown.setColorFilter(backgroundColor)
        }

        textViewContent.apply {
            text = infoText
            typeface?.let { this.typeface = it }
            setTextSize(TypedValue.COMPLEX_UNIT_SP, textSizeSp)
            setTextColor(textColor)
            setPadding(padding, padding, padding, padding)
            background = backgroundDrawable
        }


        contentView.measure(
            View.MeasureSpec.makeMeasureSpec(screenWidth, View.MeasureSpec.AT_MOST),
            View.MeasureSpec.makeMeasureSpec(screenHeight, View.MeasureSpec.AT_MOST)
        )

        popupWindow = PopupWindow(
            contentView,
            popupWidth,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            true
        ).apply {
            elevation = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                3f,
                context.resources.displayMetrics
            )

            animationStyle = R.style.DialogAnimation
        }

        contentView.doOnPreDraw {
            val anchorLocation = IntArray(2)
            anchorView.getLocationOnScreen(anchorLocation)
            val anchorCenterX = anchorLocation[0] + anchorView.width / 2

            val popupLocation = IntArray(2)
            contentView.getLocationOnScreen(popupLocation)
            val popupStartX = popupLocation[0]

            val relativeCenterX = anchorCenterX - popupStartX
            val triangleHalf = imageViewTriangleUp.width / 2
            val triangleDownHalf = imageViewTriangleDown.width / 2

            imageViewTriangleUp.translationX = (relativeCenterX - triangleHalf).toFloat()
            imageViewTriangleDown.translationX = (relativeCenterX - triangleDownHalf).toFloat()
        }


        popupWindow?.let {
            val rect = Rect()
            anchorView.getGlobalVisibleRect(rect)
            val popUpHeight = contentView.measuredHeight

            val spaceBelow = screenHeight - rect.bottom
            val spaceAbove = rect.top

            val popupX = rect.centerX() - (popupWidth / 2)
            val popupY: Int
            if (spaceBelow >= popUpHeight) {
                popupY = rect.bottom
                imageViewTriangleUp.visibility = View.VISIBLE
                imageViewTriangleDown.visibility = View.GONE
            } else if (spaceAbove >= popUpHeight) {
                popupY = rect.top - popUpHeight
                imageViewTriangleUp.visibility = View.GONE
                imageViewTriangleDown.visibility = View.VISIBLE
            } else {
                popupY = screenHeight / 2 - popUpHeight / 2
                imageViewTriangleUp.visibility = View.GONE
                imageViewTriangleDown.visibility = View.GONE
            }
            it.showAtLocation(anchorView, Gravity.NO_GRAVITY, popupX, popupY)
        }
    }

    fun dismiss() {
        if (popupWindow?.isShowing == true) popupWindow?.dismiss()
    }
}
