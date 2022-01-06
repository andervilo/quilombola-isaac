package br.org.quilombola.arquitetura.criteria;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "field",
    "operator",
    "value"
})
public class Filter {

    @JsonProperty("field")
    private String field;
    
    @JsonProperty("operator")
    private OperatorEnum operator;
    
    @JsonProperty("value")
    private Object value;
    
    @JsonProperty("valueTwo")
    private Object valueTwo;

    public Filter(String field, OperatorEnum operator, Object value, Object valueTwo) {
		super();
		this.field = field;
		this.operator = operator;
		this.value = value;
		this.valueTwo = valueTwo;
	}

	public Filter() {
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

    @JsonProperty("operator")
    public OperatorEnum getOperator() {
        return operator;
    }

    @JsonProperty("operator")
    public void setOperator(OperatorEnum operator) {
        this.operator = operator;
    }

    @JsonProperty("value")
    public Object getValue() {
        return value;
    }

    @JsonProperty("value")
    public void setValue(Object value) {
        this.value = value;
    }
    
    @JsonProperty("valueTwo")
    public Object getValueTwo() {
        return valueTwo;
    }

    @JsonProperty("valueTwo")
    public void setValueTwo(Object value) {
        this.valueTwo = value;
    }

}
