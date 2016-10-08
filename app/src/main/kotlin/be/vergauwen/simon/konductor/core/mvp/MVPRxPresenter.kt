package be.vergauwen.simon.konductor.core.mvp

import rx.Completable
import rx.Observable
import rx.Single
import rx.Subscription
import rx.subscriptions.CompositeSubscription

abstract class MVPRxPresenter<V : MVPContract.View> : MVPPresenter<V>() {

    private var compositeSubscription = CompositeSubscription()


    override fun detachView() {
        super.detachView()
        this.compositeSubscription.unsubscribe()
        this.compositeSubscription = CompositeSubscription()
    }

    protected fun <T> add(observable: Observable<T>, onNext: (t: T) -> Unit, onError: (error: Throwable) -> Unit, onComplete: (() -> Unit)? = null, unsubscribeAutomatically: Boolean = true): Subscription {

        val sub = if (onComplete == null) {
            observable.subscribe(onNext, onError)
        } else {
            observable.subscribe(onNext, onError, onComplete)
        }

        if (unsubscribeAutomatically) {
            compositeSubscription.add(sub)
        }

        return sub
    }

    protected fun <T> add(single: Single<T>, onSuccess: (t: T) -> Unit, onFailure: (error: Throwable) -> Unit): Subscription {
        val sub = single.subscribe(onSuccess, onFailure)
        compositeSubscription.add(sub)
        return sub
    }

    protected fun add(completable: Completable, onCompleted: () -> Unit, onError: (error: Throwable) -> Unit): Subscription {
        val sub = completable.subscribe(onCompleted, onError)
        compositeSubscription.add(sub)
        return sub
    }

}