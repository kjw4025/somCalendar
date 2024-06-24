package controller.task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.User;
import model.service.ExistingException;
import model.service.TaskManager;

import java.util.List;

public class DeleteTaskController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(DeleteTaskController.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int deleteId = Integer.parseInt(request.getParameter("taskId"));
		TaskManager manager = TaskManager.getInstance();

		try {
			manager.remove(deleteId);
			return "redirect:/dailyList/detailList.jsp";

		} catch (Exception e) {
			request.setAttribute("registerFailed", true);
			request.setAttribute("exception", e);
			return "/dailyList/detailList.jsp";
		}
	}
}