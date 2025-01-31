plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.google.ksp)
}

android {
    namespace = "com.danielolvera.todonotes"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.danielolvera.todonotes"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
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
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.lifecycle.viewmodel.compose)
    implementation(libs.lifecycle.livedata.ktx)
    implementation(libs.lifecycle.runtime.compose)
    implementation(libs.lifecycle.viewmodel.savedstate)

    implementation(libs.androidx.constraintlayout)

    implementation(libs.gson)
    implementation(libs.converter.gson)

    implementation(libs.navigation.compose)

    implementation ("com.squareup.retrofit2:retrofit:2.11.0")
    implementation ("com.squareup.okhttp3:okhttp:4.9.0")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.5")
    implementation("androidx.compose.runtime:runtime-livedata:1.7.2")
    implementation("androidx.compose.runtime:runtime:1.7.2")
    implementation("com.squareup.okhttp3:okhttp")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

    implementation("io.coil-kt.coil3:coil-compose:3.0.0-rc01")
    implementation("io.coil-kt.coil3:coil-network-okhttp:3.0.0-rc01")
    implementation("com.google.android.gms:play-services-location:18.0.0")

    implementation ("androidx.activity:activity-ktx:1.6.1")
    implementation ("com.google.accompanist:accompanist-permissions:0.24.13-rc")

    // for Android:
    testImplementation ("org.amshove.kluent:kluent-android:1.73")

    // Optional -- Mockito framework
    testImplementation ("org.mockito:mockito-core:4.8.0")
    // Optional -- mockito-kotlin
    testImplementation ("org.mockito.kotlin:mockito-kotlin:5.4.0")
    testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.9.0")

    testImplementation (libs.core.testing)

    implementation("io.coil-kt.coil3:coil-gif:3.0.0-rc01")

    val room_version = "2.6.1"

    implementation("androidx.room:room-runtime:$room_version")
    annotationProcessor("androidx.room:room-compiler:$room_version")

    // To use Kotlin annotation processing tool (kapt)
    ksp("androidx.room:room-compiler:$room_version")

    // optional - Kotlin Extensions and Coroutines support for Room
    implementation("androidx.room:room-ktx:$room_version")

    // optional - Test helpers
    testImplementation("androidx.room:room-testing:$room_version")

    androidTestImplementation ("androidx.test:runner:1.5.2")
    testImplementation ("androidx.test:core:1.2.0")

    testImplementation ("org.amshove.kluent:kluent-android:1.73")

}