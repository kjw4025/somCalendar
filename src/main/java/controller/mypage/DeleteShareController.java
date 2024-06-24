package controller.mypage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.User;
import model.service.ExistingException;
import model.service.PetManager;
import model.service.UserManager;

import java.util.List;

public class DeleteShareController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(DeleteShareController.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String userId = request.getParameter("userId");
		String currentId = request.getParameter("currentId");
		UserManager manager = UserManager.getInstance();
		manager.removeShare(userId, currentId);
		return "redirect:/user/mypage";
	}
}