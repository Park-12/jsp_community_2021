package com.jhs.exam.exam2.repository;

import com.jhs.exam.exam2.container.ContainerComponent;
import com.jhs.mysqliutil.MysqlUtil;
import com.jhs.mysqliutil.SecSql;

public class LikeRepository implements ContainerComponent {

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
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
	

}
