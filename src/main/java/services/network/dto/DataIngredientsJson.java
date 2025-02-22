package services.network.dto;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataIngredientsJson {
    @SerializedName("_id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("type")
    private String type;

    @SerializedName("proteins")
    private int proteins;

    @SerializedName("carbohydrates")
    private int carbohydrates;

    @SerializedName("calories")
    private int calories;

    @SerializedName("price")
    private int price;

    @SerializedName("image")
    private String image;

    @SerializedName("image_mobile")
    private String imageMobile;

    @SerializedName("image_large")
    private String imageLarge;

    @SerializedName("__v")
    private int version;
}
