package be.vergauwen.simon.common.di.modules

import be.vergauwen.simon.common.di.data.RxDataProvider
import be.vergauwen.simon.common.di.data.RxDataRepo
import be.vergauwen.simon.common.di.scopes.ViewScope
import dagger.Module
import dagger.Provides

@Module
class DataModule() {

    @ViewScope
    @Provides
    fun provideRxDataProvider() : RxDataProvider = RxDataRepo()
}