package be.vergauwen.simon.mockito1_kotlin

import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.Test
import java.util.*

class ArgumentCaptorTest {

    @Test
    fun explicitCaptor() {
        val date: Date = mock()
        val time = argumentCaptor<Long>()

        date.time = 5L

        verify(date).time = capture(time)
        MatcherAssert.assertThat(time.value, Matchers.`is`(5L))
        //expect(time.value).toBe(5L)
    }

    @Test
    fun implicitCaptor() {
        val date: Date = mock()
        date.time = 5L

        verify(date).time = capture {
            MatcherAssert.assertThat(it, Matchers.`is`(5L))
            //expect(it).toBe(5L)
        }
    }
}