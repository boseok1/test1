 package project.baseball.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import project.baseball.db.DBConn;
import project.baseball.model.BaseBall;


public class BallRepository {
	private static final String TAG = "BallRepository : ";
	private static BallRepository instance = new BallRepository();
	private BallRepository() {}
	public static BallRepository getInstance() {
		return instance;
	}
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	public List<BaseBall> findAll() {
		final String SQL="SELECT *  FROM BASEBALL";
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			
			// while 돌려서 rs -> java오브젝트에 집어넣기
			rs = pstmt.executeQuery();
			List<BaseBall> teamlist = new ArrayList<>();
			while(rs.next()) {	
					BaseBall bb = new BaseBall();
					int no = rs.getInt("no");
					String team =	rs.getString("team");
					bb.setNo(no);
					bb.setTeam(team);
					teamlist.add(bb);
			}			
			return teamlist;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG+"findAll(page) : "+e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}

		return null;
	}
}

