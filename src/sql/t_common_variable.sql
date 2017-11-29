INSERT INTO `t_common_variable` VALUES ('d79725935f5745d0a38ef4a7d555fa12', '-', 'saleOrderStatus', '0', '未发货', '1', '2016-06-13 10:14:20', null, '2016-06-13 10:14:20', null, null);
INSERT INTO `t_common_variable` VALUES ('ef6c7076567c4bbb987f2e68fb135f61', '-', 'saleOrderStatus', '1', '已发货', '1', '2016-06-13 10:14:20', null, '2016-06-13 10:14:20', null, null);
INSERT INTO `t_common_variable` VALUES (lower(replace(uuid(),'-','')), '-', 'outstockType', '0', '商品出库', '1', '2016-06-23 17:01:35', null, '2016-06-23 17:01:42', null, null);
INSERT INTO `t_common_variable` VALUES (lower(replace(uuid(),'-','')), '-', 'outstockType', '1', '产品出库', '1', '2016-06-23 17:01:35', null, '2016-06-23 17:01:42', null, null);

-- barcode 地区编码; created by jie.jia at 2016-07-04 14:27
INSERT INTO `t_common_variable` 
VALUES 
(lower(replace(uuid(),'-','')), '-', 'barcode', 'areaCode', '310', 
'1', 
SYSDATE(), null, SYSDATE(), null, 
null);

-- barcode 流水号; created by jie.jia at 2016-07-04 17:30
INSERT INTO `t_common_variable` 
VALUES 
(lower(replace(uuid(),'-','')), '-', 'barcode', 'fid', '1', 
'5', 
SYSDATE(), null, SYSDATE(), null, 
null);

INSERT INTO `t_common_variable` (`varId`, `companyId`, `varGroup`, `varName`, `varValue`, `sort`, `createTime`, `createBy`, `updateTime`, `updateBy`, `remark`) VALUES ('50CD2D3B27A114E69341egf15697467E', NULL, 'getCCustomerCategories', '2', '加盟', '1', '2016-07-08 11:49:48', NULL, '2016-07-08 11:49:52', NULL, NULL);
INSERT INTO `t_common_variable` (`varId`, `companyId`, `varGroup`, `varName`, `varValue`, `sort`, `createTime`, `createBy`, `updateTime`, `updateBy`, `remark`) VALUES ('50CD2D3B27A834E690B100515697467E', NULL, 'getCCustomerCategories', '1', '自营', '1', '2016-06-30 16:29:47', NULL, '2016-06-30 16:29:49', NULL, NULL);

---添加订单已完成
INSERT INTO `t_common_variable` VALUES (lower(replace(uuid(),'-','')), '-', 'saleOrderStatus', '2', '订单已完成', '1', '2016-06-13 11:37:52', null, '2016-06-13 11:37:52', null, null);

-- 2016-09-28 添加商品等级
INSERT INTO t_common_variable 
VALUES 
(lower(replace(uuid(),'-','')), '-', 'goodsLevel', '0', '特级','0',SYSDATE(), null, SYSDATE(), null,null),
(lower(replace(uuid(),'-','')), '-', 'goodsLevel', '1', '一级','1',SYSDATE(), null, SYSDATE(), null,null),
(lower(replace(uuid(),'-','')), '-', 'goodsLevel', '2', '二级','2',SYSDATE(), null, SYSDATE(), null,null),
(lower(replace(uuid(),'-','')), '-', 'goodsLevel', '3', '三级','3',SYSDATE(), null, SYSDATE(), null,null);



