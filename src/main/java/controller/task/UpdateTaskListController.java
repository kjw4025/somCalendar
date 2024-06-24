package controller.task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.Pet;
import model.Task;
import model.service.PetManager;
import model.service.TaskManager;
import model.service.UserManager;

import java.util.Date;
import java.util.List;

public class UpdateTaskListController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(UpdateTaskListController.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int taskId = Integer.parseInt(request.getParameter("taskId"));

		if (request.getMethod().equals("GET")) {

			TaskManager manager = TaskManager.getInstance();
			Task task = manager.findTask(taskId);
			request.setAttribute("task", task);

			PetManager mn = PetManager.getInstance();
			Pet pet = mn.findPet(task.getPetId());

			request.setAttribute("pet", pet);
			return "/task/edit.jsp";

		}

		Task task = new Task(taskId, request.getParameter("name"), request.getParameter("startdate"),
				request.getParameter("enddate"), request.getParameter("place"),
				Integer.parseInt(request.getParameter("hour")), Integer.parseInt(request.getParameter("minute")),
				request.getParameter("memo"), Integer.parseInt(request.getParameter("petId")));

		TaskManager manager = TaskManager.getInstance();
		manager.update(task);

		HttpSession session = request.getSession();
		String userId = UserSessionUtils.getLoginUserId(session);
		UserManager umanager = UserManager.getInstance();
		String userpassword = umanager.findpassword(userId);

		return "redirect:/user/login?userId=" + userId + "&password=" + userpassword;
	}
}