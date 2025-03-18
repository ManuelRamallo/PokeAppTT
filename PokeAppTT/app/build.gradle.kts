plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.dagger)
    alias(libs.plugins.kotlin.serialization)
    kotlin("kapt")
}

android {
    namespace = "com.mramallo.pokeapptt"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.mramallo.pokeapptt"
        minSdk = 29
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.ui.test.android)
    implementation(libs.androidx.junit.ktx)
    implementation(libs.androidx.ui.test.junit4.android)
    testImplementation(libs.junit)
    testImplementation(libs.hilt.testing)
    androidTestImplementation(libs.androidx.junit)
    //androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    androidTestImplementation(libs.hilt.testing)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    testImplementation(libs.mockk)
    testImplementation(libs.corroutinesTest)
    testImplementation(libs.archCoreTest)
    testImplementation(libs.testCore)

    implementation(libs.retrofit)
    implementation(libs.retrofit.gson.converter)

    implementation(libs.paging.compose)

    implementation(libs.dagger.hilt)
    implementation(libs.hilt.compose.navigation)
    kapt(libs.dagger.kapt)

    implementation(libs.coil.compose)
    
    implementation(libs.okHttp)

    implementation(libs.coroutine.core)
    implementation(libs.coroutine.android)

    implementation(libs.navigation.compose)
    implementation(libs.kotlin.serialization.json)


}