package cn.rfidcer.service.impl.yz;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.rfidcer.bean.yz.StoreSaleItem;
import cn.rfidcer.dao.yz.StoreSaleItemMapper;
import cn.rfidcer.interceptor.Page;
import cn.rfidcer.service.yz.StoreSaleItemService;

@Service
public class StoreSaleItemServiceImpl implements StoreSaleItemService {
	
	private Logger logger = LoggerFactory.getLogger(StoreSaleItemServiceImpl.class);
	
	@Autowired
	private StoreSaleItemMapper storeSaleItemMapper;

	@Override
	public List<StoreSaleItem> findAll(Page page, StoreSaleItem storeSaleItem) {
		return storeSaleItemMapper.findAll(page, storeSaleItem);
	}

}
