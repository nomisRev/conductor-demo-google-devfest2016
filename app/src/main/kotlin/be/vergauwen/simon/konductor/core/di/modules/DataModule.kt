package be.vergauwen.simon.konductor.core.di.modules

import be.vergauwen.simon.konductor.core.di.data.RxDataProvider
import be.vergauwen.simon.konductor.core.di.data.RxDataRepo
import be.vergauwen.simon.konductor.core.di.scopes.ViewScope
import dagger.Module
import dagger.Provides

@Module
class DataModule() {

    @ViewScope
    @Provides
    fun provideRxDataProvider() : RxDataProvider = RxDataRepo()
}