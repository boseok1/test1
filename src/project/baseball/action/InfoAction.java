package project.baseball.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import project.baseball.model.Info;
import project.baseball.repository.PlayerListRepository;

public class InfoAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int tno = Integer.parseInt(request.getParameter("tno"));
	
		PlayerListRepository playerListRepository = 
				PlayerListRepository.getInstance();
		List<Info> playerList = playerListRepository.findPlayerList(tno);
		System.out.println("InfoAction : playerList :" + playerList);
		
		Gson gson = new Gson();
		String playerListJson  = gson.toJson(playerList);
		
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");		
		
		PrintWriter out = response.getWriter();
		out.print(playerListJson);
		out.flush();
	}

}
