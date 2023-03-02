package Less4;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Request {

    @JsonProperty("item")
    private String item = "1 package baking powder";
    @JsonProperty("aisle")
    private String aisle = "Baking";
    @JsonProperty("parse")
    private Boolean parse = true;

}
