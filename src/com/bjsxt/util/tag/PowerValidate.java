package com.bjsxt.util.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.bjsxt.user.vo.User;

public class PowerValidate extends TagSupport {
	private String powerNum;

	@Override
	public int doStartTag() throws JspException {
		//获取用户权限
		User user = (User) this.pageContext.getSession().getAttribute("user");
		if (user != null) {
			String power = user.getPower();
			//验证是否有权限
			if (powerNum.contains(power)) {
				return this.EVAL_BODY_INCLUDE;
			} else {
				return this.SKIP_BODY;
			}
		} else {
			return this.SKIP_BODY;
		}

	}

	public String getPowerNum() {
		return powerNum;
	}

	public void setPowerNum(String powerNum) {
		this.powerNum = powerNum;
	}

}
