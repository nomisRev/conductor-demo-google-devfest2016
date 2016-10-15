package be.vergauwen.simon.common.di.data

import be.vergauwen.simon.common.di.model.Item
import rx.Observable

interface RxDataProvider {
    fun getData(): Observable<Item>
}