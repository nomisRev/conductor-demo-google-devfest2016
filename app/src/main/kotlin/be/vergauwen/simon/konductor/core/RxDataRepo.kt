package be.vergauwen.simon.konductor.core

import be.vergauwen.simon.konductor.core.model.*
import rx.Observable

object RxDataRepo {

    fun getData(): Observable<Item> = Observable.from(arrayOf(URLItem("appfoundry.be"), MailItem("info@appfoundry.be"), TwitterItem("@AppFoundryBE"), URLItem("github.com/appfoundry"),
            URLItem("facebook.com/appfoundrybe"), URLItem("linkedin.com/company/appfoundry"), URLItem("plus.google.com/+appfoundrybe"), PhoneItem("+32(0)38719966")))
}