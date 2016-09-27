package be.vergauwen.simon.konductor

import android.app.Application
import com.squareup.leakcanary.LeakCanary
import com.squareup.leakcanary.RefWatcher
import timber.log.Timber

class KonductorApp : Application() {

    var refWatcher: RefWatcher? = null

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            LeakCanary.install(this)
        }
    }

}