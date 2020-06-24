package project.baseball.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.baseball.model.BaseBall;
import project.baseball.repository.BallRepository;

public class BaseBallAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BallRepository repository = BallRepository.getInstance();
		List<BaseBall> teams = repository.findAll();
		request.setAttribute("teams", teams);
		System.out.println("나 잘탐?");
		RequestDispatcher dis = request.getRequestDispatcher("home.jsp");
		dis.forward(request, response);
	}

}
