package com.paopao.hzgzf.modules.dlj.entity;

public interface ICheckData {
	
	public static final int CHECK_STATUS_0 = 0;
	public static final int CHECK_STATUS_1 = 1;
	
	public static final int CHECK_VERIFY_0 = 0;
	public static final int CHECK_VERIFY_1 = 1;
	/**   
	* @Function: getCheckStatus
	* @Description: 获取是否审核的状态 0 未审核  1 已审核
	*/
	public int getCheckStatus();
	
	/**   
	 * @Function: getCheckStatus
	 * @Description: 获取审核结果  0 未通过 1 通过
	 */
	public int getCheckVerify();
	
	/**   
	 * @Function: toCheckContentJson
	 * @Description: 审核结果转换成json字符串
	 */
	public String toCheckContentJson();
}
