package cn.rfidcer.service.jinji;

import java.util.List;

import cn.rfidcer.bean.ProcessTemplate;
import cn.rfidcer.bean.ProcessTemplateItem;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.User;
import cn.rfidcer.interceptor.Page;

/**   加工模板
* @Title: SplitTemplateService.java 
* @Package cn.rfidcer.service.jinji 
* @Description:
* @author 席志明
* @Copyright Copyright
* @date 2016年7月27日 上午9:33:44 
* @version V1.0   
*/
public interface ProcessTemplateService {
	
	/**获取加工模板列表
	 * @param page
	 * @param processTemplate
	 * @return
	 */
	List<ProcessTemplate> getProcessTemplateList(Page page, ProcessTemplate processTemplate);
	
	/**新增或修改加工模板
	 * @param processTemplate
	 * @param currentUser
	 * @return
	 */
	ResultMsg createOrUpdateProcessTemplate(ProcessTemplate processTemplate, User currentUser);

	/**删除加工模板
	 * @param processTemplate
	 * @return
	 */
	ResultMsg delProcessTemplate(ProcessTemplate processTemplate);

	/**删除加工模板明细
	 * @param templateId
	 * @return
	 */
	ResultMsg delTemplateItem(String templateId);

	/**根据产品ID和规格明细ID查询模板
	 * @param processTemplate
	 * @return
	 */
	List<ProcessTemplateItem> findProcessTemplateItemsByProduct(ProcessTemplate processTemplate);

	/**根据模板ID查询模板明细
	 * @param templateId
	 * @return
	 */
	List<ProcessTemplateItem> findProcessTemplateItemsBTemplateid(String templateId);

	ProcessTemplate findTemplateByDetailId(ProcessTemplate processTemplate);

}
