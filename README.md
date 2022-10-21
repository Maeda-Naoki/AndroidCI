# AndroidCI

# GitLab CI
## Environment Variables
### Required
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
