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
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import br.org.quilombola.arquitetura.BaseEntity;
import br.org.quilombola.enums.TipoAssentamentoEnum;
import br.org.quilombola.model.converter.TipoAssentamentoToStringConverter;

@Entity
@Table(name = "assentamento_quilombola")
public class AssentamentoQuilombola extends BaseEntity{

	private static final long serialVersionUID = 239485279182804594L;

	@Column(name = "numero")
	private String numero;

	@JsonFormat
    (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'hh:mm:ss")
	@Column(name = "data_portaria")
	private Date dataPortaria;

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

	@JsonFormat
    (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'hh:mm:ss")
	@Column(name = "data_dou")
	private Date dataDO;
	
	@Column(name = "secao_dou")
	private String secaoDO;
	
	@Column(name = "folha_dou")
	private String folhaDO;
	
	@JsonFormat
    (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'hh:mm:ss")
	@Column(name = "data_decreto")
	private Date dataDecreto;
	
	@Column(name = "link_publicacao_decreto_1")
	private String linkPublicacaoDecreto_1;
	
	@Column(name = "link_publicacao_decreto_2")
	private String linkPublicacaoDecreto_2;
	
	@Column(name = "link_publicacao_decreto_3")
	private String linkPublicacaoDecreto_3;
	
	@JsonFormat
    (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'hh:mm:ss")
	@Column(name = "data_diario_uniao_decreto")
	private Date dataDiarioUniaoDecreto;
	
	@Column(name = "link_publicacao_diario_uniao_decreto_1")
	private String linkPublicacaoDiarioUniaoDecreto_1;
	
	@Column(name = "link_publicacao_diario_uniao_decreto_2")
	private String linkPublicacaoDiarioUniaoDecreto_2;
	
	@Column(name = "link_publicacao_diario_uniao_decreto_3")
	private String linkPublicacaoDiarioUniaoDecreto_3;
	
	@Column(name = "area",  precision = 19, scale = 4)
	@Type(type = "big_decimal")
	private BigDecimal area;	
	
	@Column(name = "tipo")
	private String tipo;
	
	private String nomeDocumento;
	
	@JsonIgnore
	private String extensaoDocumento;
	
	@JsonIgnore
//	@Lob
    private byte[] binarioDocumento;

	public AssentamentoQuilombola(String numero, Date dataPortaria, Date dataDO, String secaoDO,
			String folhaDO, BigDecimal area, String tipo) {
		super();
		this.numero = numero;
		this.dataPortaria = dataPortaria;
		this.dataDO = dataDO;
		this.secaoDO = secaoDO;
		this.folhaDO = folhaDO;
		this.area = area;
		this.tipo = tipo;
	}

	public AssentamentoQuilombola() {
		super();
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Date getDataPortaria() {
		return dataPortaria;
	}

	public void setDataPortaria(Date dataPortaria) {
		this.dataPortaria = dataPortaria;
	}

	public Date getDataDO() {
		return dataDO;
	}

	public void setDataDO(Date dataDO) {
		this.dataDO = dataDO;
	}

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

	public BigDecimal getArea() {
		return area;
	}

	public void setArea(BigDecimal area) {
		this.area = area;
	}

	public TipoAssentamentoEnum getTipo() {
		if(tipo == null) {
			return null;
		}
		return TipoAssentamentoEnum.valueOf(tipo);
	}

	@JsonDeserialize(converter = TipoAssentamentoToStringConverter.class)
	public void setTipo(String tipo) {
		this.tipo = tipo;
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

	public Date getDataDecreto() {
		return dataDecreto;
	}

	public void setDataDecreto(Date dataDecreto) {
		this.dataDecreto = dataDecreto;
	}

	public String getLinkPublicacaoDecreto_1() {
		return linkPublicacaoDecreto_1;
	}

	public void setLinkPublicacaoDecreto_1(String linkPublicacaoDecreto_1) {
		this.linkPublicacaoDecreto_1 = linkPublicacaoDecreto_1;
	}

	public String getLinkPublicacaoDecreto_2() {
		return linkPublicacaoDecreto_2;
	}

	public void setLinkPublicacaoDecreto_2(String linkPublicacaoDecreto_2) {
		this.linkPublicacaoDecreto_2 = linkPublicacaoDecreto_2;
	}

	public String getLinkPublicacaoDecreto_3() {
		return linkPublicacaoDecreto_3;
	}

	public void setLinkPublicacaoDecreto_3(String linkPublicacaoDecreto_3) {
		this.linkPublicacaoDecreto_3 = linkPublicacaoDecreto_3;
	}

	public Date getDataDiarioUniaoDecreto() {
		return dataDiarioUniaoDecreto;
	}

	public void setDataDiarioUniaoDecreto(Date dataDiarioUniaoDecreto) {
		this.dataDiarioUniaoDecreto = dataDiarioUniaoDecreto;
	}

	public String getLinkPublicacaoDiarioUniaoDecreto_1() {
		return linkPublicacaoDiarioUniaoDecreto_1;
	}

	public void setLinkPublicacaoDiarioUniaoDecreto_1(String linkPublicacaoDiarioUniaoDecreto_1) {
		this.linkPublicacaoDiarioUniaoDecreto_1 = linkPublicacaoDiarioUniaoDecreto_1;
	}

	public String getLinkPublicacaoDiarioUniaoDecreto_2() {
		return linkPublicacaoDiarioUniaoDecreto_2;
	}

	public void setLinkPublicacaoDiarioUniaoDecreto_2(String linkPublicacaoDiarioUniaoDecreto_2) {
		this.linkPublicacaoDiarioUniaoDecreto_2 = linkPublicacaoDiarioUniaoDecreto_2;
	}

	public String getLinkPublicacaoDiarioUniaoDecreto_3() {
		return linkPublicacaoDiarioUniaoDecreto_3;
	}

	public void setLinkPublicacaoDiarioUniaoDecreto_3(String linkPublicacaoDiarioUniaoDecreto_3) {
		this.linkPublicacaoDiarioUniaoDecreto_3 = linkPublicacaoDiarioUniaoDecreto_3;
	}
	
	public Territorio getTerritorio() {
        return territorio;
    }

    public void setTerritorio(Territorio territorio) {
        this.territorio = territorio;
    }
	
}
