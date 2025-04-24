package com.sbmtech.mms.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import com.sbmtech.mms.security.jwt.AuthEntryPointJwt;
import com.sbmtech.mms.security.jwt.AuthTokenFilter;
import com.sbmtech.mms.security.services.UserDetailsServiceImpl;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
		// securedEnabled = true,
		// jsr250Enabled = true,
		prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private static final String[] PUBLIC_URLS = { "/v2/api-docs", "/configuration/ui", "/swagger-resources/**",
			"/configuration/security", "/swagger-ui/**", "/webjars/**", "/static/**", "/static/static/**",
			"/static/static/css/**", "/static/static/js/**", "/static/static/media/**", "/index.html",
			// "/asset-manifest.json",
			// "/manifest.json",
			"/favicon.ico", "/public/**", "/css/**", "/js/**", "/ssui/**", "/inquiry/**", "/ssui/public/**",
			"/api/cron/**"

	};

	@Autowired
	UserDetailsServiceImpl userDetailsService;

	@Autowired
	private AuthEntryPointJwt unauthorizedHandler;

	@Bean
	public AuthTokenFilter authenticationJwtTokenFilter() {
		return new AuthTokenFilter();
	}

	@Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration
				.setAllowedOrigins(List.of("http://localhost:54664", "http://localhost:8090", "http://localhost:3000",
						"http://localhost:3001", "https://dev.selfserviceteam.com", "https://ppe.selfserviceteam.com"));
		corsConfiguration.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type", "x-auth-token"));
		corsConfiguration
				.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PUT", "OPTIONS", "PATCH", "DELETE"));

		// corsConfiguration.setAllowCredentials(true); //allow this in prod
		corsConfiguration.addAllowedOrigin("*"); // remove this

		corsConfiguration.setMaxAge(3000L);
		corsConfiguration.setExposedHeaders(List.of("Authorization"));

		http.cors().configurationSource(request -> corsConfiguration).and().csrf().disable().exceptionHandling()
				.authenticationEntryPoint(unauthorizedHandler).and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

				.authorizeRequests().antMatchers("/api/subs/**").permitAll().antMatchers("/api/test/**").permitAll()
				.antMatchers(PUBLIC_URLS).permitAll().anyRequest().authenticated();

		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
		/*
		 * http .headers() .xssProtection() .and()
		 * .contentSecurityPolicy("script-src 'self'");
		 */
	}
}
