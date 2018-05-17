package com.example.daniel.myapplication

import android.util.Log
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import junit.framework.Assert.assertEquals
import org.junit.Test

import org.junit.Assert.*
import java.sql.Time
import java.util.concurrent.TimeUnit

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
/*        Observable.just("test").startWith("xxx")
                .startWith("233")
                .startWith("351")
                .subscribe{System.out.println(it)}*/
        val observable = Observable.just(1).delay(2000,TimeUnit.MILLISECONDS)
        System.out.println("start")
        Observable.just("Test")
                .startWith("xxx")
                .startWith("233")
                .startWith("351")
                .delaySubscription(observable)
                .subscribe{System.out.println(it)}
        Thread.sleep(5000)
        System.out.println("end")
    }

    @Test
    fun  testHex(){
        val tt:Long = 0xaaffffff
        val tt2:Int = 0xaaffffff.toInt()
        System.out.print("$tt : $tt2")
    }
    @Test
   fun testRange(){
        val tmp = 0
        when(tmp){
            0 -> {System.out.print("tmp $tmp")}
            in 0..10 ->{System.out.print("0..10 tmp $tmp")}
        }



    }
}
