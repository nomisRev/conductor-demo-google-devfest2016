package be.vergauwen.simon.konductor.ui.component

import be.vergauwen.simon.konductor.core.di.component.ActivityComponent
import be.vergauwen.simon.konductor.core.di.scopes.ViewScope
import be.vergauwen.simon.konductor.ui.contract.DetailContract
import be.vergauwen.simon.konductor.ui.presenter.DetailPresenter
import dagger.Component

@ViewScope
@Component(dependencies = arrayOf(ActivityComponent::class))
interface DetailComponent : DetailContract.Component<DetailContract.View, DetailPresenter> {
    override val presenter: DetailPresenter
}