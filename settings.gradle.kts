rootProject.name = "kmm_multimodule"
include(
    ":androidApp", ":shared:core", ":shared:data:remote", ":shared:data:local",
    ":shared:feature:poi:poirepository",
    ":shared:feature:poi:poiui"
)