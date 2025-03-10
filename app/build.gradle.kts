import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.dagger.hilt)
    alias(libs.plugins.kotlin.serialization)
    kotlin("kapt")
}

android {
    namespace = "edts.android.composesandbox"
    compileSdk = 35

    defaultConfig {
        applicationId = "edts.android.composesandbox"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    //TODO: Enable if want the release build to be signed
//    signingConfigs {
//        create("release"){
//            val properties = Properties().apply {
//                load(File("key.properties").reader())
//            }
//            storePassword = properties.getProperty("storePassword")
//            keyPassword = properties.getProperty("keyPassword")
//            keyAlias = properties.getProperty("keyAlias")
//            storeFile = File(properties.getProperty("storeFile"))
//        }
//    }

    buildTypes {
        release {
            isMinifyEnabled = true
            //TODO: Enable if want the release build to be signed
//            signingConfig = signingConfigs.getByName("release")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
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
    implementation(libs.androidx.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation(libs.dagger.hilt)
    kapt(libs.dagger.hilt.compiler)
    implementation(libs.compose.nav)
    implementation(libs.kotlin.serialization)
    implementation(libs.dot.lottie)
    implementation(libs.datastore)
    implementation(libs.hilt.nav)
    implementation(libs.graphic.shapes)
    implementation(libs.coil)
}

hilt {
    enableAggregatingTask = true
}
