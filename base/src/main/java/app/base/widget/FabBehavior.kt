package app.base.widget

import android.animation.Animator
import android.content.Context
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.core.view.ViewCompat
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import android.util.AttributeSet
import android.view.View
import android.view.ViewPropertyAnimator
import android.view.animation.Interpolator

/**
 * Created by daniel.luo
 */

class FabBehavior(context: Context, attrs: AttributeSet) : FloatingActionButton.Behavior(context, attrs) {

    private var viewY: Float = 0.toFloat() // Distance from fab to bottom of parent
    private var isAnimate: Boolean = false

    override fun onStartNestedScroll(coordinatorLayout: CoordinatorLayout, child: FloatingActionButton,
                                     directTargetChild: View, target: View, nestedScrollAxes: Int): Boolean {
        if (child.visibility == View.VISIBLE && viewY == 0f) {
            viewY = coordinatorLayout.height - child.y
        }
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL || super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, nestedScrollAxes)
    }

    override fun onNestedPreScroll(coordinatorLayout: CoordinatorLayout, child: FloatingActionButton,
                                   target: View, dx: Int, dy: Int, consumed: IntArray) {
        if (dy >= 0 && !isAnimate && child.visibility == View.VISIBLE) {
            hide(child)
        } else if (dy < 0 && !isAnimate && child.visibility == View.INVISIBLE) {
            show(child)
        }
    }

    private fun hide(view: View) {
        val animator = view.animate().translationY(viewY).setInterpolator(INTERPOLATOR).setDuration(300)
        animator.setListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animator: Animator) {
                isAnimate = true
            }

            override fun onAnimationEnd(animator: Animator) {
                view.visibility = View.INVISIBLE
                isAnimate = false
            }

            override fun onAnimationCancel(animator: Animator) {
                show(view)
            }

            override fun onAnimationRepeat(animator: Animator) {}
        })
        animator.start()
    }

    private fun show(view: View) {
        val animator = view.animate().translationY(0f).setInterpolator(INTERPOLATOR).setDuration(300)
        animator.setListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animator: Animator) {
                view.visibility = View.VISIBLE
                isAnimate = true
            }

            override fun onAnimationEnd(animator: Animator) {
                isAnimate = false
            }

            override fun onAnimationCancel(animator: Animator) {
                hide(view)
            }

            override fun onAnimationRepeat(animator: Animator) {}
        })
        animator.start()
    }

    companion object {

        private val INTERPOLATOR = FastOutSlowInInterpolator()
    }

}
