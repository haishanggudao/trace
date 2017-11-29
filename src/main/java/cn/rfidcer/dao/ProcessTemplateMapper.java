package cn.rfidcer.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import tk.mybatis.mapper.common.Mapper;
import cn.rfidcer.bean.ProcessTemplate;
import cn.rfidcer.bean.ProcessTemplateItem;
import cn.rfidcer.interceptor.Page;

public interface ProcessTemplateMapper extends Mapper<ProcessTemplate>{

	/**新增加工模板
	 * @param processTemplate
	 * @return
	 */
	int createProcessTemplate(ProcessTemplate processTemplate);
	
	/**修改加工模板
	 * @param processTemplate
	 * @return
	 */
	int updateProcessTemplate(ProcessTemplate processTemplate);
	
	/**删除加工模板
	 * @param processTemplate
	 * @return
	 */
	int delProcessTemplate(ProcessTemplate processTemplate);
	
	/**查找加工模板
	 * @param page
	 * @param processTemplate
	 * @return
	 */
	List<ProcessTemplate> getProcessTemplateList(Page page, @Param("processTemplate") ProcessTemplate processTemplate);
	
	/**新增加工模板明细
	 * @param processTemplateItem
	 * @return
	 */
	int createProcessTemplateItem(ProcessTemplateItem templateItem);
	
	/**修改加工模板明细
	 * @param processTemplateItem
	 * @return
	 */
	int updateProcessTemplateItem(ProcessTemplateItem templateItem);
	
	/**删除加工模板明细
	 * @param processTemplateItem
	 * @return
	 */
	int delProcessTemplateItem(ProcessTemplateItem templateItem);

	List<ProcessTemplateItem> findProcessTemplateItemsBTemplateid(Page page, @Param("processTemplateId") String processTemplateId);
	
	/**根据产品ID和明细ID查询模板是否存在
	 * @param processTemplate
	 * @return
	 */
	int findCountByProductIdAndStandardDetailId(ProcessTemplate processTemplate);
	/**
	 * 根据模版名称查找模版是否存在
	 * @param processTemplate
	 * @return
	 */
	int findTemplateName(ProcessTemplate processTemplate);
	
	

	List<ProcessTemplateItem> findProcessTemplateItemsByProduct(ProcessTemplate processTemplate);

	List<ProcessTemplateItem> findProcessTemplateItemsByProcessId(String processId);

}
