package com.jason.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
public interface ResultSetProcessor {
	Object process(ResultSet rs) throws SQLException;
}
