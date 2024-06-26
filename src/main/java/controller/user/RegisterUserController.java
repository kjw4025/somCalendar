package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.User;
import model.service.ExistingException;
import model.service.UserManager;

public class RegisterUserController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(RegisterUserController.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (request.getMethod().equals("GET")) {

			return "/user/registerForm.jsp";
		}

		User user = new User(request.getParameter("userId"), request.getParameter("password"),
				request.getParameter("name"), null, null, request.getParameter("mainUser"));

		try {
			UserManager manager = UserManager.getInstance();
			manager.create(user);
			return "redirect:/user/loginForm";

		} catch (ExistingException e) {
			request.setAttribute("registerFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("user", user);
			return "/user/registerForm.jsp";
		}
	}
}
