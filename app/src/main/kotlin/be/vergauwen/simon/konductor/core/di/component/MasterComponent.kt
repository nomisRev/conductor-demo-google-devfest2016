package be.vergauwen.simon.konductor.core.di.component

import be.vergauwen.simon.konductor.core.di.modules.BaseModule
import be.vergauwen.simon.konductor.core.di.scopes.ViewScope
import dagger.Component

@ViewScope
@Component(dependencies = arrayOf(ActivityComponent::class), modules = arrayOf(BaseModule::class))
interface MasterComponent : BaseComponent {

}