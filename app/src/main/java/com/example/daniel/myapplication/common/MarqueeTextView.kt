package com.example.daniel.myapplication.common

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.animation.LinearInterpolator
import android.widget.Scroller
import androidx.appcompat.widget.AppCompatTextView
import kotlin.math.roundToInt

class MarqueeTextView(val ctx:Context,val attr: AttributeSet) :AppCompatTextView(ctx,attr){

    override fun onWindowFocusChanged(hasWindowFocus: Boolean) {
        super.onWindowFocusChanged(hasWindowFocus)
        if (hasWindowFocus) start() else stop()
    }

    var running = false
    val du = 500L
    var scr = Scroller(context,LinearInterpolator())
    var txtWidth = 0

    fun start() {
        txtWidth = super.getPaint().measureText(text.toString()).toInt() + 100
        Log.i("test","txtWidth ${txtWidth}")
        if(txtWidth <= 0){
            running = false
            return
        }
        running = true
        setScroller(scr)
        startAnimation()
    }

    val offset = 40

    fun startAnimation(){
        if(running){

            postDelayed({
                val startX:Int = if(scr.currX > txtWidth) -width else scr.currX
                scr.startScroll(startX,0,offset,0,du.toInt())
                invalidate()
                startAnimation()
            },du)
        }
    }

    fun stop(){
        running = false
    }

    override fun computeScroll() {
        super.computeScroll()
        if (scr.computeScrollOffset()) {
            scrollTo(scr.getCurrX(),scr.getCurrY())
            invalidate()
        }
    }


}