package br.org.quilombola.arquitetura.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Estado {

	RO(11,"Rondônia", "RO", Regiao.NORTE),
	AC(12,"Acre", "AC", Regiao.NORTE),
	AM(13,"Amazonas", "AM", Regiao.NORTE),
	RR(14,"Roraima", "RR", Regiao.NORTE),
	PA(15,"Pará", "PA", Regiao.NORTE),
	AP(16,"Amapá", "AP", Regiao.NORTE),
	TO(17,"Tocantins", "TO", Regiao.NORTE),
	MA(21,"Maranhão", "MA", Regiao.NORDESTE),
	PI(22,"Piauí", "PI", Regiao.NORDESTE),
	CE(23,"Ceará", "CE", Regiao.NORDESTE),
	RN(24,"Rio Grande do Norte", "RN", Regiao.NORDESTE),
	PB(25,"Paraíba", "PB", Regiao.NORDESTE),
	PE(26,"Pernambuco", "PE", Regiao.NORDESTE),
	AL(27,"Alagoas", "AL", Regiao.NORDESTE),
	SE(28,"Sergipe", "SE", Regiao.NORDESTE),
	BA(29,"Bahia", "BA", Regiao.NORDESTE),
	MG(31,"Minas Gerais", "MG", Regiao.SUDESTE),
	ES(32,"Espírito Santo", "ES", Regiao.SUDESTE),
	RJ(33,"Rio de Janeiro", "RJ", Regiao.SUDESTE),
	SP(35,"São Paulo",  "SP", Regiao.SUDESTE),
	PR(41,"Paraná",  "PR", Regiao.SUL),
	SC(42,"Santa Catarina",  "SC", Regiao.SUL),
	RS(43,"Rio Grande do Sul",  "RS", Regiao.SUL),
	MS(50,"Mato Grosso do Sul",  "MS", Regiao.CENTROOESTE),
	MT(51,"Mato Grosso",  "MT", Regiao.CENTROOESTE),
	GO(52,"Goiás",  "GO", Regiao.CENTROOESTE),
	DF(53,"Distrito Federal",  "DF", Regiao.CENTROOESTE);

	private final int codigoIbge;
	private final String nome;
	private final String sigla;
	private final Regiao regiao;

	private Estado(int codigoIbge,String nome, String sigla, Regiao regiao) {
		this.codigoIbge = codigoIbge;
		this.nome = nome;
		this.sigla = sigla;
		this.regiao = regiao;
	}
	
	public static Estado getEstadoByCodigoIbge(final Integer codUf) {
		List<Estado> listBusca = Arrays.asList(Estado.values());
		List<Estado> list = listBusca.stream().filter(e->e.codigoIbge == codUf).collect(Collectors.toList());		
		return list.size() == 1 ? list.get(0) : null ;
	}
	
	public String getNome(){
		return nome;
	}
	
	public int getCodigoIbge(){
		return codigoIbge;
	}

	public String getSigla() {
		return sigla;
	}

	public Regiao getRegiao() {
		return regiao;
	}
	
	
	
}
