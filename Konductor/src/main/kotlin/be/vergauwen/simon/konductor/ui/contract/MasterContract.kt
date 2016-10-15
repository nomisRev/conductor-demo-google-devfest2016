package be.vergauwen.simon.konductor.ui.contract

import be.vergauwen.simon.common.di.model.Item
import be.vergauwen.simon.konductor.core.mvp.KonductorMVPContract

interface MasterContract {
    interface View : KonductorMVPContract.View {
        fun addItem(item: Item)
    }

    interface Presenter<V : View> : KonductorMVPContract.Presenter<V> {
        fun getData()
    }

    interface Component<V : View, out P : Presenter<V>> : KonductorMVPContract.Component<V, P>
}