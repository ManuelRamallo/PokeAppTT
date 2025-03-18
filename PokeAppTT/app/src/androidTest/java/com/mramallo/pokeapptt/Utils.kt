package com.mramallo.pokeapptt

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.captureToImage
import androidx.test.core.graphics.writeToTestStorage
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert

val ctx = InstrumentationRegistry.getInstrumentation().context

fun SemanticsNodeInteraction.writeToTestStorage(assetFile: String){
    val bitmap = captureToImage().asAndroidBitmap()
    bitmap.writeToTestStorage(assetFile)
}

fun SemanticsNodeInteraction.assertMatchesGolden(assetFile: String){
    val bitmap = captureToImage().asAndroidBitmap()
    bitmap.assertMatchesGolden(assetFile)
}

fun Bitmap.assertMatchesGolden(assetFile: String) {
    val golden = ctx.assets
        .open(assetFile)
        .use(BitmapFactory::decodeStream)

    Assert.assertTrue(this.sameAs(golden))
}