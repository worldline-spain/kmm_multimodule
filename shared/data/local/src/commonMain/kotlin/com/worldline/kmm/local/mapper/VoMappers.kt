import com.worldline.kmm.core.Poi
import com.worldline.kmm.db.PoiVo

internal fun PoiVo.toModel() = Poi(
    id = id,
    title = title,
    latitude = latitude,
    longitude = longitude
)

internal fun Poi.toVo() = PoiVo(
    id = id,
    title = title,
    latitude = latitude,
    longitude = longitude
)