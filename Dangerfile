# Android
# Skip gradle task execution
android_lint.skip_gradle_task = true

# Lint only added / modified files
android_lint.filtering = true

Dir
  .glob("**/build/reports/lint-report.xml")
  .each { |file|
  	android_lint.report_file = file
  	android_lint.lint(inline_mode: true)
  }

# Make danger comment directly on the line instead of printing a markdown table (GitHub ONLY)
android_lint.lint(inline_mode: true)

# ktlint
checkstyle_format.base_path = Dir.pwd
Dir
  .glob('**/build/reports/ktlint/**/*.xml')
  .each { |report| checkstyle_format.report report.to_s }
