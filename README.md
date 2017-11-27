# MapTest
This is an active project integrating the [Grover-c13/PokeGOAPI-Java API](https://github.com/Grover-c13/PokeGOAPI-Java/) and the [ArcGIS Runtime SDK for Java](https://developers.arcgis.com/java/). 

The project currently displays Red Dots for each of the catchable pokemon near a given Lat/Lng. See the TO-DO list for upcoming features.

# Running Locally

1. Install the latest version of [Eclipse](https://www.eclipse.org/ide/).
2. Install the latest version of [Gradle](https://developers.arcgis.com/java/latest/guide/install-the-sdk.htm).
3. Create a new Gradle project and modify the gradle.build file to include the ArcGIS Runtime SDK:

```
apply plugin: 'application'
apply plugin: 'com.esri.arcgisruntime.java'
buildscript {
    repositories {
        maven {
            url 'https://esri.bintray.com/arcgis'
        }
    }
    dependencies {
        classpath 'com.esri.arcgisruntime:gradle-arcgis-java-plugin:1.0.0'
    }
}
arcgis.version = '100.1.0'
```
4. Clone the master branch of the PokeGOAPI-Java project and build a jar. Move the new jars into the `/libs` directory of this project.
5. Compile your new jars in the gradle.build file: 

`dependencies { compile fileTree(include: ['PokeGOAPI-library-all-*.jar'], dir: 'libs/') }`

6. Fill in the blank variables with your PokemonGo username and password, your desired LAT and LNG, and your POKEHASH_KEY (required for login). Hash Keys can be purchased [here](https://talk.pogodev.org/d/51-api-hashing-service-by-pokefarmer). 

# TO-DO

1. Allow users to login via JavaFX.
2. Allow users to select any location on the map.
3. Create the option to click a graphic and learn more about the pokemon at that location.
4. Display nearby gyms on map.
5. Display nearby pokestops on map.
