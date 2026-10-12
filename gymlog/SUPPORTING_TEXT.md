
Values copy and pasted
https://developer.android.com/trainin...

def room_version = "2.4.3"

    implementation "androidx.room:room-runtime:$room_version"
    annotationProcessor "androidx.room:room-compiler:$room_version"

Other Gradle values

javaCompileOptions {
            annotationProcessorOptions {
                arguments = ["room.schemaLocation": "$projectDir/schemas".toString()]
            }
        }

    buildFeatures {
        viewBinding true
    }