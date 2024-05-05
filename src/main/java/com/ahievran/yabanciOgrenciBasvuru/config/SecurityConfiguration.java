package com.ahievran.yabanciOgrenciBasvuru.config;

import com.ahievran.yabanciOgrenciBasvuru.business.abstracts.KisiKayitService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.security.config.http.SessionCreationPolicy.ALWAYS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
	private static final String[] WHITE_LIST_URL = {
			"/registration/**",
			"/js/**",
			"/css/**",
			"/images/**",
			"/kaydol/**",
			"/img/**",
			"/verifyaccount/**",
			"/showerify/**",
			"/forgetpassword/**",
			"/sendmail/**",
			"/basvurugoster/**",
	};
	private final KisiKayitService kisiKayitService;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(kisiKayitService);
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}


	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		return http
				.csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(x ->
						x.requestMatchers(WHITE_LIST_URL).permitAll()
								.anyRequest().authenticated()
				)
				.sessionManagement(session -> session.sessionCreationPolicy(ALWAYS))
				.authenticationProvider(authenticationProvider())
				.formLogin(formLogin ->
						formLogin.loginPage("/login").permitAll()
								.defaultSuccessUrl("/", true)
				)
				.logout(logout -> logout.invalidateHttpSession(true).clearAuthentication(true)
						.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login?logout").permitAll())
				.build();

	}
}