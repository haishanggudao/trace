package cn.rfidcer.interceptor;

/**分页对象
 * @author xzm
 *
 */
public class Page {

	private int page;//当前页码
	private int rows;//每页显示条数
	private int totalPage;//总页数
	private int totalNum;//总记录数
	
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}
	
	public int getOffset(){
		return (page-1)*rows;
	}
	public boolean isEmpty(){
		return rows==0?true:false;
	}
	@Override
	public String toString() {
		return "Page [page=" + page + ", rows=" + rows + ", totalPage=" + totalPage + ", totalNum=" + totalNum + "]";
	}
	
}
