plugins {
	id("com.android.application")
	id("com.google.dagger.hilt.android")
	id("com.google.devtools.ksp")
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
		buildToolsVersion = "35.0.0"

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
		// Debug Build
		getByName("debug") {
			isDefault = true
			isMinifyEnabled = false
			isDebuggable = true
			signingConfig = signingConfigs.getByName("debug")
		}

		// Debug Build (for Local Server Env)
		create("local") {
			isMinifyEnabled = false
			isDebuggable = true
			signingConfig = signingConfigs.getByName("debug")
		}

		// Staging Build
		create("staging") {
			initWith(getByName("release"))
		}

		getByName("release") {
			isMinifyEnabled = true
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

	testOptions {
		unitTests.all {
			it.useJUnitPlatform()
		}
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

	// Material Icons
	implementation(libs.compose.material.icons)

	// Lifecycle
	implementation(libs.lifecycle.viewmodel.ktx)
	implementation(libs.lifecycle.viewmodel.compose)
	implementation(libs.lifecycle.livedata.ktx)
	implementation(libs.lifecycle.runtime.compose)

	// Coroutines
	implementation(libs.kotlinx.coroutines.core)
	implementation(libs.kotlinx.coroutines.android)

	// Hilt
	implementation(libs.dagger.hilt.android)
	implementation(libs.hilt.navigation.compose)
	ksp(libs.dagger.hilt.android.compiler)
	ksp(libs.dagger.hilt.compiler)
	ksp(libs.hilt.compiler)

	// Navigation
	implementation(libs.navigation.compose)

	// Arrow
	implementation(platform(libs.arrow.bom))
	implementation(libs.arrow.core)
	implementation(libs.arrow.fx.coroutines)

	// Debug Implementation
	debugImplementation(libs.compose.ui.tooling)

	// Test Implementation
	testImplementation(libs.compose.ui.test.junit4)
	testImplementation(libs.compose.ui.test.manifest)
	testImplementation(libs.kotest.assertions.core)
	testImplementation(libs.kotest.runner.junit5)
	testImplementation(libs.kotest.property)
	testImplementation(libs.dagger.hilt.android.testing)
	kspTest(libs.dagger.hilt.android.compiler)
	kspTest(libs.dagger.hilt.compiler)
	kspTest(libs.hilt.compiler)

	// Android Test Implementation
	androidTestImplementation(libs.androidx.test.ext.junit)
	androidTestImplementation(libs.espresso.core)
	androidTestImplementation(libs.dagger.hilt.android.testing)
	kspAndroidTest(libs.dagger.hilt.android.compiler)
	kspAndroidTest(libs.dagger.hilt.compiler)
	kspAndroidTest(libs.hilt.compiler)
	androidTestImplementation(libs.kotlinx.coroutines.test)
	androidTestImplementation(libs.navigation.testing)
}
