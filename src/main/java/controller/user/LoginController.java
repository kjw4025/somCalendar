package controller.user;

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.Pet;
import model.Task;
import model.User;
import model.service.PetManager;
import model.service.TaskManager;
import model.service.UserManager;

public class LoginController implements Controller {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");

		try {
			UserManager manager = UserManager.getInstance();
			manager.login(userId, password);

			HttpSession session = request.getSession();
			session.setAttribute(UserSessionUtils.USER_SESSION_KEY, userId);
			String mainUser = manager.findmainUser(userId);

			PetManager petManager = PetManager.getInstance();
			List<Pet> pet = (List<Pet>) petManager.findPetbyUserId(mainUser);
			request.setAttribute("pet", pet);

			request.setAttribute("introduce", "날짜와 펫을 선택하세요");

			Calendar today = Calendar.getInstance();
			int year = today.get(Calendar.YEAR);
			int month = today.get(Calendar.MONTH) + 1;
			int end = today.getMaximum(Calendar.DATE);

			String startDate = String.valueOf(year) + String.valueOf(month) + "01";
			String endDate = String.valueOf(year) + String.valueOf(month) + String.valueOf(end);
			TaskManager allTaskManager = TaskManager.getInstance();
			List<Task> allTask = (List<Task>) allTaskManager.findAllTask(mainUser, startDate, endDate);
			request.setAttribute("allTask", allTask);
			request.setAttribute("year", year);
			request.setAttribute("month", month);
			return "/task/main.jsp";
		} catch (Exception e) {

			request.setAttribute("loginFailed", true);
			request.setAttribute("exception", e);
			return "/user/loginForm.jsp";
		}
	}
}
