package com.paopao.hzgzf.modules.pay.common;

public class PaymentConst {
	
	//帐户类型
	public static interface ACCT_TYPE{
		String PERSONAL = "1";//个人帐户
		String UNIFY = "2";//统一付费帐户
	}
	
	//付款方式
	public static interface PAY_METHOD{
		String CASH = "1";//现金
		String PAY_AT_BANK = "2";//银行代收
		String IMPORT = "3";//批量缴费
	}
	
	//余额操作类型
	public static interface OPER_TYPE{
		String INCOME = "1";//收入
		String EXPENSE = "2";//支出
	}
	
	//缴费状态
	public static interface PAYMENT_STATE{
		String PAYED = "1";//缴费
		String REVERSED = "2";//冲正
	}
	
	//专款专用项
	public static interface SPECIAL_PAYMENT{
		int HOUSE_RENT = 101;//房租费
		int MANAGEMENT = 102;//物业费
		int CONSUMPTION = 103;//能耗费
		int WATER = 104;//水费
		int ELECTRICITY = 105;//电费
		int NATURALGAS = 106;//天燃气费
		int DEPOSIT = 107;//押金费
		int NO_SPECIAL = -1;//自由费用
		int REPAIR_FEE = 108;//设备维修费
	}
	
	//费用科目
	public static interface ACCT_ITEM_TYPE{
		int HOUSE_RENT = 1;//房租费
		int MANAGEMENT = 2;//物业费
		int CONSUMPTION = 3;//能耗费
		int WATER = 4;//水费
		int ELECTRICITY = 5;//电费
		int NATURALGAS = 6;//天燃气费
		int DEPOSIT = 7;//押金费
		int FREE_FEE = 0;//自由费用
		int REPAIR_FEE = 8;//设备维修费
	}
	
	//帐本余额类型
	public static interface BALANCE_TYPE{
		int ALLOW_ALL = 1;//可退可销帐且提供发票
		int FORBID_DRAW = 2;//不可退可销帐提供发票
	}
	
	public static interface YES_OR_NO{
		String YES = "0";
		String NO = "1";
	}
	
	//操作编码
	public static interface OPT_CODE{
		String PAY = "1";//充值
		String WRITE_OFF_RENT = "21";//销帐（房租）
		String WRITE_OFF_WATER = "22";//销帐（水费）
		String WRITE_OFF_ELECTRICITY = "23";//销帐（电费）
		String WRITE_OFF_NATURALGAS = "24";//销帐（天燃气费）
		String WRITE_OFF_MANAGEMENT = "25";//销帐（物业费）
		String WRITE_OFF_CONSUMPTION = "26";//销帐（能耗费）
		String WRITE_OFF_REPAIR = "27";//销帐（设备维修费）
		String TRANS_HOUSE_RENT_FEE = "3";//转房租
		String TRANS_WATER_FEE = "4";//转水费
		String TRANS_ELECTRICITY_FEE = "5";//转电费
		String TRANS_NATURALGAS_FEE = "6";//转天燃气费
		String TRANS_MANAGEMENT_FEE = "9";//转物业费
		String REVERSE = "7";//冲正
		String REFUND = "8";//退费
	}
	
	//渠道
	public static interface CHANNEL_TYPE{
		String OFFICE = "1";//营业厅
		String APP = "2";//app客户端
	}
	
	//订单处理状态
	public static interface PROCESS_STATUS{
		String UN_PROCESS = "U";//未处理
		String FINISHED = "F";//已处理
		String EXCEPT = "E";//处理异常
	}
}
