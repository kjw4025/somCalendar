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
import model.User;
import model.service.ExistingException;
import model.service.PetManager;
import model.service.TaskManager;
import model.service.UserManager;

import java.util.Calendar;
import java.util.List;

public class SelectDataInMainController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(SelectDataInMainController.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int petId = 0;
		String userId = null;
		String date = null;
		int year = 0;
		int month = 0;
		String startDate = null;
		String endDate = null;

		System.out.println("ºø∑∫∆Æ¥Ÿ cPet: " + request.getParameter("calendarPet"));
		System.out.println("ºø∑∫∆Æ¥Ÿ petId: " + request.getParameter("selectPet"));
		System.out.println("ºø∑∫∆Æ¥Ÿ userId: " + request.getParameter("userId"));
		System.out.println("ºø∑∫∆Æ¥Ÿ petId: " + request.getParameter("selectPet"));
		System.out.println("ºø∑∫∆Æ¥Ÿ date: " + request.getParameter("date"));
		System.out.println("ºø∑∫∆Æ¥Ÿ year: " + request.getParameter("year"));
		System.out.println("ºø∑∫∆Æ¥Ÿ month: " + request.getParameter("month"));
		System.out.println("ºø∑∫∆Æ¥Ÿ syear: " + request.getParameter("syear"));
		System.out.println("ºø∑∫∆Æ¥Ÿ smonth: " + request.getParameter("smonth"));
		try {

			if (request.getParameter("userId") != null) {
				userId = request.getParameter("userId");

				UserManager manager = UserManager.getInstance();
				String mainUser = manager.findmainUser(userId);

				if (mainUser.equals(userId)) {

				} else {
					userId = mainUser;
				}

				PetManager petManager = PetManager.getInstance();
				List<Pet> pet = (List<Pet>) petManager.findPetbyUserId(userId);
				request.setAttribute("pet", pet);
				request.setAttribute("introduce", request.getParameter("introduce"));

				year = Integer.parseInt(request.getParameter("year"));
				month = Integer.parseInt(request.getParameter("month"));

				Calendar cal = Calendar.getInstance();

				if (request.getParameter("decreaseYear") != null) {
					year = year - 1;
				}
				if (request.getParameter("increaseYear") != null) {
					year = year + 1;
				}
				if (request.getParameter("decreaseMonth") != null) {
					if (month - 1 == 0) {
						month = 12;
						year = year - 1;
					} else {
						month = month - 1;
					}
				}
				if (request.getParameter("increaseMonth") != null) {
					if (month + 1 == 13) {
						month = 1;
						year = year + 1;
					} else {
						month = month + 1;
					}
				}

				request.setAttribute("syear", String.valueOf(year));
				request.setAttribute("smonth", String.valueOf(month));
				request.setAttribute("year", year);
				request.setAttribute("month", month);

				cal.set(year, month - 1, 1);

				if (month < 10) {
					startDate = String.valueOf(year) + "0" + String.valueOf(month) + "01";
					endDate = String.valueOf(year) + "0" + String.valueOf(month)
							+ String.valueOf(cal.getActualMaximum(Calendar.DAY_OF_MONTH));
				} else {
					startDate = String.valueOf(year) + String.valueOf(month) + "01";
					endDate = String.valueOf(year) + String.valueOf(month)
							+ String.valueOf(cal.getActualMaximum(Calendar.DAY_OF_MONTH));
				}

				TaskManager allTaskManager = TaskManager.getInstance();
				List<Task> allTask = (List<Task>) allTaskManager.findAllTask(userId, startDate, endDate);
				request.setAttribute("allTask", allTask);

				if (request.getParameter("userId") != null && request.getParameter("date") != null
						&& request.getParameter("selectPet") != null) {

					petId = Integer.parseInt(request.getParameter("selectPet"));
					date = request.getParameter("date");
					TaskManager taskManager = TaskManager.getInstance();
					List<Task> task = (List<Task>) taskManager.findTaskList(userId, petId, date);
					request.setAttribute("task", task);
					request.setAttribute("date", request.getParameter("date"));
					request.setAttribute("introduce", petManager.findPetName(petId) + "¿« " + date + " ¿œ¡§");
					request.setAttribute("selectPet", request.getParameter("selectPet"));

				}

			}

			return "/task/main.jsp";

		} catch (Exception e) {
			request.setAttribute("registerFailed", true);
			request.setAttribute("exception", e);
			return "/task/main.jsp";
		}
	}
}