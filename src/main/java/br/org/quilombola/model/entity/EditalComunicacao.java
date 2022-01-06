package br.org.quilombola.model.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.*;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.Type;

import br.org.quilombola.arquitetura.BaseEntity;

@Entity
@Table(name = "editalcomunicacao")
public class EditalComunicacao extends BaseEntity {

	private static final long serialVersionUID = 687020159829099211L;

	@JsonFormat
    (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'hh:mm:ss")
	@Column(name = "dou_doe_primeiro_edital")
	private Date dataDiarioOficialUniaoDiarioOficialEstadoPrimeiroEdital;

	@JsonFormat
    (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'hh:mm:ss")
	@Column(name = "dou_doe_segundo_edital")
	private Date dataDiarioOficialUniaoDiarioOficialEstadoSegundoEdital;

	@Column(name = "numero_familias")
	private Integer numeroFamilias = 0;

	@Column(name = "areaha_titulo", precision = 19, scale = 4)
	@Type(type = "big_decimal")
	private BigDecimal areaHaEdital = BigDecimal.ZERO;

	@Column(name = "areaha_2", precision = 19, scale = 4)
	@Type(type = "big_decimal")
	private BigDecimal areaHa2 = BigDecimal.ZERO;

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

	@Column(name = "folha_do")
	private String folhaDO;

	// ----
	@Column(name = "link_publicacao_4")
	private String linkPublicacao_4;

	@Column(name = "link_publicacao_5")
	private String linkPublicacao_5;

	@Column(name = "link_publicacao_6")
	private String linkPublicacao_6;

	@Column(name = "link_publicacao_7")
	private String linkPublicacao_7;

	@Column(name = "link_publicacao_8")
	private String linkPublicacao_8;

	@Column(name = "link_publicacao_9")
	private String linkPublicacao_9;

	@Column(name = "link_publicacao_10")
	private String linkPublicacao_10;

	@Column(name = "link_publicacao_11")
	private String linkPublicacao_11;

	@Column(name = "link_publicacao_12")
	private String linkPublicacao_12;

	@Column(name = "link_publicacao_13")
	private String linkPublicacao_13;

	@Column(name = "link_publicacao_14")
	private String linkPublicacao_14;

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
		return StringUtils.isAllEmpty(linkPublicacao_1) ? "": linkPublicacao_1;
	}

	public void setLinkPublicacao_1(String linkPublicacao_1) {
		this.linkPublicacao_1 = linkPublicacao_1;
	}

	public String getLinkPublicacao_2() {
		return StringUtils.isAllEmpty(linkPublicacao_2) ? "": linkPublicacao_2;
	}

	public void setLinkPublicacao_2(String linkPublicacao_2) {
		this.linkPublicacao_2 = linkPublicacao_2;
	}

	public String getLinkPublicacao_3() {
		return StringUtils.isAllEmpty(linkPublicacao_3) ? "": linkPublicacao_3;
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

	public Date getDataDiarioOficialUniaoDiarioOficialEstadoPrimeiroEdital() {
		return dataDiarioOficialUniaoDiarioOficialEstadoPrimeiroEdital;
	}

	public void setDataDiarioOficialUniaoDiarioOficialEstadoPrimeiroEdital(
			Date dataDiarioOficialUniaoDiarioOficialEstadoPrimeiroEdital) {
		this.dataDiarioOficialUniaoDiarioOficialEstadoPrimeiroEdital = dataDiarioOficialUniaoDiarioOficialEstadoPrimeiroEdital;
	}

	public Date getDataDiarioOficialUniaoDiarioOficialEstadoSegundoEdital() {
		return dataDiarioOficialUniaoDiarioOficialEstadoSegundoEdital;
	}

	public void setDataDiarioOficialUniaoDiarioOficialEstadoSegundoEdital(
			Date dataDiarioOficialUniaoDiarioOficialEstadoSegundoEdital) {
		this.dataDiarioOficialUniaoDiarioOficialEstadoSegundoEdital = dataDiarioOficialUniaoDiarioOficialEstadoSegundoEdital;
	}

	public Integer getNumeroFamilias() {
		numeroFamilias = !ObjectUtils.anyNotNull(numeroFamilias) ? 0 : numeroFamilias;
		return numeroFamilias;
	}

	public void setNumeroFamilias(Integer numeroFamilias) {
		this.numeroFamilias = numeroFamilias;
	}

	public BigDecimal getAreaHaEdital() {
		return areaHaEdital != null ? areaHaEdital : BigDecimal.ZERO;
	}

	public void setAreaHaEdital(BigDecimal areaHaEdital) {
		this.areaHaEdital = areaHaEdital;
	}

	public Territorio getTerritorio() {
		return territorio;
	}

	public void setTerritorio(Territorio territorio) {
		this.territorio = territorio;
	}

	public BigDecimal getAreaHa2() {
		return areaHa2 != null ? areaHa2 : BigDecimal.ZERO;
	}

	public void setAreaHa2(BigDecimal areaHa2) {
		this.areaHa2 = areaHa2;
	}

	public String getLinkPublicacao_4() {
		return StringUtils.isAllEmpty(linkPublicacao_4) ? "": linkPublicacao_4;
	}

	public void setLinkPublicacao_4(String linkPublicacao_4) {
		this.linkPublicacao_4 = linkPublicacao_4;
	}

	public String getLinkPublicacao_5() {
		return StringUtils.isAllEmpty(linkPublicacao_5) ? "": linkPublicacao_5;
	}

	public void setLinkPublicacao_5(String linkPublicacao_5) {
		this.linkPublicacao_5 = linkPublicacao_5;
	}

	public String getLinkPublicacao_6() {
		return StringUtils.isAllEmpty(linkPublicacao_6) ? "": linkPublicacao_6;
	}

	public void setLinkPublicacao_6(String linkPublicacao_6) {
		this.linkPublicacao_6 = linkPublicacao_6;
	}

	public String getLinkPublicacao_7() {
		return StringUtils.isAllEmpty(linkPublicacao_7) ? "": linkPublicacao_7;
	}

	public void setLinkPublicacao_7(String linkPublicacao_7) {
		this.linkPublicacao_7 = linkPublicacao_7;
	}

	public String getLinkPublicacao_8() {
		return StringUtils.isAllEmpty(linkPublicacao_8) ? "": linkPublicacao_8;
	}

	public void setLinkPublicacao_8(String linkPublicacao_8) {
		this.linkPublicacao_8 = linkPublicacao_8;
	}

	public String getLinkPublicacao_9() {
		return StringUtils.isAllEmpty(linkPublicacao_9) ? "": linkPublicacao_9;
	}

	public void setLinkPublicacao_9(String linkPublicacao_9) {
		this.linkPublicacao_9 = linkPublicacao_9;
	}

	public String getLinkPublicacao_10() {
		return StringUtils.isAllEmpty(linkPublicacao_10) ? "": linkPublicacao_10;
	}

	public void setLinkPublicacao_10(String linkPublicacao_10) {
		this.linkPublicacao_10 = linkPublicacao_10;
	}

	public String getLinkPublicacao_11() {
		return StringUtils.isAllEmpty(linkPublicacao_11) ? "": linkPublicacao_11;
	}

	public void setLinkPublicacao_11(String linkPublicacao_11) {
		this.linkPublicacao_11 = linkPublicacao_11;
	}

	public String getLinkPublicacao_12() {
		return StringUtils.isAllEmpty(linkPublicacao_12) ? "": linkPublicacao_12;
	}

	public void setLinkPublicacao_12(String linkPublicacao_12) {
		this.linkPublicacao_12 = linkPublicacao_12;
	}

	public String getLinkPublicacao_13() {
		return StringUtils.isAllEmpty(linkPublicacao_13) ? "": linkPublicacao_13;
	}

	public void setLinkPublicacao_13(String linkPublicacao_13) {
		this.linkPublicacao_13 = linkPublicacao_13;
	}

	public String getLinkPublicacao_14() {
		return StringUtils.isAllEmpty(linkPublicacao_14) ? "": linkPublicacao_14;
	}

	public void setLinkPublicacao_14(String linkPublicacao_14) {
		this.linkPublicacao_14 = linkPublicacao_14;
	}

}
