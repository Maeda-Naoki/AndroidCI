# Android
# Skip gradle task execution
android_lint.skip_gradle_task = true

# Changing report's file path
android_lint.report_file = "app/build/reports/lint-report.xml"

# Lint only added / modified files
android_lint.filtering = true

# Make danger comment directly on the line instead of printing a markdown table (GitHub ONLY)
android_lint.lint(inline_mode: true)

# ktlint
checkstyle_format.base_path = Dir.pwd
Dir
  .glob('app/build/reports/ktlint/**/*.xml')
  .each { |report| checkstyle_format.report report.to_s }
