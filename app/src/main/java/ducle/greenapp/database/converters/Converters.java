package ducle.greenapp.database.converters;

import androidx.room.TypeConverter;

import com.google.android.gms.maps.model.LatLng;

import ducle.greenapp.database.models.utils.ModelUtils;

public class Converters {
    @TypeConverter
    public static LatLng StringToLatLng(String location) {
        String[] locationSplit = ModelUtils.splitTrimLine(location, ",");
        return new LatLng(Double.parseDouble(locationSplit[0]), Double.parseDouble(locationSplit[1]));
    }

    @TypeConverter
    public static String LatLngToString(LatLng latLng) {
        return latLng.latitude + ", " + latLng.longitude;
    }
}
