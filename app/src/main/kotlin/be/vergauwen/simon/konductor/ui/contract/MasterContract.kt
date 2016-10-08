package be.vergauwen.simon.konductor.ui.contract

import be.vergauwen.simon.konductor.core.model.Item
import be.vergauwen.simon.konductor.core.mvp.MVPContract

interface MasterContract {
    interface View : MVPContract.View {
        fun addItem(item: Item)
    }

    interface Presenter<V : View> : MVPContract.Presenter<V> {
        fun getData()
    }

    interface Component<V : View, out P : Presenter<V>> : MVPContract.Component<V, P>
}