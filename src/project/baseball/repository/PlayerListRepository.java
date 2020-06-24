package project.baseball.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import project.baseball.db.DBConn;
import project.baseball.model.Info;

public class PlayerListRepository {
	private static final String TAG = "PlayerListRepository : ";
	private static PlayerListRepository instance = new PlayerListRepository();

	private PlayerListRepository() {
	}

	public static PlayerListRepository getInstance() {
		return instance;
	}

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	public List<Info> findPlayerList(int tno) {
		final String SQL = "SELECT dno, name FROM info WHERE tno = ? ORDER BY dno";

		List<Info> playerList = null;
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			// while 돌려서 rs -> java오브젝트에 집어넣기
			pstmt.setInt(1, tno);
			rs = pstmt.executeQuery();

			playerList = new ArrayList<>();

			while (rs.next()) {
				Info info = new Info();
				int dno = rs.getInt("dno");
				String name = rs.getString("name");
				info.setDno(dno);
				info.setName(name);

				playerList.add(info);
			}

			return playerList;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(TAG + "findplayer : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}

		return null;
	}

	public Info findInfo(int dno, String name) {
		final String SQL = "SELECT dno, name, posistion FROM info WHERE dno = ? and name = ? ORDER BY dno";

		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			// while 돌려서 rs -> java오브젝트에 집어넣기
			pstmt.setInt(1, dno);
			pstmt.setString(2, name);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				Info info = new Info();

				info.setDno(rs.getInt("dno"));
				info.setName(rs.getString("name"));
				info.setPosistion(rs.getString("posistion"));

				return info;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(TAG + "findplayer : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}

		return null;
	}
}
