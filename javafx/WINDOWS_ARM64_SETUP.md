# JavaFX Setup on Windows ARM64 (aarch64)

> **The core problem:** Official JavaFX binaries for Windows ARM64 do not exist in Maven Central or as downloadable SDKs. The `org.openjfx.javafxplugin` Gradle plugin also has an open bug ([#130](https://github.com/openjfx/javafx-gradle-plugin/issues/130)) where it fails to recognize the `windows-aarch_64` platform identifier.
>
> **The solution:** Use a **64-bit x86 (x64) JDK** instead of an ARM64 JDK. Windows 11 ARM includes a built-in x64 emulation layer, so x64 Java applications run transparently — no performance or compatibility issues for development use.

---

## Table of Contents

1. [Verify Your Windows ARM64 Machine](#1-verify-your-windows-arm64-machine)
2. [Install an x64 JDK](#2-install-an-x64-jdk)
3. [Configure JAVA_HOME](#3-configure-java_home)
4. [Configure IntelliJ IDEA](#4-configure-intellij-idea)
5. [Update build.gradle (no plugin)](#5-update-buildgradle-no-plugin)
6. [Run the Project](#6-run-the-project)
7. [Troubleshooting](#7-troubleshooting)
8. [Alternative: Liberica Full JDK (native ARM64 + JavaFX)](#8-alternative-liberica-full-jdk-native-arm64--javafx)

---

## 1. Verify Your Windows ARM64 Machine

Open **Settings → System → About** and check "System type". It should say something like:
> `64-bit operating system, ARM-based processor`

Or open **PowerShell** and run:

```powershell
(Get-WmiObject Win32_ComputerSystem).SystemType
```

If you see `ARM64-based PC`, you are on Windows ARM64.

---

## 2. Install an x64 JDK

You need a **JDK 17 or later, x64 (NOT aarch64)** build. Windows 11 ARM will run it transparently via its x64 emulation layer.
Currently we are using JDK 25 (as of SP26)

### Option A — Eclipse Temurin (Adoptium) — Recommended

1. Go to: **https://adoptium.net/temurin/releases/**
2. Set the filters:
   - **Version:** 25 (LTS)
   - **OS:** Windows
   - **Architecture:** x64
   - **Package type:** JDK
3. Download the `.msi` installer and run it.
4. During installation, check **"Set JAVA_HOME variable"** and **"Add to PATH"**.

### Option B — Microsoft OpenJDK (x64)

1. Go to: **https://learn.microsoft.com/en-us/java/openjdk/download**
2. Download `microsoft-jdk-21.x.x-windows-x64.msi`
3. Run the installer — it sets `JAVA_HOME` automatically.

> **Why x64 and not ARM64?** JavaFX ships native graphics libraries (`.dll` files) compiled for x64. An ARM64 JDK cannot load x64 native libraries. An x64 JDK can, because Windows runs the whole JVM process under x64 emulation.

---

## 3. Configure JAVA_HOME

Open **PowerShell as Administrator** and verify:

```powershell
java -version
```

Expected output (something like):
```
openjdk version "21.0.x" ...
```

If `java` is not found, set `JAVA_HOME` manually:

1. Press `Win + S` → search **"Environment Variables"** → click **"Edit the system environment variables"**
2. Click **Environment Variables...**
3. Under **System variables**, click **New**:
   - Variable name: `JAVA_HOME`
   - Variable value: `C:\Program Files\Eclipse Adoptium\jdk-21.x.x.x-hotspot` *(adjust to your install path)*
4. Find the `Path` variable → click **Edit** → click **New** → add `%JAVA_HOME%\bin`
5. Click **OK** on all dialogs, then restart PowerShell.

Verify again:
```powershell
java -version
javac -version
echo $env:JAVA_HOME
```

---

## 4. Configure IntelliJ IDEA

IntelliJ may auto-detect the wrong JDK. Force it to use your x64 JDK:

1. Open **File → Project Structure** (`Ctrl+Alt+Shift+S`)
2. Under **Project Settings → Project**, set:
   - **SDK:** select your x64 JDK 21 (if not listed, click `+` → `Add JDK` and browse to `JAVA_HOME`)
   - **Language level:** 17 or 21
3. Click **Apply → OK**

Also check the Gradle JVM:

1. Open **File → Settings** (`Ctrl+Alt+S`)
2. Navigate to **Build, Execution, Deployment → Build Tools → Gradle**
3. Set **Gradle JVM** to your x64 JDK 21
4. Click **Apply → OK**

---

## 5. Update build.gradle (no plugin)

Replace the entire contents of `build.gradle` with the following. This removes the `org.openjfx.javafxplugin` entirely and manually declares JavaFX dependencies with the `win` classifier.

```groovy
plugins {
    id 'java'
    id 'application'
}

group = 'com.daclink'
version = '1.0-SNAPSHOT'

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

application {
    mainClass = 'Main'
}

repositories {
    mavenCentral()
}

def javafxVersion = '22'
def javafxClassifier = 'win'  // x64 Windows binaries — works via emulation on ARM64

dependencies {
    // JavaFX modules — 'win' classifier = x64, runs under Windows ARM64 emulation
    implementation "org.openjfx:javafx-base:${javafxVersion}:${javafxClassifier}"
    implementation "org.openjfx:javafx-graphics:${javafxVersion}:${javafxClassifier}"
    implementation "org.openjfx:javafx-controls:${javafxVersion}:${javafxClassifier}"
    implementation "org.openjfx:javafx-fxml:${javafxVersion}:${javafxClassifier}"

    // JUnit
    testImplementation platform('org.junit:junit-bom:5.10.0')
    testImplementation 'org.junit.jupiter:junit-jupiter'
    testRuntimeOnly 'org.junit.platform:junit-platform-launcher'
}

// Pass JavaFX module-path and add-modules to the run task
run {
    doFirst {
        jvmArgs = [
            '--module-path', configurations.runtimeClasspath.asPath,
            '--add-modules', 'javafx.controls,javafx.fxml'
        ]
    }
}

test {
    useJUnitPlatform()
}
```

### What changed and why

| Removed | Reason |
|---|---|
| `id 'org.openjfx.javafxplugin'` | Fails on Windows ARM64 with "Unsupported platform: windows-aarch_64" |
| `javafx { version = '22' ... }` | Plugin-specific block, no longer needed |

| Added | Reason |
|---|---|
| Manual `implementation` deps with `:win` classifier | Directly pulls x64 JavaFX jars from Maven Central |
| `run { jvmArgs ... }` block | Replaces what the plugin did — sets `--module-path` and `--add-modules` |

---

## 6. Run the Project

In IntelliJ, open the **Gradle** tool window (right side panel) and click:
```
Tasks → application → run
```

Or from PowerShell in the project directory:

```powershell
.\gradlew run
```

You should see your JavaFX window open. If it works, you are done.

---

## 7. Troubleshooting

### "Cannot find symbol" or compilation errors after changing build.gradle

Reload Gradle:
- IntelliJ: Click the **elephant icon** (Sync Gradle) in the top-right of `build.gradle`
- Or run: `.\gradlew --refresh-dependencies`

---

### "Error: JavaFX runtime components are missing"

The `--module-path` and `--add-modules` args are not being passed. Make sure the `run { doFirst { jvmArgs ... } }` block is in your `build.gradle`. If running directly from IntelliJ's green play button (not Gradle), you must also add VM options to the run configuration:

1. Click the run config dropdown → **Edit Configurations**
2. Under **VM options**, add:
   ```
   --module-path "C:\path\to\javafx-sdk\lib" --add-modules javafx.controls,javafx.fxml
   ```

---

### "UnsatisfiedLinkError" or native library crash

This means a native `.dll` is x64 but your JDK is ARM64. Confirm your JDK is x64:

```powershell
java -XshowSettings:all -version 2>&1 | Select-String "os.arch"
```

Expected: `os.arch = amd64`
If you see `os.arch = aarch64`, you installed the wrong JDK — go back to [Step 2](#2-install-an-x64-jdk).

---

### Gradle wrapper fails to download

Check your internet connection, then run:

```powershell
.\gradlew wrapper --gradle-version 9.2.0
```

---

## 8. Alternative: Liberica Full JDK (native ARM64 + JavaFX)

BellSoft's **Liberica JDK Full** bundles OpenJFX directly inside the JDK, including a native Windows ARM64 build. This is the only way to run JavaFX natively (not emulated) on Windows ARM64 today.

### Download

1. Go to: **https://bell-sw.com/pages/downloads/**
2. Set filters:
   - **Version:** 21 LTS (or 17 LTS)
   - **OS:** Windows
   - **Architecture:** ARM 64
   - **Package:** Full JDK (includes JavaFX)
3. Download the `.msi` and install.

### build.gradle for Liberica Full

When using Liberica Full JDK, JavaFX is part of the JDK itself — no Maven dependencies needed. Use a minimal `build.gradle`:

```groovy
plugins {
    id 'java'
    id 'application'
}

group = 'com.daclink'
version = '1.0-SNAPSHOT'

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

application {
    mainClass = 'Main'
}

repositories {
    mavenCentral()
}

// No JavaFX dependencies — they are bundled in Liberica Full JDK

// Still need --add-modules since JavaFX is a named module
run {
    jvmArgs = [
        '--add-modules', 'javafx.controls,javafx.fxml'
    ]
}

dependencies {
    testImplementation platform('org.junit:junit-bom:5.10.0')
    testImplementation 'org.junit.jupiter:junit-jupiter'
    testRuntimeOnly 'org.junit.platform:junit-platform-launcher'
}

test {
    useJUnitPlatform()
}
```

> **Trade-off:** Liberica is less commonly used than Temurin/Microsoft JDK. The x64 emulation path (Steps 1–6) is simpler and more consistent with the rest of the class.

---

## Quick Reference

| | x64 JDK + win classifier | Liberica Full ARM64 |
|---|---|---|
| Native performance | No (x64 emulation) | Yes |
| Setup complexity | Low | Medium |
| Matches class setup | Yes | Partial |
| JavaFX source | Maven Central | Bundled in JDK |
| Recommended for | Most students | Performance-sensitive work |

---

## Useful Links

- [Eclipse Temurin Downloads](https://adoptium.net/temurin/releases/)
- [Microsoft OpenJDK Downloads](https://learn.microsoft.com/en-us/java/openjdk/download)
- [BellSoft Liberica Full JDK](https://bell-sw.com/pages/downloads/)
- [OpenJFX Maven Central](https://central.sonatype.com/artifact/org.openjfx/javafx-controls)
- [JavaFX 22 Release Notes](https://jdk.java.net/javafx22/)
- [Gradle 9.2 Release Notes (ARM64 support)](https://docs.gradle.org/9.2.0/release-notes.html)
- [OpenJFX Plugin Bug #130 (Windows ARM64)](https://github.com/openjfx/javafx-gradle-plugin/issues/130)
- [OpenJFX Windows AArch64 Build Ticket (JDK-8314064)](https://bugs.openjdk.org/browse/JDK-8314064)