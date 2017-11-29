package cn.rfidcer.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.rfidcer.bean.Processor;
import cn.rfidcer.interceptor.Page;
import tk.mybatis.mapper.common.Mapper;

/**   
* @Description:加工者dao层
* @author 席志明
* @Copyright Copyright
* @date 2016年10月26日 下午2:17:39 
* @version V1.0   
*/
public interface ProcessorMapper extends Mapper<Processor>{

	List<Processor> findAllList(Page page,@Param("processor") Processor processor);

}
