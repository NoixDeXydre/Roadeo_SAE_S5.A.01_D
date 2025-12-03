plugins {
    id("com.android.application")
}

android {
    namespace = "org.iut.roadeo"
    compileSdk = 34

    defaultConfig {
        applicationId = "org.iut.roadeo"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    // --- JaCoCo configuration for Android Unit Tests ---
    testOptions {
        unitTests.all {
            // Safe configuration of JacocoTaskExtension
            extensions.findByType(JacocoTaskExtension::class.java)?.apply {
                isIncludeNoLocationClasses = true
            }
        }
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
    testImplementation("org.jacoco:org.jacoco.core:0.8.14")
}

// --- Jacoco report task ---
tasks.register<JacocoReport>("jacocoTestReport") {
    dependsOn("testDebugUnitTest")

    reports {
        xml.required.set(true)
        html.required.set(true)
    }

    val fileFilter = listOf(
            "**/R.class", "**/R$*.class",
            "**/BuildConfig.*",
            "**/Manifest*.*",
            "**/*Test*.*"
    )

    val debugTree = fileTree("${buildDir}/tmp/kotlin-classes/debug") {
        exclude(fileFilter)
    }

    val mainSrc = "$projectDir/src/main/java"

    classDirectories.setFrom(debugTree)
    sourceDirectories.setFrom(files(mainSrc))
    executionData.setFrom(file("${buildDir}/jacoco/testDebugUnitTest.exec"))
}

// Optional: configure all test tasks to apply Jacoco settings (extra safety)
tasks.withType<Test>().configureEach {
    extensions.findByType(JacocoTaskExtension::class.java)?.apply {
        isIncludeNoLocationClasses = true
    }
}