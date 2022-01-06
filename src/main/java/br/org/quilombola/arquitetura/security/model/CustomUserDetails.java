package br.org.quilombola.arquitetura.security.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import br.org.quilombola.arquitetura.security.model.entity.Permissao;
import br.org.quilombola.arquitetura.security.model.entity.Usuario;

public class CustomUserDetails extends Usuario implements UserDetails {	
	
	private static final long serialVersionUID = 1L;
	private List<String> userRoles;
	

	public CustomUserDetails(Usuario user){
		super(user);
		this.userRoles=this.getPermissoesAsStringList();
	}
	
	private List<String> getPermissoesAsStringList(){
		List<String> list = new ArrayList<String>();
		for (Permissao permissao : super.getPermissoes()) {
			list.add(permissao.getDescricao());
		}
		return list;
	}
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		String roles=StringUtils.collectionToCommaDelimitedString(userRoles);			
		return AuthorityUtils.commaSeparatedStringToAuthorityList(roles);
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		return super.getEnabled();
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	@Override
	public boolean isEnabled() {
		return super.getEnabled();
	}

	@Override
	public String getUsername() {
		return super.getUserName();
	}

	@Override
	public String getPassword() {
		return super.getSenha();
	}

	


}
