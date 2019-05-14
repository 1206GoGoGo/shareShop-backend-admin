package whut.service;


import org.springframework.web.bind.annotation.RequestBody;

import whut.pojo.OrderDetail;
import whut.pojo.OrderMaster;
import whut.utils.ResponseData;

public interface MemberOrderService {

	public ResponseData getListByUser(Integer pageindex, Integer pagesize, int id);

	public ResponseData getListSearch(Integer pageindex, Integer pagesize, Integer status, String orderNumber, String username, 
			String consignee, String timeBe, String timeEn);

	public ResponseData getListByPro(Integer pageindex, Integer pagesize, int id);

	public ResponseData getDetailListByOrderId(int orderId);
	
	public ResponseData getDetail(int id);

	public ResponseData modifyOrder(OrderMaster orderMaster);

	public ResponseData modifyPro(OrderDetail orderDetail);

	public ResponseData modifyOrderStatus(@RequestBody String jsonString);

	public ResponseData modifyProStatus(@RequestBody String jsonString);

	public ResponseData getRecordByUser(Integer pageindex, Integer pagesize, String user, String timebe, String timeen);

	public ResponseData getCountWeekOrYear(int type);

	public ResponseData getCountWeekOrYearForOnePro(int type, int proId);

	public ResponseData getCountForOneClass(int cateId);

	public ResponseData getCountClassForOneGood(int cateId, Integer pageindex, Integer pagesize, String timeBe, String timeEn);
}
