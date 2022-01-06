package br.org.quilombola.model.dto;

public class MatriculaDTO {

	private Long id;
	
	private String cartorioRegistroImoveis;
	
	private String livroRegistroImoveis;
	
	private String folhaRegistroImoveis;
	
	private String matricula;
	
	private Long comunidade;
	
	private String linkPublicacao_1;
	
	private String linkPublicacao_2;
	
	private String linkPublicacao_3;

	private String secaoDO;
	
	private String folhaDO;	
	
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Long getComunidade() {
		return comunidade;
	}

	public void setComunidade(Long comunidade) {
		this.comunidade = comunidade;
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
	
}
