package cn.rfidcer.util;

import cn.rfidcer.interceptor.Page;

public class PageUtil {

	public static Page getPage(int currPageIndex, int pageSize) {
		Page page=new Page();
		if(currPageIndex<1||pageSize<1){
			page.setPage(1);
			page.setRows(10);
		}else{
			page.setPage(currPageIndex);
			page.setRows(pageSize);
		}
		return page;
	}
	
}
