package be.vergauwen.simon.konductor.core.changehandlers

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.changehandler.AnimatorChangeHandler

/**
 * An [AnimatorChangeHandler] that will slide the views left or right, depending on if it's a push or pop.
 *
 * More example can be found @https://github.com/bluelinelabs/Conductor/tree/develop/demo/src/main/java/com/bluelinelabs/conductor/demo/changehandler
 */
class HorizontalChangeHandler : AnimatorChangeHandler {

    @JvmOverloads constructor(duration: Long = DEFAULT_ANIMATION_DURATION, removesFromViewOnPush: Boolean = true) : super(duration, removesFromViewOnPush)

    override fun getAnimator(container: ViewGroup, from: View?, to: View?, isPush: Boolean, toAddedToContainer: Boolean): Animator {
        val animatorSet = AnimatorSet()

        if (isPush) {
            if (from != null) {
                animatorSet.play(ObjectAnimator.ofFloat<View>(from, View.TRANSLATION_X, -from.width.toFloat()))
            }
            if (to != null) {
                animatorSet.play(ObjectAnimator.ofFloat<View>(to, View.TRANSLATION_X, to.width.toFloat(), 0f))
            }
        } else {
            if (from != null) {
                animatorSet.play(ObjectAnimator.ofFloat<View>(from, View.TRANSLATION_X, from.width.toFloat()))
            }
            if (to != null) {
                animatorSet.play(ObjectAnimator.ofFloat<View>(to, View.TRANSLATION_X, -to.width.toFloat(), 0f))
            }
        }

        return animatorSet
    }

    override fun resetFromView(from: View) {
        from.translationX = 0f
    }
}