package br.org.quilombola.arquitetura.criteria;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "sorting", "filters" })
public class BodyFIlterAndSort {

	@JsonProperty("page")
	private int page;

	@JsonProperty("size")
	private int size;

	@JsonProperty("sorting")
	private Sorting sorting;

	@JsonProperty("filters")
	private List<Filter> filters = new ArrayList<Filter>();

	@JsonProperty("page")
	public int getPage() {
		return page;
	}

	@JsonProperty("page")
	public void setPage(int page) {
		this.page = page;
	}

	@JsonProperty("size")
	public int getSize() {
		return size;
	}

	@JsonProperty("size")
	public void setSize(int size) {
		this.size = size;
	}

	@JsonProperty("sorting")
	public Sorting getSorting() {
		return sorting;
	}

	@JsonProperty("sorting")
	public void setSorting(Sorting sorting) {
		this.sorting = sorting;
	}

	@JsonProperty("filters")
	public List<Filter> getFilters() {
		return filters;
	}

	@JsonProperty("filters")
	public void setFilters(List<Filter> filters) {
		this.filters = filters;
	}
}
