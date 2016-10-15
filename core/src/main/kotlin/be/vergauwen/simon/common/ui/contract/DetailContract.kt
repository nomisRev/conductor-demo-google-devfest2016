package be.vergauwen.simon.common.ui.contract

import be.vergauwen.simon.common.mvp.MVPContract


interface DetailContract {
    interface View : MVPContract.View {
    }

    interface Presenter<V : View> : MVPContract.Presenter<V> {
    }

    interface Component<V : View, out P : Presenter<V>> : MVPContract.Component<V, P>
}