package br.org.quilombola.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import br.org.quilombola.arquitetura.BaseEntity;

@Entity
@Table(name = "matricula")
public class Matricula extends BaseEntity {

	private static final long serialVersionUID = 4926045695947277073L;

	@Column(name = "cartorio_registro_imoveis")
	private String cartorioRegistroImoveis;

	@Column(name = "livro_registro_imoveis")
	private String livroRegistroImoveis;

	@Column(name = "folha_registro_imoveis")
	private String folhaRegistroImoveis;

	@Column(name = "matricula")
	private String matricula;

	@JsonIgnoreProperties(
			value = {
					"municipioList",
					"quilombos",
					"ccdrus",
					"decretosDesapropriatorios",
					"editaisComunicacao",
					"matriculas",
					"portariasReconhecimento",
					"processosAdministrativos",
					"tituloPropriedade",
					"assentamentos",
					"dadosAdicionais"
			},
			allowSetters = true
	)
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "territorio_id", nullable = false)
	private Territorio territorio;

	private String nomeDocumento;

	@JsonIgnore
	private String extensaoDocumento;

	@JsonIgnore
	private byte[] binarioDocumento;

	@Column(name = "link_publicacao_1")
	private String linkPublicacao_1;

	@Column(name = "link_publicacao_2")
	private String linkPublicacao_2;

	@Column(name = "link_publicacao_3")
	private String linkPublicacao_3;

	@Column(name = "secao_do")
	private String secaoDO;

	@Column(name = "numero_oficio")
	private String numeroOficio;

	@Column(name = "folha_do")
	private String folhaDO;

	@Column(name = "data_registro_imoveis")
	private String dataRegistroImoveis;

	@Column(name = "nome_proprietario")
	private String nomeProprietario;

	public String getSecaoDO() {
		return secaoDO;
	}

	public void setSecaoDO(String secaoDO) {
		this.secaoDO = secaoDO;
	}

	public String getFolhaDO() {
		return folhaDO;
	}

	public void setFolhaDO(String folhaDO) {
		this.folhaDO = folhaDO;
	}

	public String getLinkPublicacao_1() {
		return linkPublicacao_1;
	}

	public void setLinkPublicacao_1(String linkPublicacao_1) {
		this.linkPublicacao_1 = linkPublicacao_1;
	}

	public String getLinkPublicacao_2() {
		return linkPublicacao_2;
	}

	public void setLinkPublicacao_2(String linkPublicacao_2) {
		this.linkPublicacao_2 = linkPublicacao_2;
	}

	public String getLinkPublicacao_3() {
		return linkPublicacao_3;
	}

	public void setLinkPublicacao_3(String linkPublicacao_3) {
		this.linkPublicacao_3 = linkPublicacao_3;
	}

	public String getNomeDocumento() {
		return nomeDocumento;
	}

	public void setNomeDocumento(String nomeDocumento) {
		this.nomeDocumento = nomeDocumento;
	}

	public String getExtensaoDocumento() {
		return extensaoDocumento;
	}

	public void setExtensaoDocumento(String extensaoDocumento) {
		this.extensaoDocumento = extensaoDocumento;
	}

	public byte[] getBinarioDocumento() {
		return binarioDocumento;
	}

	public void setBinarioDocumento(byte[] binarioDocumento) {
		this.binarioDocumento = binarioDocumento;
	}

	public Matricula() {
	}

	public Territorio getTerritorio() {
		return territorio;
	}

	public void setTerritorio(Territorio territorio) {
		this.territorio = territorio;
	}

	public String getCartorioRegistroImoveis() {
		return cartorioRegistroImoveis;
	}

	public void setCartorioRegistroImoveis(String cartorioRegistroImoveis) {
		this.cartorioRegistroImoveis = cartorioRegistroImoveis;
	}

	public String getLivroRegistroImoveis() {
		return livroRegistroImoveis;
	}

	public void setLivroRegistroImoveis(String livroRegistroImoveis) {
		this.livroRegistroImoveis = livroRegistroImoveis;
	}

	public String getFolhaRegistroImoveis() {
		return folhaRegistroImoveis;
	}

	public void setFolhaRegistroImoveis(String folhaRegistroImoveis) {
		this.folhaRegistroImoveis = folhaRegistroImoveis;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getNumeroOficio() {
		return numeroOficio;
	}

	public void setNumeroOficio(String numeroOficio) {
		this.numeroOficio = numeroOficio;
	}

	public String getDataRegistroImoveis() {
		return dataRegistroImoveis;
	}

	public void setDataRegistroImoveis(String dataRegistroImoveis) {
		this.dataRegistroImoveis = dataRegistroImoveis;
	}

	public String getNomeProprietario() {
		return nomeProprietario;
	}

	public void setNomeProprietario(String nomeProprietario) {
		this.nomeProprietario = nomeProprietario;
	}

}
