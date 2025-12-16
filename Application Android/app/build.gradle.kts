plugins {
    id("com.android.application")
}

// TODO mettre l'URL du serveur distant
val URL_API by extra("http://10.0.2.2:8080/api/")

android {
    namespace = "org.iut.roadeo"
    compileSdk = 34

    defaultConfig {

        android.buildFeatures.buildConfig = true
        buildConfigField("String", "API_URL", "\"${extra["URL_API"]}\"")

        applicationId = "org.iut.roadeo"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            enableUnitTestCoverage = true
        }
        debug {
            enableUnitTestCoverage = true
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.8.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.android.volley:volley:1.2.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.test:monitor:1.6.1")
}