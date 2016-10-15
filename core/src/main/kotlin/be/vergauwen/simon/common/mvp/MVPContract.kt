package be.vergauwen.simon.common.mvp

import be.vergauwen.simon.common.di.component.BaseControllerComponent

interface MVPContract {
    interface View

    interface Presenter<V : View> {
        val view : V?
        fun attachView(view: V)
        fun detachView()
    }

    interface Component<V : View, out P : Presenter<V>> : BaseControllerComponent {
        val presenter: P
    }
}