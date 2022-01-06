package br.org.quilombola.model.projection;

import br.org.quilombola.arquitetura.enums.Estado;

public interface MunicipioView {

	public String getNome();

	public Integer getCodigoIbge();

	public Estado getEstado();

	public Boolean getAmazoniaLegal();
}
