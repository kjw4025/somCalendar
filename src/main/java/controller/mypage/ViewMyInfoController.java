package controller.mypage;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.service.PetManager;
import model.service.UserManager;
import model.service.UserNotFoundException;
import model.Pet;
import model.User;

public class ViewMyInfoController implements Controller {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		if (!UserSessionUtils.hasLogined(request.getSession())) {
			return "redirect:/user/login/form";
		}

		UserManager manager = UserManager.getInstance();
		String userId = request.getParameter("userId");
		User user = null;

		if (userId == null) {
			HttpSession session = request.getSession();
			userId = UserSessionUtils.getLoginUserId(session);
		}

		String mainUser = manager.findmainUser(userId);
		String uId = null;
		if (!mainUser.equals(userId)) {
			uId = mainUser;
		} else {
			uId = userId;
		}

		PetManager petManager = PetManager.getInstance();
		List<Pet> pet = (List<Pet>) petManager.findPetbyUserId(uId);
		request.setAttribute("pet", pet);

		List<String> shareUser = (List<String>) manager.findShareUser(uId);
		request.setAttribute("shareUser", shareUser);

		try {
			user = manager.findUser(userId);
		} catch (UserNotFoundException e) {
			return "redirect:/user/login";
		}

		request.setAttribute("user", user);
		return "/user/mypage.jsp";
	}
}
