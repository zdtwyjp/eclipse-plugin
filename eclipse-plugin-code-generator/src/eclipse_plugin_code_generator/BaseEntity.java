/**
 * Copyright (c) 2013 XZJC, Inc. All rights reserved. This software is the confidential and proprietary information of XZJC, Inc. You shall
 * not disclose such Confidential Information and shall use it only in accordance with the terms of the license agreement you entered into
 * with XZJC.
 */
package eclipse_plugin_code_generator;

import java.util.Date;

/**
 * <p>
 * Title: BaseEntity.java
 * </p>
 * <p>
 * Description:基础实体类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: xzjc
 * </p>
 * <p>
 * team: XzjcTeam
 * </p>
 * <p>
 * 
 * @author: Junping.Yang
 *          </p>
 * @date 2013年11月27日下午3:51:46
 * @version 1.0
 */
@SuppressWarnings("serial")
public class BaseEntity implements java.io.Serializable {
	/**
	 * 创建时间
	 */
	private Date cjsj;

	/**
	 * 更新时间
	 */
	private Date gxsj;

	public Date getCjsj() {
		return cjsj;
	}

	public void setCjsj(Date cjsj) {
		this.cjsj = cjsj;
	}

	public Date getGxsj() {
		return gxsj;
	}

	public void setGxsj(Date gxsj) {
		this.gxsj = gxsj;
	}
}
