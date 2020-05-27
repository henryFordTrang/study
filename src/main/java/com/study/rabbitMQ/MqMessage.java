package com.study.rabbitMQ;

public class MqMessage {
	private Integer command;
	private String srcId;
	private Integer fromDevId;
	private String data;
	private String sn;
	public Integer getCommand() {
		return command;
	}
	public void setCommand(Integer command) {
		this.command = command;
	}
	public String getSrcId() {
		return srcId;
	}
	public void setSrcId(String srcId) {
		this.srcId = srcId;
	}
	public Integer getFromDevId() {
		return fromDevId;
	}
	public void setFromDevId(Integer fromDevId) {
		this.fromDevId = fromDevId;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	@Override
	public String toString() {
		return "MqMessage [command=" + command + ", srcId=" + srcId + ", fromDevId=" + fromDevId + ", data=" + data
				+ ", sn=" + sn + "]";
	}
	
}
