package br.org.quilombola.model.entity.relatorios;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import br.org.quilombola.arquitetura.BaseEntity;

@Entity
@Table(name = "relatorio_assentamento")
public class RelatorioTerritorioAssentamento extends BaseEntity {

	private static final long serialVersionUID = 4577860073500084747L;

	@Column(name = "id_territorio")
	private Integer idTerritorio;

	@Column(name = "territorio")
	private String territorio;

	@Column(name = "municipios")
	private String municipio;

	@Column(name = "estado")
	private String estado;

	@Column(name = "familias")
	private Integer familias;

	@Column(name = "area")
	private Double area;

	@Column(name = "numero")
	private String numero;

	@Column(name = "data_portaria")
	private String dataPortaria;

	@Column(name = "data_dou")
	private String dataDou;

	@Column(name = "secao_dou")
	private String secaoDou;

	@Column(name = "folha_dou")
	private String folhaDou;

	@Column(name = "link_publicacao_decreto_1")
	private String linkPublicacaoDecreto1;

	@Column(name = "link_publicacao_decreto_2")
	private String linkPublicacaoDecreto2;

	@Column(name = "numero_teq")
	private String numeroTeq;

	@Column(name = "data_decreto")
	private String dataDecreto;

	@Column(name = "data_diario_uniao_decreto")
	private String dataDiarioUniaoDecreto;

	@Column(name = "cartorio")
	private String cartorioCartorio;

	@Column(name = "livro_cartorio")
	private String livroCartorio;

	@Column(name = "folha_cartorio")
	private String folhaCartorio;

	@Column(name = "matricula_cartorio")
	private String matriculaCartorio;

	public RelatorioTerritorioAssentamento() {
	}

	public RelatorioTerritorioAssentamento(Integer idTerritorio, String territorio, String municipio, String estado,
			Integer familias, Double area, String numero, String dataPortaria, String dataDou, String secaoDou,
			String folhaDou, String linkPublicacaoDecreto1, String linkPublicacaoDecreto2, String numeroTeq,
			String dataDecreto, String dataDiarioUniaoDecreto, String cartorioCartorio, String livroCartorio,
			String folhaCartorio, String matriculaCartorio) {
		super();
		this.idTerritorio = idTerritorio;
		this.territorio = territorio;
		this.municipio = municipio;
		this.estado = estado;
		this.familias = familias;
		this.area = area;
		this.numero = numero;
		this.dataPortaria = dataPortaria;
		this.dataDou = dataDou;
		this.secaoDou = secaoDou;
		this.folhaDou = folhaDou;
		this.linkPublicacaoDecreto1 = linkPublicacaoDecreto1;
		this.linkPublicacaoDecreto2 = linkPublicacaoDecreto2;
		this.numeroTeq = numeroTeq;
		this.dataDecreto = dataDecreto;
		this.dataDiarioUniaoDecreto = dataDiarioUniaoDecreto;
		this.cartorioCartorio = cartorioCartorio;
		this.livroCartorio = livroCartorio;
		this.folhaCartorio = folhaCartorio;
		this.matriculaCartorio = matriculaCartorio;
	}

	@Column(name = "id_territorio")
	public Integer getIdTerritorio() {
		return idTerritorio;
	}

	@Column(name = "id_territorio")
	public void setIdTerritorio(Integer idTerritorio) {
		this.idTerritorio = idTerritorio;
	}

	@Column(name = "territorio")
	public String getTerritorio() {
		return territorio;
	}

	@Column(name = "territorio")
	public void setTerritorio(String territorio) {
		this.territorio = territorio;
	}

	@Column(name = "municipio")
	public String getMunicipio() {
		return municipio;
	}

	@Column(name = "municipio")
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	@Column(name = "estado")
	public String getEstado() {
		return estado;
	}

	@Column(name = "estado")
	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Column(name = "familias")
	public Integer getFamilias() {
		return familias;
	}

	@Column(name = "familias")
	public void setFamilias(Integer familias) {
		this.familias = familias;
	}

	@Column(name = "area")
	public Double getArea() {
		return area;
	}

