pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "AppetiserMovies"
include(":app")
include(":data:network")
include(":feature:movie_list")
include(":core:ui")
include(":data:database")
include(":feature:movie_detail")
include(":shared:movie")
include(":core:util")
