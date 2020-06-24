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

public class PlayerInfoAction implements Action {
@Override
public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	String dnoStr = request.getParameter("dno");
	int dno = Integer.parseInt(dnoStr);
	
	String name = request.getParameter("name");
	
	
	PlayerListRepository playerListRepository = 
			PlayerListRepository.getInstance();
	Info playerinfo = playerListRepository.findInfo(dno,name);
	
	
	Gson gson = new Gson();
	String playerInfoJson  = gson.toJson(playerinfo);
	
	
	response.setCharacterEncoding("UTF-8");
	response.setContentType("application/json; charset=utf-8");		
	
	PrintWriter out = response.getWriter();
	out.print(playerInfoJson);
	out.flush();
	
   }
}
