package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.service.UserManager;
import model.service.UserNotFoundException;
import model.User;

public class ViewUserController implements Controller {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String userId = request.getParameter("userId");

		UserManager manager = UserManager.getInstance();

		User user = null;
		try {
			user = manager.findUser(userId);
		} catch (UserNotFoundException e) {
			return "redirect:/user/mypage";
		}

		request.setAttribute("user", user);
		return "/user/mapge.jsp";
	}
}
