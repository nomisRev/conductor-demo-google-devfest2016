package be.vergauwen.simon.mockito1_kotlin

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.nullValue
import org.junit.Test
import kotlin.test.assertFailsWith

/*
 * The MIT License
 *
 * Copyright (c) 2016 Niek Haarman
 * Copyright (c) 2007 Mockito contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

class MockitoTest {

    @Test
    fun anyString() {
        mock<Methods>().apply {
            string("")
            verify(this).string(any())
        }
    }

    @Test
    fun anyClosedClass() {
        mock<Methods>().apply {
            closed(Closed())
            verify(this).closed(any())
        }
    }

    @Test
    fun anyIntArray() {
        mock<Methods>().apply {
            intArray(intArrayOf())
            verify(this).intArray(any())
        }
    }

    @Test
    fun anyClassArray() {
        mock<Methods>().apply {
            closedArray(arrayOf(Closed()))
            verify(this).closedArray(anyArray())
        }
    }

    @Test
    fun anyNullableClassArray() {
        mock<Methods>().apply {
            closedNullableArray(arrayOf(Closed(), null))
            verify(this).closedNullableArray(anyArray())
        }
    }

    @Test
    fun anyCollectionOfClosed() {
        mock<Methods>().apply {
            closedCollection(listOf())
            verify(this).closedCollection(any())
            verify(this).closedCollection(anyCollection())
        }
    }

    @Test
    fun anyListOfClosed() {
        mock<Methods>().apply {
            closedList(listOf())
            verify(this).closedList(any())
            verify(this).closedList(anyList())
        }
    }

    @Test
    fun anyClosedStringMap() {
        mock<Methods>().apply {
            closedStringMap(mapOf())
            verify(this).closedStringMap(any())
            verify(this).closedStringMap(anyMap())
        }
    }

    @Test
    fun anyClosedSet() {
        mock<Methods>().apply {
            closedSet(setOf())
            verify(this).closedSet(any())
            verify(this).closedSet(anySet())
        }
    }

    @Test
    fun anyStringVararg() {
        mock<Methods>().apply {
            closedVararg(Closed(), Closed())
            verify(this).closedVararg(anyVararg())
        }
    }

    /**
     *
     * Currently failing reason undetermined postponed
     *
     */
    //    @Test
//    fun anyThrowableWithSingleThrowableConstructor() {
//        mock<Methods>().apply {
//            throwableClass(ThrowableClass(IOException()))
//            verify(this).throwableClass(any())
//        }
//    }

    @Test
    fun listArgThat() {
        mock<Methods>().apply {
            closedList(listOf(Closed(), Closed()))
            verify(this).closedList(argThat {
                size == 2
            })
        }
    }
    @Test
    fun listArgForWhich() {
        mock<Methods>().apply {
            closedList(listOf(Closed(), Closed()))
            verify(this).closedList(argForWhich {
                size == 2
            })
        }
    }

    @Test
    fun atLeastXInvocations() {
        mock<Methods>().apply {
            string("")
            string("")

            verify(this, atLeast(2)).string(any())
        }
    }

    @Test
    fun testAtLeastOnce() {
        mock<Methods>().apply {
            string("")
            string("")

            verify(this, atLeastOnce()).string(any())
        }
    }

    @Test
    fun atMostXInvocations() {
        mock<Methods>().apply {
            string("")
            string("")

            verify(this, atMost(2)).string(any())
        }
    }

    @Test
    fun testCalls() {
        mock<Methods>().apply {
            string("")
            string("")

            inOrder(this).verify(this, calls(2)).string(any())
        }
    }

    @Test
    fun testClearInvocations() {
        val mock = mock<Methods>().apply {
            string("")
        }

        reset(mock)

        verify(mock, never()).string(any())
    }

    @Test
    fun testDoAnswer() {
        val mock = mock<Methods>()

        doAnswer { "Test" }
                .whenever(mock)
                .stringResult()
        assertThat(mock.stringResult(), `is`("Test"))
    }

    @Test
    fun testDoCallRealMethod() {
        val mock = mock<Open>()

        doReturn("Test").whenever(mock).stringResult()
        doCallRealMethod().whenever(mock).stringResult()

        assertThat(mock.stringResult(), `is`("Default"))
    }

    @Test
    fun testDoNothing() {
        val spy = spy(Open())
        val array = intArrayOf(3)

        doNothing().whenever(spy).modifiesContents(array)
        spy.modifiesContents(array)

        assertThat(array[0], `is`(3))
    }

    @Test
    fun testDoReturnValue() {
        val mock = mock<Methods>()

        doReturn("test").whenever(mock).stringResult()

        assertThat(mock.stringResult(), `is`("test"))

    }

    @Test
    fun testDoReturnNullValue() {
        val mock = mock<Methods>()

        doReturn(null).whenever(mock).stringResult()

        assertThat(mock.stringResult(), nullValue())
    }

    @Test
    fun testDoReturnNullValues() {
        val mock = mock<Methods>() {
            on{ stringResult() }.doReturn(null,null)
        }

        //Not supported in Mockito 1
        //doReturn(null, null).whenever(mock).stringResult()

        assertThat(mock.stringResult(), nullValue())
        assertThat(mock.stringResult(), nullValue())
    }

    @Test
    fun testDoReturnValues() {
        val mock = mock<Methods>()
        whenever(mock.stringResult()).thenReturn("test", "test2")

        //Not supported in Mockito 1
        //doReturn("test", "test2").whenever(mock).stringResult()
        assertThat(mock.stringResult(), `is`("test"))
        assertThat(mock.stringResult(), `is`("test2"))
    }

    @Test
    fun testDoThrowClass() {
        val mock = mock<Open>()

        doThrow(IllegalStateException::class).whenever(mock).go()

        assertFailsWith(IllegalStateException::class){
            mock.go()
        }
    }

    @Test
    fun testMockStubbing_lambda() {
        /* Given */
        val mock = mock<Open>() {
            on { stringResult() } doReturn "A"
        }

        /* When */
        val result = mock.stringResult()

        /* Then */
        assertThat(result, `is`("A"))
    }

    @Test
    fun testMockStubbing_normalOverridesLambda() {
        /* Given */
        val mock = mock<Open>() {
            on { stringResult() }.doReturn("A")
        }
        whenever(mock.stringResult()).thenReturn("B")

        /* When */
        val result = mock.stringResult()

        /* Then */
        assertThat(result, `is`("B"))
    }

    @Test
    fun testMockStubbing_methodCall() {
        /* Given */
        val mock = mock<Open>()
        mock<Open> {
            on(mock.stringResult()).doReturn("A")
        }

        /* When */
        val result = mock.stringResult()

        /* Then */
        assertThat(result, `is`("A"))
    }

    @Test
    fun doReturn_withSingleItemList() {
        /* Given */
        val mock = mock<Open> {
            on { stringResult() } doReturn listOf("a", "b")
        }

        /* Then */
        assertThat(mock.stringResult(), `is`("a"))
        assertThat(mock.stringResult(), `is`("b"))
    }
}
