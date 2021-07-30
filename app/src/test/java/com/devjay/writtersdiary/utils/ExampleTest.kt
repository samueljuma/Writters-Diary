package com.devjay.writtersdiary.utils

import junit.framework.TestCase
import org.junit.Test
import org.junit.runner.RunWith
import com.google.common.truth.Truth.assertThat;
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ExampleTest : TestCase(){

    @Test
    fun whenEven (){
        var number = 4

        assertThat(Example.checkEven(number)).isTrue()

    }
}