package cn.rfidcer.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.rfidcer.bean.AreaInfo;
import cn.rfidcer.dao.AreaInfoMapper;
import cn.rfidcer.interceptor.Page;
import cn.rfidcer.service.AreaInfoService;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class AreaInfoServiceImpl extends BaseServiceImpl<AreaInfo> implements AreaInfoService{

	
	@Autowired
	private AreaInfoMapper areaInfoMapper;
	@Override
	public List<AreaInfo> findAll(Page page, AreaInfo AreaInfo) {
		return areaInfoMapper.findAll(page,AreaInfo);
	}
	
	
	@Override
	public List<AreaInfo> getProvinces() {
        Example example = new Example(AreaInfo.class);
        example.createCriteria().andLike("id", "%0000");
        List<AreaInfo> lst = mapper.selectByExample(example);
		return lst;
	}
	
	@Override
	public List<AreaInfo> getCitys(String proviceId) {
        Example example = new Example(AreaInfo.class);
        Criteria criteria = example.createCriteria();
        criteria = criteria.andEqualTo("province",proviceId);
        criteria = criteria.andNotEqualTo("city", "0");
        criteria = criteria.andEqualTo("area", "0");
        example.setOrderByClause("id DESC");
        example.or(criteria);
        List<AreaInfo> lst =  mapper.selectByExample(example);
		return lst;
	}
	@Override
	public List<AreaInfo> getAreas(String proviceId,String cityId) {
        Example example = new Example(AreaInfo.class);
        Criteria criteria = example.createCriteria();
        criteria = criteria.andEqualTo("province",proviceId);
        criteria = criteria.andEqualTo("city", cityId);
        criteria = criteria.andNotEqualTo("area", 0);
        example.setOrderByClause("id DESC");
        example.or(criteria);
        
        List<AreaInfo> lst = mapper.selectByExample(example);
		return lst;
	}


	@Override
	public List<AreaInfo> findAllWithQuery(Page page, AreaInfo areaInfo) {
		return areaInfoMapper.findAllWithQuery(page,areaInfo);
	}


	/* (non-Javadoc)
	 * @see cn.rfidcer.service.AreaInfoService#findAllCatgNameAndId(cn.rfidcer.interceptor.Page, cn.rfidcer.bean.AreaInfo)
	 */
	@Override
	public List<AreaInfo> findAllCatgNameAndId(Page page, AreaInfo ai) {
		return areaInfoMapper.findAllCatgNameAndId(page,ai);
	}
}
