rootProject.name = "kmm_multimodule"
include(
    ":androidApp", ":shared:core", ":shared:data:remote", ":shared:data:local",
    ":shared:feature:poi",
    ":shared:ui:logic:poilistvm"
)