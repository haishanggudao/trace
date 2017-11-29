package cn.rfidcer.service;

import java.util.List;

import cn.rfidcer.bean.ProcessTemplate;
import cn.rfidcer.bean.ProcessTemplateItem;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.User;
import cn.rfidcer.interceptor.Page;

public interface SplistTemplateService {

	
	/**新增或修改拆分模板
	 * @param processTemplate
	 * @param user
	 * @return
	 */
	ResultMsg createOrUpdateSplitTemplate(ProcessTemplate processTemplate, User user);
	
	/**删除拆分模板
	 * @param processTemplate
	 * @return
	 */
	ResultMsg delSplitTemplate(ProcessTemplate processTemplate);
	
	/**删除拆分模板明细
	 * @param templateId
	 * @return
	 */
	ResultMsg delTemplateItem(String templateId);
	
	/**查询拆分模板
	 * @param page
	 * @param processTemplate
	 * @return
	 */
	List<ProcessTemplate> getSplitTemplateList(Page page, ProcessTemplate processTemplate);

	/**根据模板ID查询模板明细
	 * @param page
	 * @param templateId
	 * @return
	 */
	List<ProcessTemplateItem> findProcessTemplateItemsByTemplateid(Page page, String templateId);
}
