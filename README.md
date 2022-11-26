# AndroidCI

## Environment Variables
### Common
### Required
##### Release Build variables
You must set the below environment variables to do a **Release build** .  
#### KEYSTORE_FILE_BASE64
This is KeyStore.jks encoded in Base64.  
Please encode keystore.jks in Base64 by referring to the following command.  
You should create KeyStore.jks from Android Studio or similar.  

```bash
$ openssl base64 -A -in KeyStore.jks > KeyStore.jks.txt
```

#### KEYSTORE_PASSWORD
Set the `Key Store Password` that you entered when creating the KeyStore.  
**Never commit this value.**
#### KEY_ALIAS
Set the `Key Alias` that you entered when creating the KeyStore.
#### KEY_PASSWORD
Set the `Key Password` that you entered when creating the KeyStore.
**Never commit this value.**

### Overwritable
##### Build variables
The following values are set to default values, but you can override them from the repository settings.  
#### ANDROID_COMPILE_SDK
This is the compile target SDK version.  
Please refer to `SDK Platforms` in SDK manager to set this value.  
Ref : [API Level](https://developer.android.com/guide/topics/manifest/uses-sdk-element#ApiLevels)
#### ANDROID_BUILD_TOOLS
This is the version of the Android SDK Build-tools.  
Please refer to `Android SDK Build-tools` in SDK manager to set this value.  
Ref : [SDK Build Tools](https://developer.android.com/studio/releases/build-tools)
#### ANDROID_SDK_TOOLS
This is the version of the Android command-line tools.  
Please refer to the ★ portion of the following values for version values.  
`commandlinetools-linux-{★}_latest.zip`  
Ref : [Command-line tools](https://developer.android.com/studio#downloads)

### for GitLab CI
#### Required
##### Merge Request variables
###### DANGER_GITLAB_API_TOKEN
An access token for the account which will post comments.  
Please create an account to post comments in advance.