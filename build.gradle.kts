import org.jlleitschuh.gradle.ktlint.KtlintExtension
import org.jlleitschuh.gradle.ktlint.reporter.ReporterType

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
	alias(libs.plugins.androidApplication) apply false
	alias(libs.plugins.androidLibrary) apply false
	alias(libs.plugins.kotlinAndroid) apply false
	alias(libs.plugins.composeCompiler) apply false
	alias(libs.plugins.ktlint)
}

subprojects {
	apply(plugin = "org.jlleitschuh.gradle.ktlint")
	configure<KtlintExtension> {
		// Ref : https://github.com/JLLeitschuh/ktlint-gradle/blob/master/plugin/src/main/kotlin/org/jlleitschuh/gradle/ktlint/KtlintExtension.kt
		debug.set(true) // Enable debug mode.
		verbose.set(true) // Enable verbose mode.
		android.set(true) // Enable android mode.
		outputToConsole.set(true) // Enable console output mode.
		coloredOutput.set(true) // Enabled colored output to console.
		outputColorName.set("RED") // Specify the color of the terminal output.
		ignoreFailures.set(true) // Keep the build going even with warnings.
		reporters {
			reporter(ReporterType.PLAIN) // Enable console reporter
			reporter(ReporterType.CHECKSTYLE) // Enable checkstyle reporter
		}
	}
}
