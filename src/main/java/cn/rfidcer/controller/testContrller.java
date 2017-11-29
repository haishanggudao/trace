package cn.rfidcer.controller;

import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.session.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.rfidcer.bean.TestBean;
import cn.rfidcer.dao.TestMapper;
import cn.rfidcer.util.UUIDGenerator;
import tk.mybatis.mapper.entity.Example;

@Controller
@RequestMapping("/test")
public class testContrller {
    @Autowired
    private TestMapper mapper;
    
	@RequestMapping(method=RequestMethod.GET)
	public String index(){
		
		
        TestBean  test = new TestBean();
        test.setProductCode(UUIDGenerator.generatorUUID());
        test.setProductName(""+UUIDGenerator.generatorUUID());
        //test.setProductShortName(UUIDGenerator.generatorUUID());
        test.setProductId("206b80ad2ef94e1b993a0bfe8cacc5ff");
        
        
        
        System.out.println(test);
        Example example = new Example(TestBean.class);
        example.createCriteria().andEqualTo("productId", "206b80ad2ef94e1b993a0bfe8cacc5ff");
        
	
		return "";
	}
	
}
