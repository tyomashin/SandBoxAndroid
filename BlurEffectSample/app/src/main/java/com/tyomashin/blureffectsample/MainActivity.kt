package com.tyomashin.blureffectsample

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
    }

    override fun onResume() {
        super.onResume()
        val sample = findViewById(R.id.bottomLayout) as? View
        sample?.alpha = 0.85f
        /*
        sample?.let{content ->
            if (content.width > 0) {
                val blurBitmap = BlurBuilder.makeBlurBackgroundBitmap(content)
                content.background = BitmapDrawable(resources, blurBitmap)
            }else{
                content.viewTreeObserver.addOnGlobalLayoutListener {
                    val blurBitmap = BlurBuilder.makeBlurBackgroundBitmap(content)
                    content.background = BitmapDrawable(resources, blurBitmap)
                }
            }
        }
        */
    }

}
