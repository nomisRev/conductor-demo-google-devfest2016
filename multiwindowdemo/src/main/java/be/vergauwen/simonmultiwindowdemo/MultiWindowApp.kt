package be.vergauwen.simonmultiwindowdemo

import android.app.Application
import com.squareup.leakcanary.LeakCanary
import com.squareup.leakcanary.RefWatcher
import timber.log.Timber

class MultiWindowApp : Application() {

    var refWatcher: RefWatcher? = null

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            refWatcher = LeakCanary.install(this)
        }
    }
}