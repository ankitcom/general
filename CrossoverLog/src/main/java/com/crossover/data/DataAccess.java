package com.crossover.data;

import java.util.List;

public interface DataAccess {

	public void storeLog(String userId, String userLog) throws Exception;
	public List<String> getUserLogs() throws Exception;
}
