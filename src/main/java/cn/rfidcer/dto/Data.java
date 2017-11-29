package cn.rfidcer.dto;

public class Data<T> {

	/**
	 * 数据
	 */
	private T data;

	
	
	public Data() {
	}

	public Data(T data) {
		this.data = data;
	}

	/**获取数据
	 * @return the 数据
	 */
	public T getData() {
		return data;
	}

	/**设置数据
	 * @param data the 数据 to set
	 */
	public void setData(T data) {
		this.data = data;
	}

	
}
