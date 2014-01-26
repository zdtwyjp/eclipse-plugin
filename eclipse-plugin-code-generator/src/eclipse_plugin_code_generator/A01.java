package eclipse_plugin_code_generator;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * <p>
 * Title: A01.java
 * </p>
 * <p>
 * Description:项目合同信息
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: xzjc
 * </p>
 * <p>
 * team: TibetJcTeam
 * </p>
 * <p>
 * 
 * @author: Junping.Yang
 *          </p>
 * @date 2013年11月30日上午8:54:41
 * @version 1.0
 */
@SuppressWarnings("serial")
public class A01 extends BaseEntity implements java.io.Serializable {
	private Long a01Id;

	/**
	 * 合同编号
	 */
	private Long htbh;

	/**
	 * 合同名称
	 */
	private String htmc;

	/**
	 * 档字号
	 */
	private String dzh;

	/**
	 * 合同金额
	 */
	private Double htje;

	/**
	 * 甲方单位
	 */
	private String jfdw;

	/**
	 * 甲方代表
	 */
	private String jfdb;

	/**
	 * 预计时间
	 */
	private String yjsj;

	/**
	 * 合同概述
	 */
	private String htgs;

	/**
	 * 签订时间
	 */
	private Date qdsj;

	/**
	 * 完成时间
	 */
	private Date wcsj;

	/**
	 * 合同类型
	 */
	private Long htlx;

	/**
	 * 累计付款情况
	 */
	private String ljfkqk;

	/**
	 * 备注
	 */
	private String bz;

	private Set a02s = new HashSet(0);

	private Set c02s = new HashSet(0);

	private Set a05s = new HashSet(0);

	public Long getA01Id() {
		return this.a01Id;
	}

	public void setA01Id(Long a01Id) {
		this.a01Id = a01Id;
	}

	public Long getHtbh() {
		return this.htbh;
	}

	public void setHtbh(Long htbh) {
		this.htbh = htbh;
	}

	public String getHtmc() {
		return this.htmc;
	}

	public void setHtmc(String htmc) {
		this.htmc = htmc;
	}

	public String getDzh() {
		return this.dzh;
	}

	public void setDzh(String dzh) {
		this.dzh = dzh;
	}

	public Double getHtje() {
		return this.htje;
	}

	public void setHtje(Double htje) {
		this.htje = htje;
	}

	public String getJfdw() {
		return this.jfdw;
	}

	public void setJfdw(String jfdw) {
		this.jfdw = jfdw;
	}

	public String getJfdb() {
		return this.jfdb;
	}

	public void setJfdb(String jfdb) {
		this.jfdb = jfdb;
	}

	public String getYjsj() {
		return this.yjsj;
	}

	public void setYjsj(String yjsj) {
		this.yjsj = yjsj;
	}

	public String getHtgs() {
		return htgs;
	}

	public void setHtgs(String htgs) {
		this.htgs = htgs;
	}

	public Date getQdsj() {
		return this.qdsj;
	}

	public void setQdsj(Date qdsj) {
		this.qdsj = qdsj;
	}

	public Date getWcsj() {
		return this.wcsj;
	}

	public void setWcsj(Date wcsj) {
		this.wcsj = wcsj;
	}

	public Long getHtlx() {
		return this.htlx;
	}

	public void setHtlx(Long htlx) {
		this.htlx = htlx;
	}

	public String getLjfkqk() {
		return this.ljfkqk;
	}

	public void setLjfkqk(String ljfkqk) {
		this.ljfkqk = ljfkqk;
	}

	public Set getA02s() {
		return this.a02s;
	}

	public void setA02s(Set a02s) {
		this.a02s = a02s;
	}

	public Set getC02s() {
		return this.c02s;
	}

	public void setC02s(Set c02s) {
		this.c02s = c02s;
	}

	public Set getA05s() {
		return this.a05s;
	}

	public void setA05s(Set a05s) {
		this.a05s = a05s;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}
}