package com.shp.exam.exam2.repository;

import com.shp.exam.exam2.container.ContainerComponent;
import com.shp.exam.exam2.dto.Like;
import com.shp.mysqliutil.MysqlUtil;
import com.shp.mysqliutil.SecSql;

public class LikeRepository implements ContainerComponent {

	@Override
	public void init() {
		
	}

	public int getPoint(String relTypeCode, int relId, int memberId) {
		SecSql sql = new SecSql();
		sql.append("SELECT IFNULL(SUM(L.point), 0) AS `point`");
		sql.append("FROM `like` AS L");
		sql.append("WHERE 1");
		sql.append("AND L.relTypeCode = ?", relTypeCode);
		sql.append("AND L.relId = ?", relId);
		sql.append("AND L.memberId = ?", memberId);

		return MysqlUtil.selectRowIntValue(sql);
	}

	public Like getLikeByRelTypeCodeAndMemberId(String relTypeCode, int relId, int memberId) {
		SecSql sql = new SecSql();
		sql.append("SELECT L.*");
		sql.append("FROM `like` AS L");
		sql.append("WHERE L.relTypeCode = ?", relTypeCode);
		sql.append("AND L.relId = ?", relId);
		sql.append("AND L.memberId = ?", memberId);
		sql.append("AND L.point = 1");
		
		return MysqlUtil.selectRow(sql, Like.class);
	}

	public void likeInsert(String relTypeCode, int relId, int memberId) {
		SecSql sql = new SecSql();
		sql.append("INSERT INTO `like`");
		sql.append("SET regDate = NOW()");
		sql.append(", updateDate = NOW()");
		sql.append(", relTypeCode = ?", relTypeCode);
		sql.append(", relId = ?", relId);
		sql.append(", memberId = ?", memberId);
		sql.append(", `point` = 1");

		MysqlUtil.insert(sql);
	}

	public void likeDelete(String relTypeCode, int relId, int memberId) {
		SecSql sql = new SecSql();
		sql.append("DELETE FROM `like`");
		sql.append("WHERE relTypeCode = ?", relTypeCode);
		sql.append("AND relId = ?", relId);
		sql.append("AND memberId = ?", memberId);
		sql.append("AND `point` = 1");

		MysqlUtil.delete(sql);
	}

	public Like getDisLikeByRelTypeCodeAndMemberId(String relTypeCode, int relId, int memberId) {
		SecSql sql = new SecSql();
		sql.append("SELECT L.*");
		sql.append("FROM `like` AS L");
		sql.append("WHERE L.relTypeCode = ?", relTypeCode);
		sql.append("AND L.relId = ?", relId);
		sql.append("AND L.memberId = ?", memberId);
		sql.append("AND L.point = -1");
		
		return MysqlUtil.selectRow(sql, Like.class);
	}

	public void disLikeInsert(String relTypeCode, int relId, int memberId) {
		SecSql sql = new SecSql();
		sql.append("INSERT INTO `like`");
		sql.append("SET regDate = NOW()");
		sql.append(", updateDate = NOW()");
		sql.append(", relTypeCode = ?", relTypeCode);
		sql.append(", relId = ?", relId);
		sql.append(", memberId = ?", memberId);
		sql.append(", `point` = -1");

		MysqlUtil.insert(sql);
		
	}

	public void disLikeDelete(String relTypeCode, int relId, int memberId) {
		SecSql sql = new SecSql();
		sql.append("DELETE FROM `like`");
		sql.append("WHERE relTypeCode = ?", relTypeCode);
		sql.append("AND relId = ?", relId);
		sql.append("AND memberId = ?", memberId);
		sql.append("AND `point` = -1");

		MysqlUtil.delete(sql);
	}

	public void likeUpdate(String relTypeCode, int relId, int memberId) {
		SecSql sql = new SecSql();
		sql.append("UPDATE `like`");
		sql.append("SET updateDate = NOW()");
		sql.append(", `point` = -1");
		sql.append("WHERE relTypeCode = ?", relTypeCode);
		sql.append("AND relId = ?", relId);
		sql.append("AND memberId = ?", memberId);

		MysqlUtil.update(sql);
	}

	public void disLikeUpdate(String relTypeCode, int relId, int memberId) {
		SecSql sql = new SecSql();
		sql.append("UPDATE `like`");
		sql.append("SET updateDate = NOW()");
		sql.append(", `point` = 1");
		sql.append("WHERE relTypeCode = ?", relTypeCode);
		sql.append("AND relId = ?", relId);
		sql.append("AND memberId = ?", memberId);

		MysqlUtil.update(sql);
	}

}
