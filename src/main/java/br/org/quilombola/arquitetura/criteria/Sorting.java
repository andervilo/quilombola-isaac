package br.org.quilombola.arquitetura.criteria;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "field",
    "direction"
})
public class Sorting {

    @JsonProperty("field")
    private String field;
    
    @JsonProperty("direction")
    private DirectionEnum direction;
     
    public Sorting(String field, DirectionEnum direction) {
		super();
		this.field = field;
		this.direction = direction;
	}

	public Sorting() {
		super();
		// TODO Auto-generated constructor stub
	}

	@JsonProperty("field")
    public String getField() {
        return field;
    }

    @JsonProperty("field")
    public void setField(String field) {
        this.field = field;
    }

    @JsonProperty("direction")
    public DirectionEnum getDirection() {
        return direction;
    }

    @JsonProperty("direction")
    public void setSortDirection(DirectionEnum direction) {
        this.direction = direction;
    }
}
