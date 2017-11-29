package cn.rfidcer.service;

import java.util.List;

import cn.rfidcer.bean.GoodsQCMaterials;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.User;
import cn.rfidcer.interceptor.Page;

public interface GoodsQCMaterialsService {

	ResultMsg saveGoodsQC(GoodsQCMaterials goodsQCMaterials, User currentUser);

	ResultMsg updateGoodsQC(GoodsQCMaterials goodsQCMaterials, User currentUser);

	List<GoodsQCMaterials> findAllList(Page page,GoodsQCMaterials goodsQCMaterials);

	ResultMsg delteImage(String id, String url);

}
