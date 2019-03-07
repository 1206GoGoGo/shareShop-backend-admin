package whut.service;


import whut.pojo.WithdrawRecord;
import whut.pojo.YieldDetail;
import whut.utils.ResponseData;

public interface SellerBillService {

	public ResponseData getList(String id);

	public ResponseData addWithdraw(WithdrawRecord withdrawRecord);

	public ResponseData addYield(YieldDetail yieldDetail);

	public ResponseData getWithdrawList(String id);

	public ResponseData getYieldList(String id);

}
