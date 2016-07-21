package com.paopao.hzgzf.modules.dlj.entity.interfaces;

/**   
* Copyright: Copyright (c) 2016 Asiainfo-Linkage
* 
* @ClassName: IProcessObject.java
* @Description: 审核对象实现接口
*
* @version: v1.0.0
* @author: zhoudk
* @date: 2016-6-24 上午11:07:27 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2016-6-24     zdk           v1.0.0               修改原因
*/
public interface IProcessObject {
	
	/**   
	* @Function: checkPass
	* @Description: 审核通过
	*/
	public void checkPass();
	
	/**   
	* @Function: checkReject
	* @Description: 审核驳回
	*/
	public void checkReject();
}
