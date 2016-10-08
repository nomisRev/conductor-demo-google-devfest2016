package be.vergauwen.simon.konductor.core.di.data

import be.vergauwen.simon.konductor.core.model.Item
import rx.Observable

interface RxDataProvider {
    fun getData(): Observable<Item>
}