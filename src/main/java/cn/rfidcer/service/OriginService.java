package cn.rfidcer.service;

import java.util.List;

import cn.rfidcer.bean.Origin;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.User;
import cn.rfidcer.interceptor.Page;

public interface OriginService {

	ResultMsg addOrUpdateOrigin(Origin origin, User user);

	ResultMsg delOrigin(Origin origin, User user);

	List<Origin> getOriginList(Page page, Origin origin);

}
