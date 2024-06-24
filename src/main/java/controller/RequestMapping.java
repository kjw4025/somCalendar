package controller;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.mypage.DeleteShareController;
import controller.mypage.ViewMyInfoController;
import controller.pet.RegisterPetController;
import controller.pet.DeletePetController;
import controller.pet.UpdatePetController;
import controller.task.AddTaskController;
import controller.task.DeleteTaskController;
import controller.task.SelectDataInMainController;
import controller.task.UpdateTaskListController;
import controller.task.ViewTaskController;
import controller.user.*;

public class RequestMapping {
	private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);

	private Map<String, Controller> mappings = new HashMap<String, Controller>();

	public void initMapping() {

		mappings.put("/user/loginForm", new ForwardController("/user/loginForm.jsp"));
		mappings.put("/user/logout", new LogoutController());
		mappings.put("/user/main", new ForwardController("/task/main.jsp"));
		mappings.put("/user/mypage", new ViewMyInfoController());
		mappings.put("/user/invite", new ForwardController("/user/invite.jsp"));
		mappings.put("/user/checkUser", new ForwardController("/user/checkUser.jsp"));
		mappings.put("/user/checkMain", new ForwardController("/user/checkMain.jsp"));
		mappings.put("/user/delete", new DeleteShareController());
		mappings.put("/user/login", new LoginController());
		mappings.put("/user/register", new RegisterUserController());
		mappings.put("/user/update", new UpdateUserController());

		mappings.put("/pet/update", new UpdatePetController());
		mappings.put("/pet/register", new RegisterPetController());
		mappings.put("/pet/registerfirst", new ForwardController("/pet/registerPetForm.jsp"));
		mappings.put("/pet/updatefirst", new ForwardController("/pet/updatePetForm.jsp"));
		mappings.put("/pet/delete", new DeletePetController());

		mappings.put("/task/add", new AddTaskController());
		mappings.put("/task/addfirst", new ForwardController("/task/add.jsp"));
		mappings.put("/task/delete", new DeleteTaskController());
		mappings.put("/task/update", new UpdateTaskListController());
		mappings.put("/task/view", new ViewTaskController());

		mappings.put("/task/select", new SelectDataInMainController());
	}

	public Controller findController(String uri) {
		return mappings.get(uri);
	}
}
