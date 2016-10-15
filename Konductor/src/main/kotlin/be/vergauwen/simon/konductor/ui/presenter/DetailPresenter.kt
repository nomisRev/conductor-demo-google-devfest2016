package be.vergauwen.simon.konductor.ui.presenter

import be.vergauwen.simon.common.mvp.MVPRxPresenter
import be.vergauwen.simon.konductor.ui.contract.DetailContract
import javax.inject.Inject

class DetailPresenter @Inject constructor() : MVPRxPresenter<DetailContract.View>(), DetailContract.Presenter<DetailContract.View> {

}