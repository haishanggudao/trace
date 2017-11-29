package cn.rfidcer.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.rfidcer.bean.PackageMain; 
import cn.rfidcer.interceptor.Page;

/**
 * 包装绑定信息Data Access Object
 * @author jie.jia
 *
 */
public interface PackageMainMapper {
	
	/**
	 * 删除包装绑定信息； created by jie.jia at 2016-01-12 16:51
	 * @param packageMainId
	 * @return
	 */
    int deleteByPrimaryKey(String packageMainId);
    
    /**
     * 查询包装绑定列表; created by jie.jia at 2016-01-11 16:16
     * @param page
     * @param packageMain
     * @return
     */
    List<PackageMain> findAll(Page page, @Param("packageMain") PackageMain packageMain);
    
    /**
     * 查询父类包装绑定列表；created by jie.jia at 2016-01-12 09:33
     * @param page
     * @param packageMain
     * @return
     */
    List<PackageMain> findParentPackageMains(Page page, @Param("packageMain") PackageMain packageMain);

    int insert(PackageMain record);

    /**
     * 新增包装绑定信息；created by jie.jia at 2016-01-11 17:22
     * @param record
     * @return
     */
    int insertSelective(PackageMain record);

    PackageMain selectByPrimaryKey(String packageMainId);

    /**
     * 编辑包装绑定信息；created by jie.jia at 2016-01-12 16:21
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(PackageMain record);

    int updateByPrimaryKey(PackageMain record);
}