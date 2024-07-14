# AndroidCI
![GitLab CI](https://gitlab.com/naoki_maeda/AndroidCI/badges/main/pipeline.svg)
[![MIT License](http://img.shields.io/badge/license-MIT-blue.svg?style=flat)](https://github.com/Maeda-Naoki/AndroidCI/blob/main/LICENSE)

## Overview
This project is intended to be the base of the Android project.

## Development Environment
- GitHub Action
  - [ ] Build
    - [ ] Debug Build
    - [ ] Release Build
  - [ ] Lint
    - [ ] [Ktlint Gradle](https://github.com/JLLeitschuh/ktlint-gradle)
    - [ ] [Android Lint](https://developer.android.com/studio/write/lint)
    - [ ] [Reviewdog](https://github.com/reviewdog/reviewdog)
  - [ ] Test
    - [ ] UnitTest
    - [ ] Instrumented Unit Tests
  - [ ] Dependencies Update Check
    - [ ] [Renovate](https://github.com/renovatebot/renovate#renovate)
- GitLab CI
  - [X] Build
    - [X] Debug Build
    - [X] Release Build
  - [X] Lint
    - [X] [Ktlint Gradle](https://github.com/JLLeitschuh/ktlint-gradle)
    - [X] [Android Lint](https://developer.android.com/studio/write/lint)
    - [X] [Reviewdog](https://github.com/reviewdog/reviewdog)
  - [X] Test
    - [X] UnitTest
    - [ ] Instrumented Unit Tests
  - [X] Dependencies Update Check
    - [X] [Renovate](https://github.com/renovatebot/renovate#renovate)

## Build Variants
The following four Build Types are set.  
We assume that you can switch between them depending on the optimization level, signature, and server settings.

- [Debug](#debug-build)
- [Local](#local-build)
- [Staging](#staging-build)
- [Release](#release-build)

### Debug Build
This Build Type is primarily intended for use during development.  

- Minify : Disable
- Signature : Debug Key

### Local Build
This is basically the same as Debug Build, but it is a Build Type that assumes a connection to a local server.  
It is mainly intended for use in Unit Test.  

- Minify : Disable
- Signature : Debug Key

### Staging Build
This build type is basically the same as the Release build, but is intended for testing in the Staging environment. 

- Minify : Enable
- Signature : Release Key

### Release Build
This build type is primarily intended to create a release version APK. 

- Minify : Enable
- Signature : Release Key


## Repository Setting
#### Auto-cancel redundant pipelines
![Static Badge](https://img.shields.io/badge/Required-F44336)
![GitLab](https://img.shields.io/badge/gitlab-%23181717.svg?style=for-the-badge&logo=gitlab&logoColor=orange)  
This GitLab CI uses interruptible flag to enable auto-cancel pipelines.  
Therefore, please enable GitLab's [auto-cancel redundant pipelines](https://docs.gitlab.com/ee/ci/pipelines/settings.html#auto-cancel-redundant-pipelines).

## Environment Variables
### Release Build variables
You must set the below environment variables to do a **Release build** .  
#### KEYSTORE_FILE_BASE64
![Static Badge](https://img.shields.io/badge/Required-F44336)
![GitHub](https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white)
![GitLab](https://img.shields.io/badge/gitlab-%23181717.svg?style=for-the-badge&logo=gitlab&logoColor=orange)  
This is KeyStore.jks encoded in Base64.  
Please encode keystore.jks in Base64 by referring to the following command.  
You should create KeyStore.jks from Android Studio or similar.  

```bash
$ openssl base64 -A -in KeyStore.jks > KeyStore.jks.txt
```

#### KEYSTORE_PASSWORD
![Static Badge](https://img.shields.io/badge/Required-F44336)
![GitHub](https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white)
![GitLab](https://img.shields.io/badge/gitlab-%23181717.svg?style=for-the-badge&logo=gitlab&logoColor=orange)  
Set the `Key Store Password` that you entered when creating the KeyStore.  
**Never commit this value.**
#### KEY_ALIAS
![Static Badge](https://img.shields.io/badge/Required-F44336)
![GitHub](https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white)
![GitLab](https://img.shields.io/badge/gitlab-%23181717.svg?style=for-the-badge&logo=gitlab&logoColor=orange)  
Set the `Key Alias` that you entered when creating the KeyStore.
#### KEY_PASSWORD
![Static Badge](https://img.shields.io/badge/Required-F44336)
![GitHub](https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white)
![GitLab](https://img.shields.io/badge/gitlab-%23181717.svg?style=for-the-badge&logo=gitlab&logoColor=orange)  
Set the `Key Password` that you entered when creating the KeyStore.  
**Never commit this value.**

### Renovate
#### RENOVATE_TOKEN
![Static Badge](https://img.shields.io/badge/Required-F44336)
![GitHub](https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white)
![GitLab](https://img.shields.io/badge/gitlab-%23181717.svg?style=for-the-badge&logo=gitlab&logoColor=orange)  
API token for Renovate.  
The token is required for automatic Updates of Dependencies by Renovate.  
Please refer to the following site for the settings required to create the token.  

- [GitHub](https://docs.renovatebot.com/modules/platform/github/)
- [GitLab](https://docs.renovatebot.com/modules/platform/gitlab/)

### Build variables
The following values are set to default values, but you can override them from the repository settings.  
#### ANDROID_COMPILE_SDK
![Static Badge](https://img.shields.io/badge/Optional-009688)
![GitHub](https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white)
![GitLab](https://img.shields.io/badge/gitlab-%23181717.svg?style=for-the-badge&logo=gitlab&logoColor=orange)  
This is the compile target SDK version.  
Please refer to `SDK Platforms` in SDK manager to set this value.  
Ref : [API Level](https://developer.android.com/guide/topics/manifest/uses-sdk-element#ApiLevels)
#### ANDROID_BUILD_TOOLS
![Static Badge](https://img.shields.io/badge/Optional-009688)
![GitHub](https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white)
![GitLab](https://img.shields.io/badge/gitlab-%23181717.svg?style=for-the-badge&logo=gitlab&logoColor=orange)  
This is the version of the Android SDK Build-tools.  
Please refer to `Android SDK Build-tools` in SDK manager to set this value.  
Ref : [SDK Build Tools](https://developer.android.com/studio/releases/build-tools)
#### ANDROID_SDK_TOOLS
![Static Badge](https://img.shields.io/badge/Optional-009688)
![GitHub](https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white)
![GitLab](https://img.shields.io/badge/gitlab-%23181717.svg?style=for-the-badge&logo=gitlab&logoColor=orange)  
This is the version of the Android command-line tools.  
Please refer to the ★ portion of the following values for version values.  
`commandlinetools-linux-{★}_latest.zip`  
Ref : [Command-line tools](https://developer.android.com/studio#downloads)

### Merge Request variables
You must set the below environment variables to do a **Merge Request** .  
#### REVIEWDOG_GITLAB_API_TOKEN
![Static Badge](https://img.shields.io/badge/Required-F44336)
![GitLab](https://img.shields.io/badge/gitlab-%23181717.svg?style=for-the-badge&logo=gitlab&logoColor=orange)  
An access token for the account which will post MR comments.  
Please create an account to post MR comments in advance.  
Get the token with `api` scope from https://gitlab.com/-/user_settings/personal_access_tokens.