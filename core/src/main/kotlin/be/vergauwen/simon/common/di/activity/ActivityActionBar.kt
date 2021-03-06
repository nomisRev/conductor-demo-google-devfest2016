package be.vergauwen.simon.common.di.activity

import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity

class ActivityActionBar(private val activity: AppCompatActivity) : ActionBarProvider {

    override val supportActionBar: ActionBar?
        get() = activity.supportActionBar
}