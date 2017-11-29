package cn.rfidcer.dao;

import org.springframework.beans.factory.annotation.Autowired;

import cn.rfidcer.bean.Resource;
import cn.rfidcer.dao.ResourceDao;
import cn.rfidcer.enums.ResourceType;

public class ResourceDaoTest{

	@Autowired
	private ResourceDao resourceDao;
	
	public void createResource(){
		Resource resource = new Resource();
		resource.setName("用户管理");
		resource.setParentId(new Long(1));
		resource.setType(ResourceType.menu);
		resource.setAvailable(Boolean.TRUE);
		resource.setPermission("system:user:*");
		resourceDao.createResource(resource);
	}
	
	public void addResourceForRole(){
		resourceDao.addResourceForRole(4, 1);
		resourceDao.addResourceForRole(4, 2);
	}
}
