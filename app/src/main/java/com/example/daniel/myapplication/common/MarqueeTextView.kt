package com.example.test

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.Log
import android.view.Gravity
import android.view.animation.LinearInterpolator
import android.widget.Scroller
import androidx.appcompat.widget.AppCompatTextView

class MarqueeTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) :
    AppCompatTextView(context, attrs, defStyle) {
    private val TAG: String = "MarqueeTextView"
    private var mRunning: Boolean = false
    private val mScroller: Scroller = Scroller(context, LinearInterpolator())
    private var mTxtWidth: Int = 0
    private var mAttached: Boolean = false
    private var mTxt: CharSequence? = null
    private var mPreLoad: Boolean = false
    private var mLoading: Boolean = false
    private var mGravity: Int = 0
    private val mHandler = Handler(Looper.getMainLooper())
    private var mOffsetXInView = 0;

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        Log.i(TAG, "onAttach width: $width")
        mAttached = true
        startScroll()
    }

    override fun onDetachedFromWindow() {
        mAttached = false
        stopScroll()
        super.onDetachedFromWindow()
    }

    fun startScroll() {
        if (mRunning) {
            return
        }
        mTxt = text
        if (TextUtils.isEmpty(mTxt)) {
            return
        }
        if (width <= 0) {
            if (!mPreLoad) {
                Log.i(TAG, "preload:$mPreLoad")
                mPreLoad = true
                post{ doStartScroll() }
            }
        } else {
            if (!mLoading) {// only start one time
                mLoading = true
                doStartScroll()
                mLoading = false
            }
        }
    }

    private fun doStartScroll() {
        mTxtWidth = (super.getPaint().measureText(mTxt.toString())).toInt()
        val availableWidth: Int = width - paddingLeft - paddingRight
        Log.i(TAG, "txtWidth:$mTxtWidth viewWidth:$availableWidth")
        if (mTxtWidth <= 0 || mTxtWidth <= availableWidth) {
            return
        }
        mRunning = true
        gravity = Gravity.START or  mGravity
        startAnimation()
    }

    private fun startAnimation() {
        if (!mRunning) {
            return
        }
        mHandler.postDelayed(object :Runnable{

            override fun run() {
                val currX: Int = mScroller.currX
                val startX: Int = if ((currX > mTxtWidth)) -width else currX
                if (!mRunning) {
                    return
                }
                mScroller.startScroll(startX, 0, OFFSET, 0, DURATION.toInt())
                startAnimation()
            }
        }, DURATION)
    }

    fun stopScroll() {
        mRunning = false
        mHandler.removeCallbacksAndMessages(null)
        mScroller.abortAnimation()
    }

    fun resetScroll(){
        if(mScroller.currX != 0){
            mScroller.startScroll(mScroller.currX,0, -mScroller.currX,0,0)
        }
        if(scrollX != mOffsetXInView){
            scrollTo(mOffsetXInView,0)
        }
    }

    override fun computeScroll() {
        super.computeScroll()
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.currX, mScroller.currY)
            invalidate()
        }
    }

    override fun onPreDraw(): Boolean {
        val running = mRunning
        if(running){
            stopScroll()
        }
        val res = super.onPreDraw()
        mOffsetXInView = scrollX
        if(running){
            resetScroll()
            startScroll()
        }
        return res;
    }

    companion object {
        private const val DURATION: Long = 1000L
        private const val OFFSET: Int = 80
    }

    init {
        mGravity = gravity
        setScroller(mScroller)
        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable) {
                Log.i("test","$s")
                if (mRunning) {
                    stopScroll()
                }
                if(gravity != mGravity){
                    gravity = mGravity
                }
                resetScroll()
                mTxt = s.toString()
                if (mAttached) {
                    startScroll()
                }
            }
        })
    }
}