package be.vergauwen.simon.konductor.core.di.component

import be.vergauwen.simon.common.di.scopes.ViewScope
import be.vergauwen.simon.konductor.core.di.activity.ActionBarProvider
import dagger.Component

@ViewScope
@Component(dependencies = arrayOf(ActivityComponent::class))
interface BaseControllerComponent {
    val actionBarProvider: ActionBarProvider
}