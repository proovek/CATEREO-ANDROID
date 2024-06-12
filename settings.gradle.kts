pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        // Dodaj inne repozytoria, jeśli są potrzebne
    }
    resolutionStrategy {
        eachPlugin {
            if (requested.id.id == "realm-android") {
                useModule("io.realm:realm-gradle-plugin:${requested.version}")
            }
        }
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        jcenter()  // Realm może wymagać jcenter, ale jest on stopniowo wycofywany
    }
}


rootProject.name = "CATEREO"
include(":app")
 