package br.org.quilombola.arquitetura.security.config;



import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import br.org.quilombola.arquitetura.security.model.CustomUserDetailsService;
import br.org.quilombola.enums.PermissoesSistemaEnum;



@Configuration
@EnableWebSecurity
@ComponentScan(basePackageClasses = CustomUserDetailsService.class)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordencoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		
		http.authorizeRequests().antMatchers("/").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.POST ,"/api/v1/hot-site/create-user/**").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.GET ,"/api/v1/relatorios/**").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.GET ,"/api/v1/comunidades/get-by-cod-uf/**").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.GET ,"/api/v1/comunidades/dados-consolidados-nacional").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.GET ,"/api/v1/comunidades/estado/**").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.GET ,"/api/v1/comunidades/**").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.GET ,"/api/v1/territorios/**").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.GET ,"/api/v1/territorios/**").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.GET ,"/api/v1/estados").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.POST ,"/api/v1/municipios/criteria").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.POST ,"/api/v1/territorios/criteria").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.POST ,"/api/v1/comunidades/criteria").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.POST ,"/api/v1/quilombos/criteria").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.POST ,"/api/v1/comunidades/search/**").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.POST ,"/api/v1/territorios/search/**").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.GET ,"/**/documento").permitAll();
		http.authorizeRequests().antMatchers("/hotsite_assets/**").permitAll();
		http.authorizeRequests().antMatchers("/").authenticated();
		http.authorizeRequests().antMatchers("/dashboard").hasAnyAuthority(Arrays.stream(PermissoesSistemaEnum.values()).map(Enum::name).toArray(String[]::new));
		http.authorizeRequests().antMatchers("/assets/**").permitAll();
		http.authorizeRequests().antMatchers("/assetslogin/**").permitAll();
		http.authorizeRequests().antMatchers("/css/**").permitAll();
		http.authorizeRequests().antMatchers("/img/**").permitAll();
		http.authorizeRequests().antMatchers("/js/**").permitAll();
		http.authorizeRequests().antMatchers("/layout/**").permitAll();
		http.authorizeRequests().antMatchers("/fontawesome5.1/**").permitAll();
		http.authorizeRequests().antMatchers("/cadastrar-usuario").permitAll();
		
		http.authorizeRequests().antMatchers("/403").permitAll();
		http.authorizeRequests().antMatchers("/404").permitAll();
		http.authorizeRequests().antMatchers("/500").permitAll();
		
		/**
		 * Permissões para API
		 */
		http.sessionManagement()
		.maximumSessions(1)
		.expiredUrl("/login?expired")
		.and()
		.invalidSessionUrl("/login?expired")		
        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
        .invalidSessionUrl("/login")
		;
		
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/v2/contatos").permitAll();
		
		http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/v2/contatos/**").hasAnyAuthority(
				PermissoesSistemaEnum.CRUD_CONTATOS.name(),
				PermissoesSistemaEnum.ADMINISTRADOR.name()
				);
		
		http.authorizeRequests().antMatchers(HttpMethod.PUT,"/api/v2/contatos/**").hasAnyAuthority(
				PermissoesSistemaEnum.CRUD_CONTATOS.name(),
				PermissoesSistemaEnum.ADMINISTRADOR.name()
				);
		
		http.authorizeRequests().antMatchers(HttpMethod.DELETE,"/api/v2/contatos/**").hasAnyAuthority(
				PermissoesSistemaEnum.CRUD_CONTATOS.name(),
				PermissoesSistemaEnum.ADMINISTRADOR.name()
				);
		
		http.authorizeRequests().antMatchers("/api/v1/territorios/**").hasAnyAuthority(
				PermissoesSistemaEnum.CRUD_TERRITORIO.name(),
				PermissoesSistemaEnum.READ_TERRITORIO.name(),
				PermissoesSistemaEnum.ADMINISTRADOR.name()
				);
		http.authorizeRequests().antMatchers("/api/v1/cetificados/**").hasAnyAuthority(
				PermissoesSistemaEnum.CRUD_CERTIFICADO.name(),
				PermissoesSistemaEnum.READ_CERTIFICADO.name(),
				PermissoesSistemaEnum.ADMINISTRADOR.name()
				);
		http.authorizeRequests().antMatchers("/api/v1/comunidades/**").hasAnyAuthority(
				PermissoesSistemaEnum.CRUD_COMUNIDADE.name(),
				PermissoesSistemaEnum.READ_COMUNIDADE.name(),
				PermissoesSistemaEnum.ADMINISTRADOR.name()
				);
		http.authorizeRequests().antMatchers("/api/v1/decretosdesapropriatorios/**").hasAnyAuthority(
				PermissoesSistemaEnum.CRUD_DECRETO_DESAPROPRIATORIO.name(),
				PermissoesSistemaEnum.READ_DECRETO_DESAPROPRIATORIO.name(),
				PermissoesSistemaEnum.ADMINISTRADOR.name()
				);
		http.authorizeRequests().antMatchers("/api/v1/documentos/**").hasAnyAuthority(
				PermissoesSistemaEnum.CRUD_DOCUMENTO.name(),
				PermissoesSistemaEnum.READ_DOCUMENTO.name(),
				PermissoesSistemaEnum.ADMINISTRADOR.name()
				);
		http.authorizeRequests().antMatchers("/api/v1/editaiscomunicacoes/**").hasAnyAuthority(
				PermissoesSistemaEnum.CRUD_EDITAL_COMUNICACAO.name(),
				PermissoesSistemaEnum.READ_EDITAL_COMUNICACAO.name(),
				PermissoesSistemaEnum.ADMINISTRADOR.name()
				);;
		http.authorizeRequests().antMatchers("/api/v1/matriculas/**").hasAnyAuthority(
				PermissoesSistemaEnum.CRUD_MATRICULA.name(),
				PermissoesSistemaEnum.READ_MATRICULA.name(),
				PermissoesSistemaEnum.ADMINISTRADOR.name()
				);;
		http.authorizeRequests().antMatchers("/api/v1/municipios/**").hasAnyAuthority(
				PermissoesSistemaEnum.CRUD_MUNICIPIO.name(),
				PermissoesSistemaEnum.READ_MUNICIPIO.name(),
				PermissoesSistemaEnum.ADMINISTRADOR.name()
				);;
		http.authorizeRequests().antMatchers("/api/v1/orgaos/**").hasAnyAuthority(
				PermissoesSistemaEnum.CRUD_ORGAO.name(),
				PermissoesSistemaEnum.READ_ORGAO.name(),
				PermissoesSistemaEnum.ADMINISTRADOR.name()
				);
		http.authorizeRequests().antMatchers("/api/v1/portariasreconhecimentos/**").hasAnyAuthority(
				PermissoesSistemaEnum.CRUD_PORTARIA_RECONHECIMENTO.name(),
				PermissoesSistemaEnum.READ_PORTARIA_RECONHECIMENTO.name(),
				PermissoesSistemaEnum.ADMINISTRADOR.name()
				);
		http.authorizeRequests().antMatchers("/api/v1/portarias/**").hasAnyAuthority(
				PermissoesSistemaEnum.CRUD_PORTARIA.name(),
				PermissoesSistemaEnum.READ_PORTARIA.name(),
				PermissoesSistemaEnum.ADMINISTRADOR.name()
				);
		http.authorizeRequests().antMatchers("/api/v1/processosadministrativos/**").hasAnyAuthority(
				PermissoesSistemaEnum.CRUD_PROCESSO_ADMINISTRATIVO.name(),
				PermissoesSistemaEnum.READ_PROCESSO_ADMINISTRATIVO.name(),
				PermissoesSistemaEnum.ADMINISTRADOR.name()
				);
		
		http.authorizeRequests().antMatchers("/api/v1/processosambitos/**").hasAnyAuthority(
				PermissoesSistemaEnum.CRUD_PROCESSO_RECONHECIMENTO.name(),
				PermissoesSistemaEnum.READ_PROCESSO_RECONHECIMENTO.name(),
				PermissoesSistemaEnum.ADMINISTRADOR.name()
				);
		http.authorizeRequests().antMatchers("/api/v1/quilombos/**").hasAnyAuthority(
				PermissoesSistemaEnum.CRUD_QUILOMBO.name(),
				PermissoesSistemaEnum.READ_QUILOMBO.name(),
				PermissoesSistemaEnum.ADMINISTRADOR.name()
				);
		http.authorizeRequests().antMatchers("/api/v1/tiposconcessoes/**").hasAnyAuthority(
				PermissoesSistemaEnum.CRUD_TIPO_CONCESSAO.name(),
				PermissoesSistemaEnum.READ_TIPO_CONCESSAO.name(),
				PermissoesSistemaEnum.ADMINISTRADOR.name()
				);
		http.authorizeRequests().antMatchers("/api/v1/tipodocumentos/**").hasAnyAuthority(
				PermissoesSistemaEnum.CRUD_TIPO_DOCUMENTO.name(),
				PermissoesSistemaEnum.READ_TIPO_DOCUMENTO.name(),
				PermissoesSistemaEnum.ADMINISTRADOR.name()
				);
		http.authorizeRequests().antMatchers("/api/v1/titulospropriedades/**").hasAnyAuthority(
				PermissoesSistemaEnum.CRUD_TITULO_PROPRIEDADE.name(),
				PermissoesSistemaEnum.READ_TITULO_PROPRIEDADE.name(),
				PermissoesSistemaEnum.ADMINISTRADOR.name()
				);
		http.authorizeRequests().antMatchers("/api/v1/estados").hasAnyAuthority(
				PermissoesSistemaEnum.READ_ESTADOS.name(),
				PermissoesSistemaEnum.ADMINISTRADOR.name()
				);
		http.authorizeRequests().antMatchers("/api/v1/regioes").hasAnyAuthority(
				PermissoesSistemaEnum.READ_REGIOES.name(),
				PermissoesSistemaEnum.ADMINISTRADOR.name()
				);
		http.authorizeRequests().antMatchers("/api/v1/superintendencias/**").hasAnyAuthority(
				PermissoesSistemaEnum.CRUD_SUPERINTENDENCIA.name(),
				PermissoesSistemaEnum.ADMINISTRADOR.name()
				);
		/**
		 * Permissões para as URLs
		 */
		http.authorizeRequests().antMatchers("/territorios/**").hasAnyAuthority(
				PermissoesSistemaEnum.CRUD_TERRITORIO.name(),
				PermissoesSistemaEnum.READ_TERRITORIO.name(),
				PermissoesSistemaEnum.ADMINISTRADOR.name()
				);
		http.authorizeRequests().antMatchers("/cetificados/**").hasAnyAuthority(
				PermissoesSistemaEnum.CRUD_CERTIFICADO.name(),
				PermissoesSistemaEnum.READ_CERTIFICADO.name(),
				PermissoesSistemaEnum.ADMINISTRADOR.name()
				);
		http.authorizeRequests().antMatchers("/comunidades/**").hasAnyAuthority(
				PermissoesSistemaEnum.CRUD_COMUNIDADE.name(),
				PermissoesSistemaEnum.READ_COMUNIDADE.name(),
				PermissoesSistemaEnum.ADMINISTRADOR.name()
				);
		http.authorizeRequests().antMatchers("/decretosdesapropriatorios/**").hasAnyAuthority(
				PermissoesSistemaEnum.CRUD_DECRETO_DESAPROPRIATORIO.name(),
				PermissoesSistemaEnum.READ_DECRETO_DESAPROPRIATORIO.name(),
				PermissoesSistemaEnum.ADMINISTRADOR.name()
				);
		http.authorizeRequests().antMatchers("/documentos/**").hasAnyAuthority(
				PermissoesSistemaEnum.CRUD_DOCUMENTO.name(),
				PermissoesSistemaEnum.READ_DOCUMENTO.name(),
				PermissoesSistemaEnum.ADMINISTRADOR.name()
				);
		http.authorizeRequests().antMatchers("/editaiscomunicacoes/**").hasAnyAuthority(
				PermissoesSistemaEnum.CRUD_EDITAL_COMUNICACAO.name(),
				PermissoesSistemaEnum.READ_EDITAL_COMUNICACAO.name(),
				PermissoesSistemaEnum.ADMINISTRADOR.name()
				);
		http.authorizeRequests().antMatchers("/matriculas/**").hasAnyAuthority(
				PermissoesSistemaEnum.CRUD_MATRICULA.name(),
				PermissoesSistemaEnum.READ_MATRICULA.name(),
				PermissoesSistemaEnum.ADMINISTRADOR.name()
				);
		http.authorizeRequests().antMatchers("/municipios/**").hasAnyAuthority(
				PermissoesSistemaEnum.CRUD_MUNICIPIO.name(),
				PermissoesSistemaEnum.READ_MUNICIPIO.name(),
				PermissoesSistemaEnum.ADMINISTRADOR.name()
				);
		http.authorizeRequests().antMatchers("/orgaos/**").hasAnyAuthority(
				PermissoesSistemaEnum.CRUD_ORGAO.name(),
				PermissoesSistemaEnum.READ_ORGAO.name(),
				PermissoesSistemaEnum.ADMINISTRADOR.name()
				);
		http.authorizeRequests().antMatchers("/portariasreconhecimentos/**").hasAnyAuthority(
				PermissoesSistemaEnum.CRUD_PORTARIA_RECONHECIMENTO.name(),
				PermissoesSistemaEnum.READ_PORTARIA_RECONHECIMENTO.name(),
				PermissoesSistemaEnum.ADMINISTRADOR.name()
				);
		http.authorizeRequests().antMatchers("/portarias/**").hasAnyAuthority(
				PermissoesSistemaEnum.CRUD_PORTARIA.name(),
				PermissoesSistemaEnum.READ_PORTARIA.name(),
				PermissoesSistemaEnum.ADMINISTRADOR.name()
				);
		http.authorizeRequests().antMatchers("/processosadministrativos/**").hasAnyAuthority(
				PermissoesSistemaEnum.CRUD_PROCESSO_ADMINISTRATIVO.name(),
				PermissoesSistemaEnum.READ_PROCESSO_ADMINISTRATIVO.name(),
				PermissoesSistemaEnum.ADMINISTRADOR.name()
				);
		http.authorizeRequests().antMatchers("/processosambitos/**").hasAnyAuthority(
				PermissoesSistemaEnum.CRUD_PROCESSO_RECONHECIMENTO.name(),
				PermissoesSistemaEnum.READ_PROCESSO_RECONHECIMENTO.name(),
				PermissoesSistemaEnum.ADMINISTRADOR.name()
				);
		http.authorizeRequests().antMatchers("/quilombos/**").hasAnyAuthority(
				PermissoesSistemaEnum.CRUD_QUILOMBO.name(),
				PermissoesSistemaEnum.READ_QUILOMBO.name(),
				PermissoesSistemaEnum.ADMINISTRADOR.name()
				);
		http.authorizeRequests().antMatchers("/tiposconcessoes/**").hasAnyAuthority(
				PermissoesSistemaEnum.CRUD_TIPO_CONCESSAO.name(),
				PermissoesSistemaEnum.READ_TIPO_CONCESSAO.name(),
				PermissoesSistemaEnum.ADMINISTRADOR.name()
				);
		http.authorizeRequests().antMatchers("/tiposdocumentos/**").hasAnyAuthority(
				PermissoesSistemaEnum.CRUD_TIPO_DOCUMENTO.name(),
				PermissoesSistemaEnum.READ_TIPO_DOCUMENTO.name(),
				PermissoesSistemaEnum.ADMINISTRADOR.name()
				);
		http.authorizeRequests().antMatchers("/titulospropriedades/**").hasAnyAuthority(
				PermissoesSistemaEnum.CRUD_TITULO_PROPRIEDADE.name(),
				PermissoesSistemaEnum.READ_TITULO_PROPRIEDADE.name(),
				PermissoesSistemaEnum.ADMINISTRADOR.name()
				);
		http.authorizeRequests().antMatchers("/superintendencias/**").hasAnyAuthority(
				PermissoesSistemaEnum.CRUD_SUPERINTENDENCIA.name(),
				PermissoesSistemaEnum.ADMINISTRADOR.name()
				);
		http.authorizeRequests().antMatchers("/assentamentos/**").hasAnyAuthority(
				PermissoesSistemaEnum.CRUD_ASSENTAMENTOS.name(),
				PermissoesSistemaEnum.MASTER.name(),
				PermissoesSistemaEnum.ADMINISTRADOR.name()
				);
		http.authorizeRequests().antMatchers("/dadosadicionais/**").hasAnyAuthority(
				PermissoesSistemaEnum.CRUD_DADOS_ADICIONAIS.name(),
				PermissoesSistemaEnum.MASTER.name(),
				PermissoesSistemaEnum.ADMINISTRADOR.name()
				);
		http.authorizeRequests().antMatchers("/estados").hasAnyAuthority(
				PermissoesSistemaEnum.READ_ESTADOS.name(),
				PermissoesSistemaEnum.ADMINISTRADOR.name()
				);
		http.authorizeRequests().antMatchers("/permissoes").hasAnyAuthority(
				PermissoesSistemaEnum.ADMINISTRADOR.name()
				);
		http.authorizeRequests().antMatchers("/usuarios").hasAnyAuthority(
				PermissoesSistemaEnum.ADMINISTRADOR.name()
				);
		http.authorizeRequests().antMatchers("/api/v1/security/usuarios/**").hasAnyAuthority(
				PermissoesSistemaEnum.ADMINISTRADOR.name()
				);
		
		http.formLogin().loginPage("/login")
			.permitAll()
			.defaultSuccessUrl("/");
		
		http.logout().permitAll()
			.logoutSuccessUrl("/login?logout")
			.deleteCookies("JSESSIONID")
			.and()
			.exceptionHandling()
			.accessDeniedPage("/403");
		
		http.csrf().disable();
	}

	@Bean(name = "passwordEncoder")
	public PasswordEncoder passwordencoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
}