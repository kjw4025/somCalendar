package controller.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;
import controller.Controller;
import controller.user.UserSessionUtils;
import model.Pet;
import model.Task;
import model.User;
import model.service.ExistingException;
import model.service.PetManager;
import model.service.TaskManager;
import model.service.UserManager;

public class AddTaskController implements Controller {

	private static final Logger log = LoggerFactory.getLogger(AddTaskController.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (request.getMethod().equals("GET")) {
			if (request.getParameter("userId") != null) {
				String userId = request.getParameter("userId");
				UserManager userManager = UserManager.getInstance();
				String mainUser = userManager.findmainUser(userId);

				PetManager petManager = PetManager.getInstance();
				List<Pet> pet = (List<Pet>) petManager.findPetbyUserId(mainUser);
				request.setAttribute("pet", pet);
			}

			return "/task/add.jsp";
		}
		Task task = new Task(request.getParameter("name"), request.getParameter("startdate").replace("/", ""),
				request.getParameter("enddate").replace("/", ""), request.getParameter("place"),
				Integer.parseInt(request.getParameter("hour")), Integer.parseInt(request.getParameter("minute")),
				request.getParameter("memo"), Integer.parseInt(request.getParameter("selectPet")));

		HttpSession session = request.getSession();
		String userId = UserSessionUtils.getLoginUserId(session);
		UserManager umanager = UserManager.getInstance();
		String userpassword = umanager.findpassword(userId);

		try {
			TaskManager manager = TaskManager.getInstance();
			manager.create(task);
			return "redirect:/user/login?userId=" + userId + "&password=" + userpassword;
			/* return "redirect:/user/dailyList/detailList.jsp"; */ // 성공 시 사용자 데일리리스트 화면으로 redirect

		} catch (ExistingException e) { // 예외 발생 시 회원가입 form으로 forwarding
			request.setAttribute("registerFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("task", task);
			return "redirect:/task/add.jsp";
		}
	}

}