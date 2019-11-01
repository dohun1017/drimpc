package com.drimsys.scheduled;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.drimsys.dto.ProductVO;
import com.drimsys.service.inf.SalesService;

@Service
public class DaySales {

	@Inject
	SalesService sales_service;

//	@Scheduled(cron = "0 0 0 * * ?")
	@Scheduled(cron = "0 0/1 * * * ? ")
	public void Print() throws Exception {
		SimpleDateFormat fmDate = new SimpleDateFormat("yyyy-MM-dd");

		Calendar time = Calendar.getInstance();
		String end_date = fmDate.format(time.getTime());

		time.add(Calendar.DATE, -1);
		String start_date = fmDate.format(time.getTime());
		System.out.print(start_date + " 판매 금액 : ");
		end_date = end_date + " " + "00:00";
		start_date = start_date + " " + "00:00";

		ProductVO productVO = new ProductVO();
		productVO.setEnd_date(end_date);
		productVO.setStart_date(start_date);
//		productVO.setEnd_date("2019-09-26 00:00");
//		productVO.setStart_date("2019-09-25 00:00");
		productVO = sales_service.salesDate(productVO);
		if(productVO == null)
			System.out.println("판매 내역 없음");
		else
			System.out.println(productVO.getJoin_date_price());
	}
}
