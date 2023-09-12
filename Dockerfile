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

# Install dependencies
RUN apt update && apt install -y --no-install-recommends \
    wget \
    tar \
    unzip

# Download Android SDK
RUN wget --quiet --output-document=android-sdk.zip "https://dl.google.com/android/repository/commandlinetools-linux-${ANDROID_SDK_TOOLS}_latest.zip" && \
    unzip -d ${ANDROID_SDK_ROOT} android-sdk.zip

# Install Android SDK components
RUN echo y | android-sdk-linux/cmdline-tools/bin/sdkmanager --sdk_root=${ANDROID_SDK_ROOT} \
    "platforms;android-${ANDROID_COMPILE_SDK}" \
    "platform-tools" \
    "build-tools;${ANDROID_BUILD_TOOLS}" \
    > /dev/null

# Agree Android SDK License
RUN yes | android-sdk-linux/cmdline-tools/bin/sdkmanager --sdk_root=${ANDROID_SDK_ROOT} --licenses > /dev/null

# =================================================================================================

# Base Docker image
FROM openjdk:17.0.2-slim-bullseye
