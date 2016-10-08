package be.vergauwen.simon.konductor.core.mvp

import be.vergauwen.simon.konductor.core.di.component.BaseControllerComponent

interface MVPContract {
    interface View

    interface Presenter<V : MVPContract.View> {
        val view : V?
        fun attachView(view: V)
        fun detachView()
    }

    interface Component<V : MVPContract.View, out P : Presenter<V>> : BaseControllerComponent{
        val presenter: P
    }
}