plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.learningwithteam.classcrate1"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.learningwithteam.classcrate1"
        minSdk = 24
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
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)


    implementation(libs.lifecycle.livedata.ktx)
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)


    // pdf viewer
    implementation (libs.mhiew.android.pdf.viewer)


    implementation(libs.navigation.fragment.ktx)
    implementation(libs.navigation.ui.ktx)
//    implementation("androidx.navigation:navigation-fragment-ktx:2.7.4")
//    implementation("androidx.navigation:navigation-ui-ktx:2.7.4")


    implementation ("androidx.appcompat:appcompat:1.6.1")
    implementation ("com.google.android.material:material:1.11.0")



    // glide library for image loading of youtube thumbnail
//    implementation("com.github.bumptech.glide:glide:4.14.2")
//    annotationProcessor("com.github.bumptech.glide:compiler:4.14.2")


    // for youtube video thumbnail
    implementation("com.github.bumptech.glide:glide:4.16.0")


    // 10:43pm, 2/06/25 -> add this for pdf viewer
//    implementation(libs.android.pdf.viewer)



}