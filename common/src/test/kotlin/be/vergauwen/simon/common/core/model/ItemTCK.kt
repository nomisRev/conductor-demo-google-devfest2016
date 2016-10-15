package be.vergauwen.simon.common.core.model

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import be.vergauwen.simon.common.di.model.*
import be.vergauwen.simon.mockito1_kotlin.any
import be.vergauwen.simon.mockito1_kotlin.mock
import be.vergauwen.simon.mockito1_kotlin.whenever
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import org.powermock.api.mockito.PowerMockito
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner
import org.powermock.modules.junit4.PowerMockRunnerDelegate


@RunWith(PowerMockRunner::class)
@PowerMockRunnerDelegate(JUnitPlatform::class)
@PrepareForTest(ContextCompat::class)
abstract class ItemTCK(val factory: () -> Item) : Spek({
    val item = factory()
    describe("handling an item object") {
        val context = mock<Context>()
        val drawable = mock<Drawable>()
        beforeEach {
            PowerMockito.mockStatic(ContextCompat::class.java)
            whenever(ContextCompat.getDrawable(any<Context>(), any<Int>()))
                    .thenReturn(drawable)
        }

        it("should never have a null name") {
            MatcherAssert.assertThat(item.name, Matchers.notNullValue())
        }
        it("should always have a color id") {
            MatcherAssert.assertThat(item.itemColorId, Matchers.isA(Int::class.java))
        }
        it("should always return a drawable") {
            MatcherAssert.assertThat(item.getIcon(context), Matchers.equalTo(drawable))
        }
    }
})

class URLItemTCKTest : ItemTCK({ URLItem("www.appfoundry.be") })

class TwitterItemTCKTest : ItemTCK({ TwitterItem("@AppFoundryBE") })

class MailItemTCKTest : ItemTCK({ MailItem("simon.vergauwen@appfoundry.be") })

class PhoneItemTCKTest : ItemTCK({ PhoneItem("003238719966") })