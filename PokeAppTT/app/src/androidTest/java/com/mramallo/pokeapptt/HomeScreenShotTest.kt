package com.mramallo.pokeapptt

import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onRoot
import androidx.test.core.graphics.writeToTestStorage
import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.HiltAndroidTest
import okio.IOException
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestName
import org.junit.runner.RunWith
import kotlin.jvm.Throws


@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class HomeScreenShotTest {

    @get:Rule(order = 2)
    val activityRule = createAndroidComposeRule<MainActivity>()

    @get:Rule(order = 3)
    val nameRule = TestName()

    @Test
    @Throws(IOException::class)
    fun saveStartScreenToBitmap(): Unit = with(activityRule) {
        onRoot().writeToTestStorage("${javaClass.simpleName}_${nameRule.methodName}")
    }

    @Test
    fun compareToStartScreen(): Unit = with(activityRule) {
        onRoot().assertMatchesGolden("screenshots/AndroidComposeTestRule_screenshotTest.png")
    }


}
