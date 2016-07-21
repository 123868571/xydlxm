/**   
* 
*/

package com.paopao.hzgzf.common.process.util;


/**   
 * Copyright: Copyright (c) 2016 Asiainfo-Linkage
 * 
 * @ClassName: ProcConst.java
 * @Description: 该类的功能描述
 *
 * @version: v1.0.0
 * @author: zhoudk
 * @date: 2016-6-23 下午3:05:30 
 *
 * Modification History:
 * Date         Author          Version            Description
 *---------------------------------------------------------*
 * 2016-6-23     zdk           v1.0.0               修改原因
 */
public class ProcConst {
	
	/** 待处理 */
	public static final int EDIT = 0;
	
	/** 处理中 */
	public static final int PROCESSING = 1;
	
	/** 已通过 */
	public static final int FINISH = 2;
	
	/** 作废(驳回) */
	public static final int REJECT = 3;
	
	/** 明细处理中 */
	public static final int DETAIL_PROCESSING = 0;
	
	/** 明细通过 */
	public static final int DETAIL_PASS = 1;
	
	/** 明细驳回 */
	public static final int DETAIL_REJECT = 2;
	
	/**   
	* Copyright: Copyright (c) 2016 Asiainfo-Linkage
	* 
	* @Description: 流程实例静态属性配置
	*/
	public static interface PROCESS_TYPE {
		public static final String RESIDENT_ELEC_APPLY = "RESIDENT_ELEC_APPLY";
	}
	
	
//	public static final Map<String, String> PROCESS_TYPE_MAP;
//	
//	static {
//		PROCESS_TYPE_MAP = new HashMap<String, String>();
//		PROCESS_TYPE_MAP.put("RESIDENT_ELEC_APPLY", "RESIDENT_ELEC_APPLY");
//	}

}
