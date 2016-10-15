package be.vergauwen.simon.mockito1_kotlin/*
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

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.notNullValue
import org.junit.Test
import org.mockito.Mockito.RETURNS_DEEP_STUBS
import org.mockito.exceptions.base.MockitoException
import java.util.*

class MockTest {

    private lateinit var propertyInterfaceVariable: MyInterface
    private lateinit var propertyClassVariable: MyClass

    @Test
    fun localInterfaceValue() {
        /* When */
        val instance: MyInterface = mock()

        /* Then */
        assertThat(instance, notNullValue())
    }

    @Test
    fun propertyInterfaceVariable() {
        /* When */
        propertyInterfaceVariable = mock()

        /* Then */
        assertThat(propertyInterfaceVariable, notNullValue())
    }

    @Test
    fun localClassValue() {
        /* When */
        val instance: MyClass = mock()

        /* Then */
        assertThat(instance, notNullValue())
    }

    @Test
    fun propertyClassVariable() {
        /* When */
        propertyClassVariable = mock()

        /* Then */
        assertThat(propertyClassVariable, notNullValue())
    }

    @Test
    fun untypedVariable() {
        /* When */
        val instance = mock<MyClass>()

        assertThat(instance, notNullValue())
    }

    @Test(expected = MockitoException::class)
    fun closedClass() {
        mock<ClosedClass>()
    }

    @Test
    fun deepStubs() {
        val cal: Calendar = mock(RETURNS_DEEP_STUBS)
        whenever(cal.time.time).thenReturn(123L)
        assertThat(cal.time.time, `is`(123L))
    }

    private interface MyInterface
    private open class MyClass
    private class ClosedClass
}

