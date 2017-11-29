package cn.rfidcer.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import cn.rfidcer.DefaultContext;

/**   
* @Title: BarcodeServiceTest.java 
* @Package cn.rfidcer.service 
* @Description: TODO(用一句话描述该文件做什么) 
* @author jie.jia
* @Copyright Copyright
* @date 2016年7月14日 下午5:11:28 
* @version V1.0   
*/
public class BarcodeServiceTest extends DefaultContext {
	
	@Autowired
	private BarcodeService barcodeService;
	
	static String[] path = new String[] { "" };  
    static Map<String, String> countMap = new Hashtable<String, String>();  
    static Map<String, String> countMap2 = new Hashtable<String, String>();  
    static Set<String> countSet = new HashSet<String>();  
    static List<String> list = new ArrayList<String>();
	/*
	@Test
	public void testExecuteBarcodeString() throws Throwable{
		
		// Runner 数组, 相当于并发多少个
		TestRunnable[] trs = new TestRunnable[10];
		
		for (int i = 0; i < 10; i++) {
			trs[i] = new ThreadA();
		}
		
		// 用于执行多线程测试用例的Runner, 将前面定义的单个Runner组成的数组传入
		MultiThreadedTestRunner mttr = new MultiThreadedTestRunner(trs);
		
		// 开发并发执行数组里定义的内容
		mttr.runTestRunnables();
		
		Thread.sleep(2000);
		
		String myListString = "--------\n[";
		for (int i = 0; i < list.size(); i++) {
			myListString += list.get(i) + ",";
		}
		myListString+="];";
		System.out.println(myListString);
		System.out.println("set size is " + countSet.size());
		System.out.println("list size is " + list.size());
	}
	
	private class ThreadA extends TestRunnable {

		@Override
		public void runTest() throws Throwable {
			// 测试内容
			myCommMethod2();  
		}
	}
	
	public String myCommMethod2(){
		System.out.println("===" + Thread.currentThread().getId() + "begin to execute myCommMethod2");
		
		String myBarcode = "";
		for (int i = 0; i < 10; i++) {
			myBarcode = barcodeService.executeBarcodeString("", "");
			countSet.add(myBarcode);
			list.add(myBarcode);
		}
		
		// System.out.print("test *** "+myBarcode);
		
		System.out.println("===" + Thread.currentThread().getId() + "end to execute myCommMethod2");
		return myBarcode;
	}
*/
}
