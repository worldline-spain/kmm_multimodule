rootProject.name = "kmm_multimodule"
include(
    ":androidApp", ":shared:shared", ":shared:core", ":shared:data:remote", ":shared:data:local",
    ":shared:data:repository",
    ":shared:views"
)