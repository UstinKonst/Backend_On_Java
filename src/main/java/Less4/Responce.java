package Less4;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "aisles",
        "cost",
        "startDate",
        "endDate"
})
@Data
public class Responce {

    @JsonProperty("aisles")
    private List<Object> aisles;
    @JsonProperty("cost")
    private double cost;
    @JsonProperty("startDate")
    private int startDate;
    @JsonProperty("endDate")
    private int endDate;
    @JsonProperty("status")
    private String status;
}
