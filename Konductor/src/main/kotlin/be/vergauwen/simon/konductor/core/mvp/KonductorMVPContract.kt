package be.vergauwen.simon.konductor.core.mvp

import be.vergauwen.simon.common.mvp.MVPContract
import be.vergauwen.simon.konductor.core.di.component.BaseControllerComponent


interface KonductorMVPContract {

    interface View : MVPContract.View

    interface Presenter<V : View> : MVPContract.Presenter<V>

    interface Component<V : MVPContract.View, out P : MVPContract.Presenter<V>> : MVPContract.Component<V,P>, BaseControllerComponent
}