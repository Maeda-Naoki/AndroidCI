# Setup Docker image
FROM amazoncorretto:21.0.6-alpine3.20 AS setup

# Docker image build args
## Android SDK setting
### Compile SDK version
ARG ANDROID_COMPILE_SDK="35"

### Android SDK Build-Tools version
ARG ANDROID_BUILD_TOOLS="35.0.0"

### Command-line tools version
#### Ref : https://developer.android.com/studio#downloads
ARG ANDROID_SDK_TOOLS="11076708"

### Android SDK Path
ARG ANDROID_SDK_ROOT="/android-sdk-linux"

# Install dependencies
RUN apk update && apk --no-cache add \
    wget=1.24.5-r0  \
    tar=1.35-r2     \
    unzip=6.0-r14

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
FROM amazoncorretto:21.0.6-alpine3.20

# Metadata of Docker image
LABEL maintainer="maeda.naoki.md9@gmail.com"
LABEL version="1.2.0"

# Docker image build args
## Build user setting
ARG GID=10000
ARG UID=10000
ARG GroupName="DevelopGroup"
ARG UserName="developer"
ARG UserHomeDir="/home/developer"
ARG srcGradleDir="/gradle"
ARG distGradleDir="/opt/gradle"

## Environment Variables
### Android
ENV ANDROID_HOME="/android-sdk-linux"
ENV ANDROID_SDK_ROOT="/android-sdk-linux"

### General
ENV PATH="$PATH:${ANDROID_HOME}/cmdline-tools/bin:${ANDROID_HOME}/platform-tools"

# Add build user (Non-root user)
RUN addgroup -g ${GID} ${GroupName} && \
    adduser --disabled-password \
    -u ${UID} -G ${GroupName} -h ${UserHomeDir} ${UserName}

# Install dependencies
RUN apk update && apk --no-cache add \
    gcompat=1.1.0-r4    \
    libgcc=13.2.1_git20240309-r1

# Copy Android SDK directory
COPY --from=setup --chown=${UID}:${GID} ${ANDROID_HOME} ${ANDROID_HOME}

# Setup working user
USER ${UID}
WORKDIR ${UserHomeDir}
