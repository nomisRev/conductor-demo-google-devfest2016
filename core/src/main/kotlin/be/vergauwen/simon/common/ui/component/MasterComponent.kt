package be.vergauwen.simon.common.ui.component

import be.vergauwen.simon.common.di.component.ActivityComponent
import be.vergauwen.simon.common.di.data.RxDataProvider
import be.vergauwen.simon.common.di.modules.DataModule
import be.vergauwen.simon.common.di.scopes.ViewScope
import be.vergauwen.simon.common.ui.contract.MasterContract
import be.vergauwen.simon.common.ui.presenter.MasterPresenter
import dagger.Component

@ViewScope
@Component(dependencies = arrayOf(ActivityComponent::class), modules = arrayOf(DataModule::class))
interface MasterComponent : MasterContract.Component<MasterContract.View, MasterPresenter> {
    override val presenter: MasterPresenter
    val rxDataProvider: RxDataProvider
}