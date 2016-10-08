package be.vergauwen.simon.konductor.ui.component

import be.vergauwen.simon.konductor.core.di.component.ActivityComponent
import be.vergauwen.simon.konductor.core.di.data.RxDataProvider
import be.vergauwen.simon.konductor.core.di.modules.DataModule
import be.vergauwen.simon.konductor.core.di.scopes.ViewScope
import be.vergauwen.simon.konductor.ui.contract.MasterContract
import be.vergauwen.simon.konductor.ui.presenter.MasterPresenter
import dagger.Component

@ViewScope
@Component(dependencies = arrayOf(ActivityComponent::class), modules = arrayOf(DataModule::class))
interface MasterComponent : MasterContract.Component<MasterContract.View, MasterPresenter> {
    override val presenter: MasterPresenter
    val rxDataProvider: RxDataProvider
}