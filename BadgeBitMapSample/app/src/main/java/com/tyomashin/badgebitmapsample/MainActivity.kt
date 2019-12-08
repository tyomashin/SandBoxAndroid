package com.tyomashin.badgebitmapsample

import android.graphics.*
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val icon = BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher)
        val badgeIcon = makeBadgeIconBitmap(icon,"2")
        imageView.setImageBitmap(badgeIcon)
    }

    /**
     * マップ用のカテゴリアイコンにバッジを合成して返す
     * @param targetIcon カテゴリアイコン
     * @param badgeColorId バッジの色
     * @param badgeStr バッジに表示する数字文字列
     * @return アイコンとバッジを合成したBitmap
     */
    fun makeBadgeIconBitmap(icon : Bitmap, badgeStr : String) : Bitmap {
        // バッジサイズの比率
        val badgeAspect = 0.5f
        // アイコンサイズ
        val width = icon.width
        val height = icon.height
        // バッジサイズ
        val badgeSize = (width * badgeAspect).toInt()
        val badgeRadius = badgeSize/2.0f
        // キャンバスを作成してベースとなるアイコンを描画する
        val newBitmap = Bitmap.createBitmap(width + badgeRadius.toInt(),height + badgeRadius.toInt(), Bitmap.Config.ARGB_8888)
        val newCanvase = Canvas(newBitmap)
        newCanvase.drawBitmap(icon,0f, badgeRadius,null)
        // バッジを作成してアイコンに合成する
        val badge = createBadgeBitmap(badgeSize,  "2")
        newCanvase.drawBitmap(badge, width.toFloat() - badgeRadius, 0f, null)

        return newBitmap
    }

    /**
     * マップアイコンに表示する円形バッジを作成する
     * @param badgeSize バッジのサイズ
     * @param badgeColorId バッジの色ID
     * @param badgeStr バッジに表示するテキスト
     * @return バッジBitmap
     */
    fun createBadgeBitmap(badgeSize : Int, badgeStr : String) : Bitmap{
        // バッジ半径
        val badgeRadius = badgeSize/2.0f
        // バッジを描画するCanvasを作成
        val bitmap = Bitmap.createBitmap(badgeSize,badgeSize, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        // バッジの円を描画
        val circlePaint = Paint()
        circlePaint.isAntiAlias = true
        circlePaint.color = Color.RED
        canvas.drawCircle(badgeRadius, badgeRadius, badgeRadius, circlePaint)
        // バッジテキストを描画
        val textPaint = Paint()
        textPaint.isAntiAlias = true
        textPaint.color = Color.WHITE
        textPaint.textAlign = Paint.Align.CENTER
        textPaint.textSize = badgeRadius
        val xPos = canvas.width/2f
        val yPos = (canvas.height/2f)-((textPaint.descent()+textPaint.ascent()) / 2)
        canvas.drawText(badgeStr, xPos, yPos, textPaint)

        return bitmap
    }
}
