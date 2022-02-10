plugins {
}

    android {
    compileSdk = 32

    defaultConfig {
      applicationId = "com.example.feature_edit"
    minSdk = 23
    targetSdk = 32
    versionCode = 1
    versionName = "1.0"

      testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
       release {
           isMinifyEnabled = false
           proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
       }
    }
    }

  dependencies {

  }