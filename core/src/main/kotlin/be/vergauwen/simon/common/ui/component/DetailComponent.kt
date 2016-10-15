package be.vergauwen.simon.common.ui.component

import be.vergauwen.simon.common.di.component.ActivityComponent
import be.vergauwen.simon.common.di.scopes.ViewScope
import be.vergauwen.simon.common.ui.contract.DetailContract
import be.vergauwen.simon.common.ui.presenter.DetailPresenter
import dagger.Component

@ViewScope
@Component(dependencies = arrayOf(ActivityComponent::class))
interface DetailComponent : DetailContract.Component<DetailContract.View, DetailPresenter> {
    override val presenter: DetailPresenter
}