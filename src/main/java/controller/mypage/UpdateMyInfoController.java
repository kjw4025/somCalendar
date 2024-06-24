package controller.mypage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.user.UpdateUserController;
import controller.user.UserSessionUtils;
import model.service.UserManager;
import model.User;

public class UpdateMyInfoController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(UpdateUserController.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		if (request.getMethod().equals("GET")) {

			String updateId = request.getParameter("userId");

			UserManager manager = UserManager.getInstance();
			User user = manager.findUser(updateId);
			request.setAttribute("user", user);

			HttpSession session = request.getSession();
			if (UserSessionUtils.isLoginUser(updateId, session)) {
				return "/user/updateMyInfoForm.jsp";
			}

			request.setAttribute("updateFailed", true);
			request.setAttribute("exception", new IllegalStateException("타인의 정보는 수정할 수 없습니다."));
			return "/user/mypage.jsp";
		}

		User updateUser = new User(request.getParameter("userId"), request.getParameter("password"),
				request.getParameter("name"), request.getParameter("email"), request.getParameter("pet"),
				request.getParameter("mainUser"));

		UserManager manager = UserManager.getInstance();
		manager.update(updateUser);
		return "redirect:/user/mypage";
	}
}