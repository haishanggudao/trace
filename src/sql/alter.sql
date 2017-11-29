ALTER table t_company add isDeleted int(1) DEFAULT '0' COMMENT '是否删除，0是未删除，-1位已删除';
ALTER table t_product add isDeleted int(1) DEFAULT '0' COMMENT '是否删除，0是未删除，-1位已删除';
ALTER table t_goods add isDeleted int(1) DEFAULT '0' COMMENT '是否删除，0是未删除，-1位已删除';
ALTER table t_supplier add isDeleted int(1) DEFAULT '0' COMMENT '是否删除，0是未删除，-1位已删除';
ALTER table t_customers add isDeleted int(1) DEFAULT '0' COMMENT '是否删除，0是未删除，-1位已删除';
ALTER table t_product_category add isDeleted int(1) DEFAULT '0' COMMENT '是否删除，0是未删除，-1位已删除';
ALTER table t_product_standard add isDeleted int(1) DEFAULT '0' COMMENT '是否删除，0是未删除，-1位已删除';
ALTER table t_product_standard_detail add isDeleted int(1) DEFAULT '0' COMMENT '是否删除，0是未删除，-1位已删除';
ALTER table t_slaughterhouse add isDeleted int(1) DEFAULT '0' COMMENT '是否删除，0是未删除，-1位已删除';
ALTER table t_logistics add isDeleted int(1) DEFAULT '0' COMMENT '是否删除，0是未删除，-1位已删除';

ALTER table t_supplier add supplierAlias varchar(255) COMMENT '供应商别名';
ALTER table t_slaughterhouse add slaughterhouseName varchar(255) COMMENT '屠宰场别名';
ALTER table t_slaughterhouse add slaughterhouseAddress varchar(255) COMMENT '屠宰场地址';
ALTER table t_logistics add logisticsAlias varchar(255) COMMENT '物流企业别名';
ALTER table t_customers add customerAlias varchar(255) COMMENT '客户企业别名';
ALTER table t_product add marketCode varchar(32) COMMENT '超市商品编码';

-- 2016.04.20
ALTER table t_supplier add supplierAddress varchar(255) COMMENT '供应商地址';
ALTER table t_supplier add remark varchar(255) COMMENT '备注';

-- 2016.04.21
ALTER table t_slaughterhouse add remark varchar(255) COMMENT '备注';
ALTER table t_logistics add logisticsAddress varchar(255) COMMENT '物流企业地址';
ALTER table t_logistics add remark varchar(255) COMMENT '备注';
ALTER table t_customers add customerAddress varchar(255) COMMENT '客户地址';
ALTER table t_customers add remark varchar(255) COMMENT '备注';

-- 2016.05.18
ALTER table t_goods add traceCount INT(10) COMMENT '追溯次数';
ALTER table t_goods_detail add traceCount INT(10) COMMENT '追溯次数';

/*2016年5月26日 09:23:27*/
alter table t_process_template add templateName varchar(255) DEFAULT '模版名称'  COMMENT '模版名称';
alter table t_process_template add type char(1) DEFAULT '0'  COMMENT '0:加工模板；type 1:拆分模板';
alter table t_process_template add productType char(1)  COMMENT '产品类型：0（默认值，表示既是原料，又是商品），1-表示商品，2-表示原料';
alter table t_template_item add productType char(1)  COMMENT '产品类型：0（默认值，表示既是原料，又是商品），1-表示商品，2-表示原料';
alter table t_process_main add type char(1) DEFAULT '0'  COMMENT '0:加工模板；type 1:拆分模板';
alter table t_process_item add productType char(1)  COMMENT '产品类型：0（默认值，表示既是原料，又是商品），1-表示商品，2-表示原料';

-- 2016.06.02
ALTER table t_goods add deliverType char(32) DEFAULT '0' COMMENT '配送类型';
ALTER table t_outstock_item add deliveryTime datetime  COMMENT '配送时间';
ALTER table t_outstock_item add deliveryBy varchar(32)  COMMENT '配送人';

-- 2016-06-12 update for the price of goods
ALTER table t_product_standard_detail add purchasePrice        decimal(10,2) default 0.00 comment '采购价格';
ALTER table t_product_standard_detail add purchaseCurrency     varchar(32) default 'CNY' comment '采购货币';
ALTER table t_product_standard_detail add salePrice            decimal(10,2) default 0.00 comment '销售价格';
ALTER table t_product_standard_detail add saleCurrency         varchar(32) default 'CNY' comment '销售货币';
-- 2016.06.12 手持设备用户增加企业ID和类型
ALTER table client_user add companyId varchar(32) comment '企业ID';
ALTER table client_user add clientType varchar(32) comment '设备类型';

