package controller.pet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.service.ExistingException;
import model.service.PetManager;
import model.service.UserManager;
import model.Pet;
import model.User;

public class UpdatePetController implements Controller {

	private static final Logger log = LoggerFactory.getLogger(UpdatePetController.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int petId = Integer.parseInt(request.getParameter("petId"));
		if (request.getMethod().equals("GET")) {

			PetManager manager = PetManager.getInstance();
			Pet pet = manager.findPet(petId);
			request.setAttribute("pet", pet);

			String userId = request.getParameter("userId");
			UserManager userManager = UserManager.getInstance();
			String mainUser = userManager.findmainUser(userId);
			User user = userManager.findUser(mainUser);
			request.setAttribute("user", user);

			return "/pet/updatePetForm.jsp";
		}

		Pet updatePet = new Pet(petId, request.getParameter("name"), Float.parseFloat(request.getParameter("weight")),
				request.getParameter("sex"), request.getParameter("species"), request.getParameter("mainUser"));

		PetManager manager = PetManager.getInstance();
		manager.update(updatePet);

		return "redirect:/user/mypage";

	}
}