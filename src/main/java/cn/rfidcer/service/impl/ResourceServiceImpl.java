package cn.rfidcer.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authz.permission.WildcardPermission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import cn.rfidcer.bean.Resource;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.TreeBean;
import cn.rfidcer.dao.ResourceDao;
import cn.rfidcer.dao.RoleDao;
import cn.rfidcer.service.ResourceService;

@Service
public class ResourceServiceImpl implements ResourceService{
	
	private Logger logger = LoggerFactory.getLogger(ResourceServiceImpl.class);

	@Autowired
	private ResourceDao resourceDao;
	
	@Autowired
	private RoleDao roleDao;
	
	@Override
	public int createResource(Resource resource) {
		return resourceDao.createResource(resource);
	}

	@Override
	public int addResourceForRole(long roleId, long resourceId) {
		return resourceDao.addResourceForRole(roleId, resourceId);
	}

	@Override
	public List<Resource> findResourcesByRoleId(String roleId) {
		return resourceDao.findResourcesByRoleId(roleId);
	}

	@Override
	public List<Resource> findMenus(Set<String> permissions) {
		List<Resource> allMenus = resourceDao.findMenus();
		List<Resource> menus=new ArrayList<Resource>();
		for (Resource resource : allMenus) {
			Resource tmpResource = resource.clone();
			if(!hasPermission(permissions, tmpResource)){
				continue;
			}
			List<Resource> subResources = tmpResource.getChildren();
			List<Resource> subMenus = new ArrayList<Resource>();
			for (Resource resource2 : subResources) {
				if(hasPermission(permissions, resource2)){
					subMenus.add(resource2);
				}
			}
			tmpResource.setChildren(subMenus);
			menus.add(tmpResource);
		}
		return menus;
	}
	
	private boolean hasPermission(Set<String> permissions, Resource resource) {
        if(StringUtils.isEmpty(resource.getPermission())) {
            return true;
        }
        for(String permission : permissions) {
            WildcardPermission p1 = new WildcardPermission(permission);
            WildcardPermission p2 = new WildcardPermission(resource.getPermission());
            if(p1.implies(p2) || p2.implies(p1)) {
                return true;
            }
        }
        return false;
    }

	private boolean hasPermissionNode(Set<String> permissions, TreeBean tree) {
        if(StringUtils.isEmpty(tree.getPermission())) {
            return true;
        }
        for(String permission : permissions) {
            WildcardPermission p1 = new WildcardPermission(permission);
            WildcardPermission p2 = new WildcardPermission(tree.getPermission());
            if(p1.implies(p2)) {
                return true;
            }
        }
        return false;
    }
	@Override
	public List<Resource> findAll() {
		return resourceDao.findAll();
	}

	@Override
	public List<TreeBean> findTree(Set<String> permissions) {
		List<TreeBean> findTree = resourceDao.findTree();
		checkResource(permissions, findTree);
		return findTree;
	}

	private void checkResource(Set<String> permissions, List<TreeBean> findTree) {
		for (TreeBean treeBean : findTree) {
			if(hasPermissionNode(permissions, treeBean)){
				treeBean.setChecked(true);
				continue;
			}else{
				treeBean.setChecked(false);
			}
			List<TreeBean> subResources = treeBean.getChildren();
			if(subResources!=null&&!subResources.isEmpty()){
				checkResource(permissions, subResources);
			}
		}
	}

	@Override
	public ResultMsg addResourcesForRole(long roleId, String[] resourceIds) {
		roleDao.delResourceRelation(roleId);
		int res=0;
		ResultMsg resultMsg = new ResultMsg();
		for (String resourceId : resourceIds) {
			res = resourceDao.addResourceForRole(roleId, Long.parseLong(resourceId));
		}
		resultMsg.setCode(res+"");
		if( res==1){
			resultMsg.setMsg("设置权限成功");
		}else{
			resultMsg.setMsg("设置权限失败");
		}
		return resultMsg;
	}

}
