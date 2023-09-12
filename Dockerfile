# Setup Docker image
FROM openjdk:17.0.2-slim-bullseye AS setup

# Docker image build args
## Android SDK setting
### Compile SDK version
ARG ANDROID_COMPILE_SDK="33"

### Android SDK Build-Tools version
ARG ANDROID_BUILD_TOOLS="34.0.0"

### Command-line tools version
#### Ref : https://developer.android.com/studio#downloads
ARG ANDROID_SDK_TOOLS="10406996"

### Android SDK Path
ARG ANDROID_SDK_ROOT="/android-sdk-linux"

# =================================================================================================

# Base Docker image
FROM openjdk:17.0.2-slim-bullseye
