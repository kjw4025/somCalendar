package controller.pet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.Pet;
import model.service.ExistingException;
import model.service.PetManager;

public class RegisterPetController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(RegisterPetController.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (request.getMethod().equals("GET")) {

			return "/pet/registerPetForm.jsp ";
		}

		Pet pet = new Pet(request.getParameter("name"), Float.parseFloat(request.getParameter("weight")),
				request.getParameter("sex"), request.getParameter("species"), request.getParameter("mainUser"));

		try {
			PetManager manager = PetManager.getInstance();
			manager.create(pet);

			return "redirect:/user/mypage";
		} catch (ExistingException e) {
			request.setAttribute("registerFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("pet", pet);
			return "redirect:/pet/registerPetForm.jsp";
		}
	}
}