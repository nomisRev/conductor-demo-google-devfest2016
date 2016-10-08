package be.vergauwen.simon.konductor.ui.contract

import be.vergauwen.simon.konductor.core.mvp.MVPContract

interface DetailContract {
    interface View : MVPContract.View {
    }

    interface Presenter<V : View> : MVPContract.Presenter<V> {
    }

    interface Component<V : View, out P : Presenter<V>> : MVPContract.Component<V, P>
}