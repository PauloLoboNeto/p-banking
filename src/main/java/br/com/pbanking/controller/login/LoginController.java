package br.com.pbanking.controller.login;

import br.com.pbanking.controller.login.requests.UserRequest;
import br.com.pbanking.controller.login.response.DoLoginResponse;
import br.com.pbanking.exception.SenhaInvalidaException;
import br.com.pbanking.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class LoginController {

	@Autowired
	private LoginService loginService;

	@PostMapping("/doLogin")
	public ResponseEntity<DoLoginResponse> doLogin(@RequestBody UserRequest user){
		try {
			return ResponseEntity.ok().body(this.loginService.doLogin(user, ((ServletRequestAttributes) RequestContextHolder
					.getRequestAttributes()).getRequest()));
		} catch (UsernameNotFoundException | SenhaInvalidaException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new DoLoginResponse());
		}
	}

	@GetMapping("/doLogout")
	public ResponseEntity<String> doLogout(){
		String token = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest().getHeader("Authorization");
		this.loginService.doLogout(token.substring(6));
		return ResponseEntity.ok().body("OK!");
	}

}
