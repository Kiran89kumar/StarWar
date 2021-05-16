plugins {
    id(BuildPlugins.androidApplication)
    id(BuildPlugins.kotlinAndroid)
    id(BuildPlugins.kotlinAndroidExtensions)
    id(BuildPlugins.kotlinKapt)
    id(BuildPlugins.kotlinSafeArgs)
}

android {
    compileSdkVersion(AndroidSdk.compile)
    buildToolsVersion(AndroidSdk.buildToolVersion)

    defaultConfig {
        applicationId = BuildPlugins.applicationId
        minSdkVersion(AndroidSdk.min)
        targetSdkVersion(AndroidSdk.compile)
        versionCode = BuildPlugins.androidVersionCode
        versionName = BuildPlugins.androidVersionName
        testInstrumentationRunner = BuildPlugins.testInstrumentationRunner
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles("proguard-rules.pro")
            proguardFiles("proguard-android-optimize.txt")
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
        dataBinding = true
    }
}

dependencies {
    implementation(Libraries.kotlinStdLib)
    implementation(Libraries.coreKtx)
    implementation(Libraries.appcompat)
    implementation(Libraries.design)
    implementation(Libraries.constraintLayout)

    //Dagger
    implementation(Libraries.dagger)
    implementation(Libraries.daggerAndroid)
    implementation(Libraries.daggerSupport)
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    kapt(Libraries.daggerCompiler)
    kapt(Libraries.daggerProcessor)

    //Retrofit
    implementation(Libraries.retrofit)
    implementation(Libraries.retrofitRxJavaAdapter)
    implementation(Libraries.gsonConverter)
    implementation(Libraries.moshiConverter)
    implementation(Libraries.scalarConverter)

    //Interceptor
    implementation(Libraries.okHttpLog)

    //RXJava
    implementation(Libraries.rxJava)
    implementation(Libraries.rxAndroid)

    //ViewModel
    implementation(Libraries.lifecycleViewmodelKtx)
    implementation(Libraries.lifecycleRuntimeKtx)
    implementation(Libraries.lifecycleSavedstate)
    implementation(Libraries.lifecycleCommonJava8)
    kapt(Libraries.lifecycleCompiler)

    // Navigation
    implementation(Libraries.navigationFragmentKtx)
    implementation(Libraries.navigationUiKtx)
    implementation(Libraries.fragmentKtx)

    // rx bindings
    implementation(Libraries.rxDataBindings)
    implementation(Libraries.rxkotlin)

    // Timber
    implementation(Libraries.timber)

    testImplementation(TestLibraries.junit)
    testImplementation(TestLibraries.coreTest)
    testImplementation(TestLibraries.mockk)
    testImplementation(TestLibraries.assertjCore)
    testImplementation(TestLibraries.jUnitParams)
    androidTestImplementation(TestLibraries.junitExt)
    androidTestImplementation(TestLibraries.espressoCore)
}