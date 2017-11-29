package cn.rfidcer.dao;

import java.util.List;

import cn.rfidcer.bean.Resource;
import cn.rfidcer.bean.TreeBean;

public interface ResourceDao {

    int createResource(Resource resource);
    int updateResource(Resource resource);
    int deleteResource(Long resourceId);

    Resource findOne(Long resourceId);
    List<Resource> findAll();
    List<TreeBean> findTree();
    List<Resource> findMenus();
    
    int addResourceForRole(long roleId,long resourceId);
    
    List<Resource> findResourcesByRoleId(String roleId);
}
