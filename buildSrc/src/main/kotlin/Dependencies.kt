import org.gradle.kotlin.dsl.provideDelegate

object BuildPlugins {
    const val kotlinGradlePlugin            = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinVersion}"
    const val androidGradlePlugin           = "com.android.tools.build:gradle:${Versions.buildToolsVersion}"
    const val navigationGradlePlugin        = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"

    const val androidApplication            = "com.android.application"
    const val kotlinAndroid                 = "kotlin-android"
    const val kotlinAndroidExtensions       = "kotlin-android-extensions"
    const val kotlinKapt                    = "kotlin-kapt"
    const val kotlinSafeArgs                = "androidx.navigation.safeargs.kotlin"


    const val applicationId                 = "com.star.war"
    private val versions                    = "1.0.0"
    val androidVersionCode                  = versions[0].toString().toInt()
    val androidVersionName                  = versions[1].toString()

    const val testInstrumentationRunner     = "androidx.test.runner.AndroidJUnitRunner"
}

object AndroidSdk {
    const val min = 21
    const val compile = 30
    const val buildToolVersion = "30.0.3"
}

object Libraries {

    const val kotlinStdLib                  = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlinVersion}"

    // android
    const val design                        = "com.google.android.material:material:${Versions.design}"

    const val multidex                      = "androidx.multidex:multidex:${Versions.multidex}"
    const val appcompat                     = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val coreKtx                       = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val constraintLayout              = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"

    const val databinding                   = "androidx.databinding:databinding-compiler:${Versions.databinding}"
    const val fragmentKtx                   = "androidx.fragment:fragment-ktx:${Versions.fragmentKtx}"

    //ViewModel
    const val lifecycleViewmodelKtx         = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val lifecycleRuntimeKtx           = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
    const val lifecycleSavedstate           = "androidx.lifecycle:lifecycle-viewmodel-savedstate:${Versions.lifecycle}"
    const val lifecycleCommonJava8          = "androidx.lifecycle:lifecycle-common-java8:${Versions.lifecycle}"
    const val lifecycleCompiler             = "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycle}"

    // Navigation
    const val navigationFragmentKtx         = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigationUiKtx               = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"

    // dagger
    const val dagger                        = "com.google.dagger:dagger:${Versions.dagger}"
    const val daggerAndroid                 = "com.google.dagger:dagger-android:${Versions.dagger}"
    const val daggerCompiler                = "com.google.dagger:dagger-compiler:${Versions.dagger}"
    const val daggerProcessor               = "com.google.dagger:dagger-android-processor:${Versions.dagger}"
    const val daggerSupport                 = "com.google.dagger:dagger-android-support:${Versions.dagger}"

    // glide
    const val glide                         = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glideCompiler                 = "com.github.bumptech.glide:compiler:${Versions.glide}"

    // retrofit
    const val retrofit                      = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitRxJavaAdapter         = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
    const val gsonConverter                 = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val scalarConverter               = "com.squareup.retrofit2:converter-scalars:${Versions.retrofit}"
    const val moshiConverter                = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    const val okHttpLog                     = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttpLog}"

    // rxjava
    const val rxJava                        = "io.reactivex.rxjava2:rxjava:${Versions.rxJava}"
    const val rxAndroid                     = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroid}"

    // rx bindings
    const val rxDataBindings                = "com.stepango.rxdatabindings:rxdatabindings:${Versions.rxDataBindings}"
    const val rxkotlin                      = "io.reactivex.rxjava2:rxkotlin:${Versions.rxkotlin}"

    // timber
    const val timber                        = "com.jakewharton.timber:timber:${Versions.timber}"
}

object TestLibraries {
    // testing
    const val junit                         = "junit:junit:${Versions.junit}"
    const val junitExt                      = "androidx.test.ext:junit:${Versions.junitExt}"
    const val mockitoKotlin                 = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockitoKotlin}"
    const val espressoCore                  = "com.android.support.test.espresso:espresso-core:${Versions.espressoVersion}"


    const val mockk                         = "io.mockk:mockk:${Versions.mockk}"
    const val coreTest                      = "androidx.arch.core:core-testing:${Versions.coreTest}"
    const val assertjCore                   = "org.assertj:assertj-core:${Versions.assertjCore}"
    const val jUnitParams                   = "pl.pragmatists:JUnitParams:${Versions.jUnitParams}"
}