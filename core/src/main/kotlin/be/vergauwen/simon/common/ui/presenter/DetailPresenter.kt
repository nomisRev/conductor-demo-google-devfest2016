package be.vergauwen.simon.common.ui.presenter

import be.vergauwen.simon.common.mvp.MVPRxPresenter
import be.vergauwen.simon.common.ui.contract.DetailContract
import javax.inject.Inject

class DetailPresenter @Inject constructor() : MVPRxPresenter<DetailContract.View>(), DetailContract.Presenter<DetailContract.View> {

}