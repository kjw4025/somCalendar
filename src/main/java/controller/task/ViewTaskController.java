package controller.task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Pet;
import model.Task;
import model.service.PetManager;
import model.service.TaskManager;

import java.util.List;

public class ViewTaskController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Task task = null;
		TaskManager manager = TaskManager.getInstance();
		int taskId = Integer.parseInt(request.getParameter("taskId"));
		task = manager.findTask(taskId);

		Pet pet = null;
		PetManager mn = PetManager.getInstance();
		pet = mn.findPet(task.getPetId());

		request.setAttribute("task", task);
		request.setAttribute("pet", pet);
		return "/task/detail.jsp";
	}
}