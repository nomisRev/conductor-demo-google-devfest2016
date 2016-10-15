package be.vergauwen.simon.common.ui.presenter

import be.vergauwen.simon.common.di.data.RxDataProvider
import be.vergauwen.simon.common.mvp.MVPRxPresenter
import be.vergauwen.simon.common.ui.contract.MasterContract
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class MasterPresenter @Inject constructor(private val rxDataProvider: RxDataProvider) : MVPRxPresenter<MasterContract.View>(), MasterContract.Presenter<MasterContract.View> {

    override fun getData() {
        add(rxDataProvider.getData().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()),
                onNext = { view?.addItem(it) },
                onError = { Timber.e(it.message) }
        )
    }
}