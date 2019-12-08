package com.tyomashin.blureffectsample

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.renderscript.*
import android.view.View

/**
 * ブラーのかかったBitmapを作成する
 * 参考：https://stackoverflow.com/questions/6795483/create-blurry-transparent-background-effect
 *
 * ※iOS のように、背景にブラー効果を与えるような実装は難しい。
 * 実装するためには、View のスナップショットBitmapをとってBlurをかけ、Viewに適用する必要がある。
 * これを実装したサードパーティ製ライブラリを使うことで実現する方法があるが、
 * パフォーマンスに影響が出てくる模様。
 *
 * 　参考：https://stackoverflow.com/questions/49842139/how-to-set-the-blur-level-of-a-view-background
 */
class BlurBuilder {

    companion object{

        fun makeBlurBackgroundBitmap(v : View) : Bitmap{
            return blur(v.context, getScreenShop(v))
        }

        private fun getScreenShop(v : View) : Bitmap{
            val b = Bitmap.createBitmap(v.width, v.height, Bitmap.Config.ARGB_8888)
            val c = Canvas(b)
            v.draw(c)
            return b
        }

        private fun blur(context : Context, image : Bitmap) : Bitmap{
            val inputBitmap = Bitmap.createScaledBitmap(image, image.width, image.height, false)
            val outputBitmap = Bitmap.createBitmap(inputBitmap)

            val rs = RenderScript.create(context)
            val theIntrinsic = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs))

            val tmpIn = Allocation.createFromBitmap(rs, inputBitmap)
            val tmpOut = Allocation.createFromBitmap(rs, inputBitmap)

            theIntrinsic.setRadius(25f)
            theIntrinsic.setInput(tmpIn)
            theIntrinsic.forEach(tmpOut)
            tmpOut.copyTo(outputBitmap)

            return outputBitmap
        }


    }
}