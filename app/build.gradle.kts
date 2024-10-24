import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.example.na_regua_app"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.na_regua_app"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"


        val localProperties = project.rootProject.file("local.properties")
        val properties = Properties().apply {
            load(FileInputStream(localProperties))
        }

        buildConfigField(type="String",name="API_BASE_URL", properties.getProperty("API_BASE_URL"))

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
        buildConfig = true
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
    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")

    // Retrofit2
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.1")
    implementation("com.squareup.retrofit2:converter-scalars:2.9.0")


    // Coil
    implementation("io.coil-kt:coil-compose:2.6.0")

    // Koin
    implementation("io.insert-koin:koin-android:4.0.0")
    implementation("io.insert-koin:koin-compose-viewmodel:4.0.0")

    // DataStore
    implementation("androidx.datastore:datastore-preferences:1.1.1")

    // AndroidX
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation("androidx.compose.material3:material3:1.1.0")
    implementation(libs.material)
    val navVersion = "2.5.3"
    implementation("androidx.navigation:navigation-compose:$navVersion")

    // Testes
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    val latest_release = "0.2.2-alpha"

    // Gráficos
    implementation("com.github.tehras:charts:$latest_release")

    // LogInterceptor
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.10")

}
