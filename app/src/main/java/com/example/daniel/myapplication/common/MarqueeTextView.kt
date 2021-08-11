package com.example.test

import android.content.Context
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

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        Log.i(TAG, "onAttach width: $width")
        mAttached = true
        startScroll()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        mAttached = false
        stopScroll()
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
            if (!mLoading) {
                mLoading = true
                doStartScroll()
                mLoading = false
            }
        }
    }

    private fun doStartScroll() {
        mTxtWidth = (super.getPaint().measureText(mTxt.toString())).toInt()
        val viewWidth: Int = width
        Log.i(TAG, "txtWidth:$mTxtWidth viewWidth:$viewWidth")
        if (mTxtWidth <= 0 || mTxtWidth <= viewWidth) {
            return
        }
        mRunning = true
        gravity = Gravity.START or  mGravity
        setScroller(mScroller)
        startAnimation()
    }

    private fun startAnimation() {
        if (!mRunning) {
            return
        }
        postDelayed(object :Runnable{

            override fun run() {
            val currX: Int = mScroller.currX
            val startX: Int = if ((currX > mTxtWidth)) -width else currX
            if (!mRunning) {
                return
            }
            mScroller.startScroll(
                startX, 0, OFFSET, 0,
                DURATION.toInt()
            )
            invalidate()
            startAnimation()
            }
        }, DURATION)
    }

    fun stopScroll() {
        mRunning = false
        mScroller.abortAnimation()
        scrollTo(0, 0)
        val curX: Int = mScroller.currX
        mScroller.startScroll(curX, 0, -curX, 0, 1)
        invalidate()
        mScroller.abortAnimation()
        mPreLoad = false
    }

    override fun computeScroll() {
        super.computeScroll()
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.currX, mScroller.currY)
            invalidate()
        }
    }

    companion object {
        private const val DURATION: Long = 500L
        private const val OFFSET: Int = 40
    }

    init {
        mGravity = gravity
        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable) {
                Log.i("test","$s")
                if (mRunning) {
                    stopScroll()
                }
                gravity = mGravity
                val currX0: Int = scrollX
                val currX: Int = mScroller.currX
                val startX: Int = mScroller.startX
                val finalX: Int = mScroller.finalX
                Log.i(TAG, "currX0: $currX0 currX:$currX startX:$startX finalX: $finalX")
                mTxt = s.toString()
                if (mAttached) {
                    startScroll()
                }
            }
        })
    }
}