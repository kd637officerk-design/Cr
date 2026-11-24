# Prime One — Android / GitHub build

This is an Android WebView wrapper around the supplied Prime One HTML app. The supplied artwork is used as the launcher icon.

## Build the APK on GitHub (no Android build tools needed on your phone)
1. Create a new GitHub repository.
2. Upload the contents of this ZIP (not the ZIP file itself) to the repository, or extract and push the folder.
3. Open **Actions**. The workflow **Build Android APK** runs automatically after a push; you can also run it with **Run workflow**.
4. Open the completed workflow run, scroll to **Artifacts**, and download **Prime-One-Android-APK**.
5. Extract the artifact ZIP to get `app-debug.apk`, then install it on your Android phone. Android may ask you to allow installing apps from that source.

## Local build
Requires JDK 17, Android SDK platform 35/build tools 35.0.0, and Gradle 8.9. Run:

```bash
gradle assembleDebug
```

APK output: `app/build/outputs/apk/debug/app-debug.apk`.

## Notes
- The app opens the bundled HTML offline. Features that depend on internet or external services (for example, a metadata script loaded from a CDN) still need connectivity.
- Android file chooser is enabled for the app's import/media pickers.
- This repository is a debug APK build pipeline. It is not Play Store-signed; a release build and signing key would be needed for store distribution.
