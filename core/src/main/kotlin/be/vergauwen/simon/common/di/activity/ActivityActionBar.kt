package be.vergauwen.simon.common.di.activity

import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity

class ActivityActionBar(activity: AppCompatActivity) : ActionBarProvider {

    private val actionBar : ActionBar?

    init {
        this.actionBar = activity.supportActionBar
    }

    override val supportActionBar: ActionBar?
        get() = actionBar
}