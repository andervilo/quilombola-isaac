package br.org.quilombola.arquitetura.security.model.dto;

public class UsuarioDTO {

	private Long id;

	private String nome;
	private String email;
	private String senha;
	private String confirmacaoSenha;
	private String userName;
	private Boolean enabled;



	public UsuarioDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public UsuarioDTO(Long id, String nome, String email, String senha, String confirmacaoSenha, String userName,
			Boolean enabled) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.confirmacaoSenha = confirmacaoSenha;
		this.userName = userName;
		this.enabled = enabled;
	}



	public Long getCodigo() {
		return id;
	}

	public void setCodigo(Long codigo) {
		this.id = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getConfirmacaoSenha() {
		return confirmacaoSenha;
	}

	public void setConfirmacaoSenha(String confirmacaoSenha) {
		this.confirmacaoSenha = confirmacaoSenha;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}


}