-- 2016-06-13 update the table of purchase
ALTER table t_purchase_order add registrant varchar(32) not null default '-' comment '登记人';

-- 2016-06-14 update the table of purchase and sale
ALTER table t_purchase_order add status int(1) not null default 0 comment '状态';
ALTER table t_sale_order add status int(1) not null default 0 comment '状态';

ALTER table t_common_variable add remark varchar(255) comment '备注';

--2016.06.12
ALTER table t_supplier add sregisteredperson varchar(32) COMMENT '登记人';
ALTER table t_supplier add sregiesteredtime datetime COMMENT '登记时间';
ALTER table t_supplier add isinuse int(1) DEFAULT '0' COMMENT '0:启用,1:禁用';

--2016.06.13
ALTER table t_customers add cadministrativedivision varchar(255) COMMENT '行政区域';
ALTER table t_customers add isinuse int(1) DEFAULT '0' COMMENT '0:启用,1:禁用';

--2016.06.13
ALTER table t_company add cname varchar(255) COMMENT '名称';
ALTER table t_company add cnature varchar(5) COMMENT '性质';
ALTER table t_company add cidnumb varchar(255) COMMENT '身份证号';
ALTER table t_company add ccustomercategories varchar(5) COMMENT '客户类别';
ALTER table t_company add cbusinessscope varchar(5) COMMENT '经营范围';
ALTER table t_company add cliquorbusinesslicense varchar(255) COMMENT '酒类经营许可证';
ALTER table t_company add cbusinessaddress varchar(255) COMMENT '经营地址';
ALTER table t_company add chygienelicense varchar(255) COMMENT '卫生许可证';
ALTER table t_company add clegalpersonaddress varchar(255) COMMENT '法人地址';
ALTER table t_company add cbusinesslicense varchar(255) COMMENT '营业执照';
ALTER table t_company add ceffectivedate1 datetime COMMENT '有效日期1';
ALTER table t_company add ceffectivedate2 datetime COMMENT '有效日期2';
ALTER table t_company add cregistrationnumber varchar(255) COMMENT '工商注册号';

-- 2016-06-14 资源表增加备注说明
ALTER table sys_resource add remark varchar(255) comment '备注';
-- 2016-06-15 入库主表改变入库和等级日期类型
ALTER table t_instock_main modify  instockDate datetime;
ALTER table t_instock_main modify  registDate datetime;
--2016年6月15日 18:29:21
ALTER table t_sale_item add status int(1) DEFAULT '0' COMMENT '0：未出库，-1位已出库';
--2016年6月17日 10:23:57
ALTER table t_product add publicityImageUrl varchar(500) DEFAULT '' COMMENT '宣传图片';
ALTER table t_product add publicityImageLink varchar(500) DEFAULT '' COMMENT '宣传图链接';

-- 2016-06-20 修改出库主表时间类型
ALTER table t_outstock_main modify  registDate datetime;

-- 2016-06-20 采购明细表增加字段-状态
ALTER table t_purchase_item add status int(1) not null default 0 comment '状态';

-- 2016-06-22 屠宰场信息表增加字段-屠宰方式
ALTER table t_slaughterhouse add mode varchar(48) comment '屠宰方式';

-- 2016-06-23 商品明细表增加字段-barcode
ALTER table t_goods_detail add barcode varchar(32) not null default '-' comment '条形码';

-- 2016-06-24 修改供应商备注字段
ALTER table t_supplier modify remark varchar(255) COMMENT '备注';

-- 2016-07-01 入库明细表增加采购价格字段
ALTER table t_instock_item add purchasePrice decimal(10,2) default 0.00 COMMENT '采购价格';
-- 2016年7月1日  添加销售明细单销售价格
ALTER table t_sale_item add salePrice decimal(10,2) default 0.00 comment '销售价格';
-- 2016年7月4日 添加商品出库明细中销售单ID
ALTER table t_outstock_item add saleOrderId char(32) not null default '-' comment '销售单ID';
-- 2016年7月4日 添加销售单条形码
ALTER table t_sale_order add barcode char(32) not null default '-' comment '条形码';
ALTER TABLE t_sale_order ADD UNIQUE (barcode)
UPDATE t_sale_order SET barcode=upper(replace(uuid(),'-','')) where barcode ='-'

