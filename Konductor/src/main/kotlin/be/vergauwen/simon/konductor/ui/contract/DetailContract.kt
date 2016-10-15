package be.vergauwen.simon.konductor.ui.contract

import be.vergauwen.simon.konductor.core.mvp.KonductorMVPContract

interface DetailContract {
    interface View : KonductorMVPContract.View {
    }

    interface Presenter<V : View> : KonductorMVPContract.Presenter<V> {
    }

    interface Component<V : View, out P : Presenter<V>> : KonductorMVPContract.Component<V, P>
}