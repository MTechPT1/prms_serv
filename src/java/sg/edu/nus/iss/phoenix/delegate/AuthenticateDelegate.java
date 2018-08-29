package sg.edu.nus.iss.phoenix.delegate;

import sg.edu.nus.iss.phoenix.entity.authenticate.User;
import sg.edu.nus.iss.phoenix.service.AuthenticateService;

public class AuthenticateDelegate {
	private AuthenticateService service;

	public AuthenticateDelegate() {
		super();
		service = new AuthenticateService();
	}

	public User validateUserIdPassword(User user) {
		return service.validateUserIdPassword(user);
	}

	public User evaluateAccessPreviledge(User user) {
		return service.evaluateAccessPreviledge(user);
	}
	
}
