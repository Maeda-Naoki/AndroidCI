plugins {
	id("com.android.application")
	id("org.jetbrains.kotlin.android")
	id("org.jetbrains.kotlin.plugin.compose")
}

android {
	namespace = "lucky.retriever.androidci"
	compileSdk = 34

	defaultConfig {
		applicationId = "lucky.retriever.androidci"
		minSdk = 29
		targetSdk = 34
		versionCode = 1
		versionName = "1.0"
		buildToolsVersion = "34.0.0"

		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
	}

	signingConfigs {
		create("release") {
			storeFile = rootProject.file("release.keystore.jks")
			storePassword = System.getenv("KEYSTORE_PASSWORD")
			keyAlias = System.getenv("KEY_ALIAS")
			keyPassword = System.getenv("KEY_PASSWORD")
		}
	}

	buildTypes {
		getByName("release") {
			isMinifyEnabled = false
			proguardFiles(
				getDefaultProguardFile("proguard-android-optimize.txt"),
				"proguard-rules.pro",
			)

			// Apply release signature config
			signingConfig = signingConfigs.getByName("release")
		}
	}

	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_11
		targetCompatibility = JavaVersion.VERSION_11
	}

	kotlinOptions {
		jvmTarget = "11"
	}

	lint {
		// if true, stop the gradle build if errors are found
		abortOnError = false
		// if true, check all issues, including those that are off by default
		checkAllWarnings = true
		// if true, generate a text report of issues (false by default)
		textReport = true
		// Whether we should write a SARIF (OASIS Static Analysis Results Interchange Format) report.
		sarifReport = true
		// The optional path to where a SARIF report (OASIS Static Analysis Results Interchange Format) should be written.
		sarifOutput = file("${layout.buildDirectory.get()}/reports/lint-report.sarif")
	}

	buildFeatures {
		compose = true
		viewBinding = true
	}
}

composeCompiler {
	enableStrongSkippingMode = true
}

dependencies {
	// Implementation
	implementation(libs.core.ktx)
	implementation(libs.activity.compose)

	// Jetpack Compose
	implementation(platform(libs.compose.bom))
	implementation(libs.compose.foundation)
	implementation(libs.compose.foundation.layout)
	implementation(libs.compose.material3)
	implementation(libs.compose.runtime)
	implementation(libs.compose.runtime.livedata)
	implementation(libs.compose.ui)
	implementation(libs.compose.ui.graphics)
	implementation(libs.compose.ui.tooling.preview)
	implementation(libs.compose.ui.viewbinding)

	// Lifecycle
	implementation(libs.lifecycle.viewmodel.ktx)
	implementation(libs.lifecycle.viewmodel.compose)
	implementation(libs.lifecycle.livedata.ktx)
	implementation(libs.lifecycle.runtime.compose)

	// Coroutines
	implementation(libs.kotlinx.coroutines.core)
	implementation(libs.kotlinx.coroutines.android)

	// Navigation
	implementation(libs.navigation.compose)

	// Debug Implementation
	debugImplementation(libs.compose.ui.tooling)

	// Test Implementation
	testImplementation(libs.junit)
	testImplementation(libs.compose.ui.test.junit4)
	testImplementation(libs.compose.ui.test.manifest)

	// Android Test Implementation
	androidTestImplementation(libs.androidx.test.ext.junit)
	androidTestImplementation(libs.espresso.core)
	androidTestImplementation(libs.kotlinx.coroutines.test)
	androidTestImplementation(libs.navigation.testing)
}
