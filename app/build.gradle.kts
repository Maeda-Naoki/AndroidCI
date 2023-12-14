plugins {
	id("com.android.application")
	id("org.jetbrains.kotlin.android")
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
				"proguard-rules.pro"
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
		// if true, generate an XML report for use by for example Jenkins
		xmlReport = true
		// file to write report to (if not specified, defaults to lint-results.xml)
		xmlOutput = file("${layout.buildDirectory.get()}/reports/lint-report.xml")
	}

	buildFeatures {
		viewBinding = true
	}
}

dependencies {
	implementation("androidx.core:core-ktx:1.12.0")
	implementation("androidx.appcompat:appcompat:1.6.1")
	implementation("com.google.android.material:material:1.11.0")
	implementation("androidx.constraintlayout:constraintlayout:2.1.4")
	implementation("androidx.navigation:navigation-fragment-ktx:2.7.6")
	implementation("androidx.navigation:navigation-ui-ktx:2.7.6")
	testImplementation("junit:junit:4.13.2")
	androidTestImplementation("androidx.test.ext:junit:1.1.5")
	androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}
