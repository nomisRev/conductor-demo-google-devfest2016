package be.vergauwen.simon.common.di.component

import be.vergauwen.simon.common.di.activity.ActionBarProvider
import be.vergauwen.simon.common.di.scopes.ViewScope
import dagger.Component

@ViewScope
@Component(dependencies = arrayOf(ActivityComponent::class))
interface BaseControllerComponent {
    val actionBarProvider: ActionBarProvider
}