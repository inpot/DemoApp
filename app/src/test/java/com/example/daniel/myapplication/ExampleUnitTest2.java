package com.example.daniel.myapplication;

import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
public class ExampleUnitTest2 {

    @Test
   public void testHex2(){
        long tt = 0xaaffffff;
        int tt2 = 0xaaffffff;
        System.out.print(  tt + " : " + tt2);
    }
}
