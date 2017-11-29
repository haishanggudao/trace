package cn.rfidcer.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.rfidcer.bean.GoodsQCMaterials;
import cn.rfidcer.interceptor.Page;
import tk.mybatis.mapper.common.Mapper;

public interface GoodsQCMaterialsMapper extends Mapper<GoodsQCMaterials>{

	List<GoodsQCMaterials> findAllList(Page page,@Param("goodsQC")GoodsQCMaterials goodsQCMaterials);

}
