package be.vergauwen.simon.common.mvp

abstract class MVPPresenter<V : MVPContract.View> : MVPContract.Presenter<V> {
    override var view: V? = null

    override fun attachView(view: V) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }
}