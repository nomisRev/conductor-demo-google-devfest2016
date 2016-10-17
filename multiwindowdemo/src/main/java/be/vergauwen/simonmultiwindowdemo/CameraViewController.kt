package be.vergauwen.simonmultiwindowdemo

import android.app.Activity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.bluelinelabs.conductor.Controller
import com.google.android.cameraview.CameraView
import org.jetbrains.anko.UI
import org.jetbrains.anko.frameLayout
import rx.Observable
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import timber.log.Timber
import java.util.concurrent.TimeUnit

class CameraViewController(args: Bundle? = null) : Controller(args) {

    private var cameraView: CameraView? = null
    private var frameLayout: FrameLayout? = null
    private val colors = listOf(android.R.color.black, android.R.color.white, android.R.color.holo_purple, android.R.color.holo_orange_dark)
    private var subscription: Subscription? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View = container.context.UI {
        frameLayout = frameLayout {
            cameraView = cameraView { }
        }
    }.view

    override fun onAttach(view: View) {
        super.onAttach(view)
        cameraView?.start()
    }

    override fun onActivityPaused(activity: Activity?) {
        cameraView?.stop()
        startSwitchingColors()
        super.onActivityPaused(activity)
    }

    override fun onDetach(view: View) {
        subscription?.unsubscribe()
        subscription = null
        super.onDetach(view)
    }

    private fun startSwitchingColors() {
        subscription = Observable.interval(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { frameLayout?.setBackgroundColor(ContextCompat.getColor(activity, colors[(it % 4).toInt()])) },
                        { Timber.e(it.message) }
                )
    }

}