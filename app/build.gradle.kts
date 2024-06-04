plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.hiltAndroid)
    id("kotlin-kapt")
    alias(libs.plugins.navigationArgs)
}

android {
    namespace = "com.ulan.youtube"
    compileSdk = config.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.ulan.youtube"
        minSdk = config.versions.minSdk.get().toInt()
        targetSdk = config.versions.targetSdk.get().toInt()
        versionCode = config.versions.versionCode.get().toInt()
        versionName = config.versions.versionName.get()

        buildConfigField("String", "BASE_URL", "\"https://www.googleapis.com/youtube/v3/\"")
        buildConfigField("String", "CHANNEL_ID", "\"UC2oIn4q3AiBK5-I-NbqMvFw\"")
        buildConfigField("String", "API_KEY", "\"AIzaSyA54z67UdPN8RmC7X2TmV57bVCDNPnqEdA\"")
        testInstrumentationRunner = config.versions.testInstrumentationRunner.get()
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile(config.versions.getDefaultProguardFile.get()),
                config.versions.proguardFiles.get()
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
        viewBinding = true
        buildConfig = true
    }
}

dependencies {
    implementation(libs.bundles.ui.component)
    implementation(libs.bundles.test.component)
    //ExoPlayer
    implementation(libs.bundles.exo.component)

    //Retrofit
    implementation(libs.bundles.network.component)

    //Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    //Koin
    implementation(libs.bundles.koin.component)

    //Nav Component
    implementation(libs.bundles.navigaion.component)

    //OkHttp
    implementation(platform(libs.okhttp.bom))
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)

}