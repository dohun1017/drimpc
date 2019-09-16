package com.drimsys.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.drimsys.dao.inf.ComputerDAO;
import com.drimsys.dto.ComputerVO;
import com.drimsys.service.inf.ComputerService;

@Service
public class ComputerServiceImpl implements ComputerService {

	@Inject
	private ComputerDAO dao;

	@Override
	public List<ComputerVO> selectComputer() throws Exception {

		return dao.selectComputer();
	}

	// 컴퓨터 상태 수정
	@Override
	public boolean updateComputer_status_t(ComputerVO computerVO) throws Exception {
		try {
			if (dao.updateComputer_status_t(computerVO) >= 1) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}
	@Override
	public boolean updateComputer_status_f(ComputerVO computerVO) throws Exception {
		try {
			if (dao.updateComputer_status_f(computerVO) >= 1) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}
	
	// 컴퓨터 좌석 추가
	@Override
	public boolean insertComputer(ComputerVO computerVO) throws Exception {
		try {
			if(dao.insertComputer(computerVO) >= 1)
				return true;
			else
				return false;
		}catch (Exception e) {
			return false;
		}
	}
}
