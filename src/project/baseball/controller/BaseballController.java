package project.baseball.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.baseball.action.Action;
import project.baseball.action.BaseBallAction;
import project.baseball.action.InfoAction;
import project.baseball.action.PlayerInfoAction;

/**
 * Servlet implementation class BaseballController
 */
@WebServlet("/ball")
public class BaseballController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String TAG = "";

	public BaseballController() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("이동");
		String cmd = request.getParameter("cmd");
		System.out.println(TAG + "router : " + cmd);
		Action action = router(cmd);
		System.out.println("BaseballController : action : " + action);
		action.execute(request, response);
	}

	public Action router(String cmd) {
		if (cmd.equals("home")) {
			return new BaseBallAction();
		} else if (cmd.equals("info")) {
			return new InfoAction();
		} else if (cmd.equals("playerinfo")) {
			return new PlayerInfoAction();
		}

		return null;
	}

}
