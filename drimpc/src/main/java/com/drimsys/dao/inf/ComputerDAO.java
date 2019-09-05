package com.drimsys.dao.inf;

import java.util.List;

import com.drimsys.dto.ComputerVO;

public interface ComputerDAO {

	public List<ComputerVO> selectComputer() throws Exception;

	// 로그인 관련
	public ComputerVO selectComputerUsing(ComputerVO computerVO) throws Exception;

	public int updateComputerUsing(ComputerVO computerVO) throws Exception;

	// 컴퓨터 활성화, 비활성화 관련
	public int updateComputer_status(ComputerVO computerVO) throws Exception;

	// 좌석 추가
	public int insertComputer(ComputerVO computerVO) throws Exception;
}