-- 2016-07-07 零售价格  update for the price of goods
ALTER table t_product_standard_detail add retailPrice          decimal(10,2) default 0.00 comment '零售价格';
ALTER table t_product_standard_detail add retailCurrency       varchar(32) default 'CNY' comment '零售货币';

ALTER table t_product add madein varchar(255) COMMENT '产地';

-- 2016.07.14
ALTER table t_supplier add supplierCode varchar(255) COMMENT '供应商编号';
ALTER table t_customers add custCode varchar(255) COMMENT '门店编号';

-- 2016.07.18
ALTER table t_instockqc add goodsId char(32) COMMENT '商品ID';

-- 2016.07.21
ALTER table t_trace_log add qrcode varchar(255) COMMENT '二维追溯码';

-- 2016-08-03 入库明细表[t_instock_item]增加字段+出库明细Id[outstockItemId]
ALTER table t_instock_item add outstockItemId char(32) comment '出库明细ID';

-- 2016-8-4 企业表增加追溯码流水号
ALTER table t_company add traceSerialNum int default 1 COMMENT '追溯码流水号';

-- 2016-08-04 产品规格明细表增加企业ID
ALTER table t_product_standard_detail add companyId char(32) not null default '-' comment '企业ID';

-- 2016年8月19日 11:26:13 企业表增加mac编号
ALTER table t_company add macNum int default 1 COMMENT 'mac编号';

-- 2016-08-22 企业表增加宣传和图片字段
ALTER table t_company add presentation text comment '企业宣传';
ALTER table t_company add imageUrl varchar(255) comment '企业宣传图片';

-- 2016年8月24日10:46:40 产品表增加生产日期和保质期字段
ALTER table t_product add productDate datetime comment '生产日期';
ALTER table t_product add shelfLife int(4) comment '保质期';

-- 2016年9月2日 供应商增加简介
ALTER table t_supplier add supplierDesc varchar(3000) COMMENT '供应线简介';

-- 2016年9月13日 添加订单金额 
ALTER table t_sale_order add orderAmount  decimal(10,2) default 0.00 comment '实付金额';

-- 2016年9月13日 销售主单添加配送时间
ALTER table t_sale_order add deliveryTime datetime comment '配送时间';

-- 2016-09-28 商品表添加字段-等级
ALTER table t_goods add level varchar(32) default '0' comment '商品等级';

-- 2016-10-26 企业表增加字段-企业营业执照图片,字段-卫生许可证图片
ALTER table t_company add licenseUrl varchar(255) comment '企业营业执照图片';
ALTER table t_company add chygienelicenseUrl varchar(255) comment '卫生许可证图片';
-- 2016-10-27 加工模板关联加工者ID
alter table t_process_template add processorId varchar(32) comment '加工者ID';
alter table t_process_main add processorId varchar(32) comment '加工者ID';
-- 2017-03-15 门店销售单增加总价字段
alter table t_storeSale_order add salePrice decimal(10,2) comment '销售原价';
-- 2017-03-15 门店销售明细增加折扣总价字段
alter table t_storeSale_item add discountPrice decimal(10,2) comment '折扣总价';

-- 2017-03-29 门店销售明细增加退货和损耗
alter table t_storeSale_item add isRefund int(1) default 0 comment '退货标志,默认未退货';
alter table t_storeSale_item add refundAttritionQuantity decimal(10,2) comment '退到损耗的总量';
alter table t_storeSale_item add refundStockQuantity decimal(10,2) comment '退到库存的总量';
-- 2017-03-29 门店销售单增加退货标志
alter table t_storeSale_order add isRefund int(1) default 0 comment '退货标志(0表示未退货，1表示全部退货，2表示部分退货),默认0';

-- 2017-09-30 采购单增加源头（渔船）ID
ALTER TABLE `t_purchase_order`
ADD COLUMN `originId`  char(32) NULL COMMENT '源头（渔船）ID' AFTER `supplierId`;

-- 2017-10-12 入库明细表增加源头信息
ALTER table t_instock_item add originId char(32) COMMENT '源头信息';

-- 2017-10-16 出库主表修改“出库批次号”字段名
ALTER TABLE `t_outstock_main`
CHANGE COLUMN `outstockBatchNmu` `outstockBatchNum`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '出库批次号' AFTER `outstockNum`;