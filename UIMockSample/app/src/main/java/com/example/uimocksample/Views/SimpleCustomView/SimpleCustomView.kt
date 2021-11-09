package com.example.uimocksample.Views.SimpleCustomView

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat.getDrawable
import com.example.uimocksample.R
import com.example.uimocksample.databinding.FragmentSplashBinding
import com.example.uimocksample.databinding.CustomViewSimpleBinding

/**
 * シンプルなカスタムView.
 * ViewGroup を継承して、既存のコンポーネントを組み合わせた「複合コントロール」による例。
 */
class SimpleCustomView(
    context: Context,
    attrs: AttributeSet
) : LinearLayout(context, attrs){
    companion object{
        val ColorIdList = listOf(
            R.color.red,
            R.color.green,
            R.color.blue,
            R.color.purple_200,
        )
    }

    private var _binding: CustomViewSimpleBinding? = null
    private val binding get() = _binding!!

    // ボタンタップ回数
    private var tapCount = 0


    init {
        // カスタムViewでViewBindingする方法
        // https://stackoverflow.com/questions/60433269/how-to-use-view-binding-on-custom-views
        _binding = CustomViewSimpleBinding.inflate(LayoutInflater.from(context), this, true)

        binding.textChangeButton.setOnClickListener(getButtonClickListener())
        binding.textChangeButton.setBackgroundColor(ColorIdList[0])
    }

    /**
     * ボタンタップ時のリスナー
     */
    private fun getButtonClickListener() : OnClickListener{
        return OnClickListener {
            tapCount++
            val index = tapCount % ColorIdList.size
            // 色の設定
            // https://stackoverflow.com/questions/13842447/android-set-button-background-programmatically
            // https://stackoverflow.com/questions/31590714/getcolorint-id-deprecated-on-android-6-0-marshmallow-api-23/32149275#32149275
            binding.root.setBackgroundColor(ContextCompat.getColor(context, ColorIdList[index]))
            Log.d("hoge", "$tapCount")

            invalidate()
            requestLayout()
        }
    }
}