	@Column(name = "area")
	public void setArea(Double area) {
		this.area = area;
	}

	@Column(name = "numero")
	public String getNumero() {
		return numero;
	}

	@Column(name = "numero")
	public void setNumero(String numero) {
		this.numero = numero;
	}

	@Column(name = "data_portaria")
	public String getDataPortaria() {
		return dataPortaria;
	}

	@Column(name = "data_portaria")
	public void setDataPortaria(String dataPortaria) {
		this.dataPortaria = dataPortaria;
	}

	@Column(name = "data_dou")
	public String getDataDou() {
		return dataDou;
	}

	@Column(name = "data_dou")
	public void setDataDou(String dataDou) {
		this.dataDou = dataDou;
	}

	@Column(name = "secao_dou")
	public String getSecaoDou() {
		return secaoDou;
	}

	@Column(name = "secao_dou")
	public void setSecaoDou(String secaoDou) {
		this.secaoDou = secaoDou;
	}

	@Column(name = "folha_dou")
	public String getFolhaDou() {
		return folhaDou;
	}

	@Column(name = "folha_dou")
	public void setFolhaDou(String folhaDou) {
		this.folhaDou = folhaDou;
	}

	@Column(name = "link_publicacao_decreto_1")
	public String getLinkPublicacaoDecreto1() {
		return linkPublicacaoDecreto1;
	}

	@Column(name = "link_publicacao_decreto_1")
	public void setLinkPublicacaoDecreto1(String linkPublicacaoDecreto1) {
		this.linkPublicacaoDecreto1 = linkPublicacaoDecreto1;
	}

	@Column(name = "link_publicacao_decreto_2")
	public String getLinkPublicacaoDecreto2() {
		return linkPublicacaoDecreto2;
	}

	@Column(name = "link_publicacao_decreto_2")
	public void setLinkPublicacaoDecreto2(String linkPublicacaoDecreto2) {
		this.linkPublicacaoDecreto2 = linkPublicacaoDecreto2;
	}

	@Column(name = "numero_teq")
	public String getNumeroTeq() {
		return numeroTeq;
	}

	@Column(name = "numero_teq")
	public void setNumeroTeq(String numeroTeq) {
		this.numeroTeq = numeroTeq;
	}

	@Column(name = "data_decreto")
	public String getDataDecreto() {
		return dataDecreto;
	}

	@Column(name = "data_decreto")
	public void setDataDecreto(String dataDecreto) {
		this.dataDecreto = dataDecreto;
	}

	@Column(name = "data_diario_uniao_decreto")
	public String getDataDiarioUniaoDecreto() {
		return dataDiarioUniaoDecreto;
	}

	@Column(name = "data_diario_uniao_decreto")
	public void setDataDiarioUniaoDecreto(String dataDiarioUniaoDecreto) {
		this.dataDiarioUniaoDecreto = dataDiarioUniaoDecreto;
	}

	@Column(name = "cartorio_cartorio")
	public String getCartorioCartorio() {
		return cartorioCartorio;
	}

	@Column(name = "cartorio_cartorio")
	public void setCartorioCartorio(String cartorioCartorio) {
		this.cartorioCartorio = cartorioCartorio;
	}

	@Column(name = "livro_cartorio")
	public String getLivroCartorio() {
		return livroCartorio;
	}

	@Column(name = "livro_cartorio")
	public void setLivroCartorio(String livroCartorio) {
		this.livroCartorio = livroCartorio;
	}

	@Column(name = "folha_cartorio")
	public String getFolhaCartorio() {
		return folhaCartorio;
	}

	@Column(name = "folha_cartorio")
	public void setFolhaCartorio(String folhaCartorio) {
		this.folhaCartorio = folhaCartorio;
	}

	@Column(name = "matricula_cartorio")
	public String getMatriculaCartorio() {
		return matriculaCartorio;
	}

	@Column(name = "matricula_cartorio")
	public void setMatriculaCartorio(String matriculaCartorio) {
		this.matriculaCartorio = matriculaCartorio;
	}

}
