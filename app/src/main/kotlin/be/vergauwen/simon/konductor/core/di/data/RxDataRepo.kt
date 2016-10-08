package be.vergauwen.simon.konductor.core.di.data

import be.vergauwen.simon.konductor.core.model.*
import rx.Observable

class RxDataRepo() : RxDataProvider {

    override fun getData(): Observable<Item> = Observable.from(arrayOf(URLItem("appfoundry.be"), MailItem("info@appfoundry.be"), TwitterItem("@AppFoundryBE"), URLItem("github.com/appfoundry"),
            URLItem("facebook.com/appfoundrybe"), URLItem("linkedin.com/company/appfoundry"), URLItem("plus.google.com/+appfoundrybe"), PhoneItem("+32(0)38719966")))
}