package controller.pet;

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

public class DeletePetController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(DeletePetController.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int petId = Integer.parseInt(request.getParameter("petId"));
		PetManager manager = PetManager.getInstance();
		manager.remove(petId);
		return "redirect:/user/mypage";
	}
}