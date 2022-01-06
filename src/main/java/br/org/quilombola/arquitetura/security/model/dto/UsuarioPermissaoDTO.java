package br.org.quilombola.arquitetura.security.model.dto;

public class UsuarioPermissaoDTO {
	
	private Long usuarioId;
	private Long permissaoId;
	
	public UsuarioPermissaoDTO(Long usuarioId, Long permissaoId) {
		super();
		this.usuarioId = usuarioId;
		this.permissaoId = permissaoId;
	}

	public UsuarioPermissaoDTO() {
		super();
	}

	public Long getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}

	public Long getPermissaoId() {
		return permissaoId;
	}

	public void setPermissaoId(Long permissaoId) {
		this.permissaoId = permissaoId;
	}

	@Override
	public String toString() {
		return "UsuarioPermissaoDTO [usuarioId=" + usuarioId + ", permissaoId=" + permissaoId + "]";
	}
	
	
}
