create table t_trace_column
(
   companyCode            varchar(32) comment '企业编码',
   traceType        char(32) comment '追溯信息类型',
   traceColumns     varchar(255) comment '追溯对应表的列名',
   createTime           datetime,
   createBy             varchar(32),
   updateTime           datetime,
   updateBy             varchar(32),
   primary key (companyCode,traceType)
)
COMMENT = '追溯字段表';

create table t_trace_log
(
   traceLogId            char(32) comment 'ID',
   IPAddress        varchar(15) comment 'IP地址',
   kernel			varchar(255) comment '浏览器内核',
   country     varchar(32) comment '国家',
   province     varchar(32) comment '省',
   city           varchar(32) comment '城市',
   area           varchar(32) comment '地区',
   isp           varchar(32) comment '运营商',
   createTime           datetime,
   primary key (traceLogId)
)
COMMENT = '追溯日志表';

-- 2016-06-14
create table t_common_variable
(
   varId                char(32) not null comment 'pk',
   companyId            char(32) not null comment '所属公司ID',
   varGroup             varchar(32) not null comment '分组类别',
   varName              varchar(32) not null comment '键',
   varValue             varchar(32) not null comment '值',
   sort                 int(1) not null default 0 comment '排序', 
   createTime           datetime,
   createBy             varchar(32),
   updateTime           datetime,
   updateBy             varchar(32),
   primary key (varId)
)
COMMENT = '通用参数表';

-- 销售出库关系表
create table t_saleOutstock
(
   outstockMainId       char(32) not null comment '出库单ID',
   saleOrderId          char(32) not null comment '销售单ID',
   createTime           datetime comment '创建时间',
   createBy             varchar(32) comment '创建人',
   updateTime           datetime comment '修改时间',
   updateBy             varchar(32) comment '修改人',
   isDeleted            int(1) default 0 comment '0-正常 1-已删除',
   primary key (outstockMainId, saleOrderId)
);

create table t_company_page
(
   companyId        char(32) comment '企业ID',
   url            varchar(255) comment 'URL',
   page     varchar(255) comment '企业对应的页面',
   remark   varchar(255) comment '备注',
   createTime           datetime not null default CURRENT_TIMESTAMP,
   primary key (companyId,url)
)
COMMENT = '企业页面表';

create table t_instockqc
(
   id            		char(32) comment '编码',
   instockMainId    	char(32) comment '入库单编码',
   qcmaterialsURL     	varchar(1024) comment '质检材料',
   createTime       	datetime,
   createBy             varchar(32),
   updateTime           datetime,
   updateBy             varchar(32),
   primary key (id)
)
COMMENT = '进货质检表';

DROP TABLE IF EXISTS t_instock_log;
CREATE TABLE t_instock_log (
  id char(32) NOT NULL COMMENT 'id',
  goodsId char(32) NOT NULL COMMENT '商品id',
  companyId char(32) DEFAULT NULL COMMENT '用户企业ID',
  instockNum varchar(255) DEFAULT NULL COMMENT '入库单号',
  instockBatchNum varchar(255) DEFAULT NULL COMMENT '入库批次号',
  price decimal(10,2) DEFAULT '0.00' COMMENT '采购价格',
  createTime datetime DEFAULT NULL,
  createBy varchar(32) DEFAULT NULL,
  updateTime datetime DEFAULT NULL,
  updateBy varchar(32) DEFAULT NULL,
  num int(11) DEFAULT NULL COMMENT '数量',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='入库日志';

CREATE TABLE t_outstock_log (
  id char(32) NOT NULL COMMENT 'id',
  goodsId char(32) NOT NULL COMMENT '商品id',
  companyId char(32) DEFAULT NULL COMMENT '用户企业ID',
  outstockNum varchar(255) DEFAULT NULL COMMENT '出库单号',
  outstockBatchNum varchar(255) DEFAULT NULL COMMENT '出库批次号',
  price decimal(10,2) DEFAULT '0.00' COMMENT '采购价格',
  createTime datetime DEFAULT NULL,
  createBy varchar(32) DEFAULT NULL,
  updateTime datetime DEFAULT NULL,
  updateBy varchar(32) DEFAULT NULL,
  num int(11) DEFAULT NULL COMMENT '数量',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='出库日志';

/*==============================================================*/
/* Table: 门店销售明细   2016年7月8日 14:13:00                          */
/*==============================================================*/
create table t_storeSale_order
(
   storeSaleOrderId     char(32) not null comment '销售单ID',
   storeSaleOrdeNo      varchar(32) not null comment '销售单编号',
   companyId            char(32) not null comment '用户企业ID',
   totalPrice           decimal(10,2) comment '销售总价',
   discountPrice        decimal(10,2) comment '折扣价格',
   clubCard             char(32) comment '会员卡',
   payType              int(2) comment '付款方式',
   orderTime            datetime not null comment '下单时间',
   createTime           datetime comment '创建时间',
   createBy             varchar(32) comment '创建人',
   updateTime           datetime comment '修改时间',
   updateBy             varchar(32) comment '修改人',
   primary key (storeSaleOrderId)
);
/*==============================================================*/
/* Table: 门店销售单                  2016年7月8日 14:13:00                       */
/*==============================================================*/
create table t_storeSale_item
(
   storeSaleItemId      char(32) not null comment '销售单明细ID',
   storeSaleOrderId     char(32) not null comment '销售单ID',
   goodsBarCode         varchar(32) not null comment '商品条形码',
   goodsTraceCode       varchar(32) not null comment '商品追溯码',
   salePrice            decimal(10,2) not null comment '销售单价',
   unitName             varchar(32) not null comment '销售单位',
   quantity             decimal(10,2) not null comment '销售数量',
   totalPrice      decimal(10,2) comment '销售总价',
   createTime           datetime comment '创建时间',
   createBy             varchar(32) comment '创建人',
   updateTime           datetime comment '修改时间',
   updateBy             varchar(32) comment '修改人',
   primary key (storeSaleItemId)
);



/*==============================================================*/
/* Table: t_store_mac     门店Mac地址管理    2016年8月19日 11:25:38                                           */
/*==============================================================*/
create table t_store_mac
(
   mac                  varchar(48) not null comment 'mac地址',
   macNum               varchar(10) not null comment 'mac编号',
   companyId            char(32) not null default '0' comment '企业ID',
   createTime           datetime comment '创建时间',
   createBy             varchar(32) comment '创建人',
   updateTime           datetime comment '修改时间',
   updateBy             varchar(32),
   isDeleted            int(1) not null default 0 comment '是否删除',
   status               char(1) not null default '0' comment '状态：0-启用，1-禁用',
   primary key (mac)
)

-- 2016-10-26 加工者信息表
create table t_processor
(
	id varchar(32) comment 'ID', 
	name varchar(50) not null comment '加工者姓名',
	healthCard varchar(255) comment '健康证图片地址',
	companyId varchar(32) not null comment '企业ID',
	PRIMARY KEY (id)
)

-- 2016-11-3 应用升级表
create table t_appliation_update(
	id varchar(32) comment 'ID',
	name varchar(50) comment '应用名',
	version varchar(32) comment '版本号',
	versionIntroduction varchar(1000) comment '版本简介',
	status char(1) comment '其否强制升级，0为否，1为是',
	applicationPath varchar(255) comment '应用路径',
	companyId varchar(32) comment '企业ID',
	createTime datetime comment '创建时间',
  	createBy varchar(32) comment '创建人',
  	updateTime datetime comment '修改时间',
  	updateBy varchar(32) comment '修改人',
	PRIMARY KEY(id)
);

-- 2016-11-14 门店注册表
create table t_store_register(
	id varchar(32) comment 'ID',
	license varchar(50) UNIQUE NOT NULL comment '注册码',
	mac varchar(32) comment 'mac地址',
	location varchar(32) NOT NULL comment '门店编号',
	subLocation varchar(32) NOT NULL comment '门店机器编号',
	companyId varchar(32) NOT NULL comment '门店企业ID',
	createTime datetime comment '创建时间',
	createBy varchar(32) comment '创建人',
	updateTime datetime comment '修改时间',
	updateBy varchar(32) comment '修改人',
	PRIMARY KEY(id)
);

-- 2016-11-16 门店账号表
create table t_store_account(
	id varchar(32) comment 'ID',
	username varchar(50) NOT NULL comment '登录用户名',
	nickName varchar(50) NOT NULL comment '显示名称',
	password varchar(32) NOT NULL comment '密码',
	companyId varchar(32) NOT NULL comment '门店企业ID',
	createTime datetime comment '创建时间',
	createBy varchar(32) comment '创建人',
	updateTime datetime comment '修改时间',
	updateBy varchar(32) comment '修改人',
	PRIMARY KEY(id),
	UNIQUE KEY (username,companyId)
);


-- 2017-09-29 源头信息
CREATE TABLE `t_origin` (
  `originId` char(32) NOT NULL,
  `companyId` char(32) DEFAULT NULL,
  `originName` varchar(255) DEFAULT NULL COMMENT '渔船名称',
  `originNo` varchar(60) DEFAULT NULL COMMENT '舷号',
  `coordinate` varchar(60) DEFAULT NULL COMMENT '坐标',
  `contact` varchar(60) DEFAULT NULL COMMENT '联系人',
  `tel` varchar(20) DEFAULT NULL COMMENT '电话',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `remark` text COMMENT '备注',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态（0开启，1关闭）',
  `isDeleted` tinyint(1) DEFAULT '0',
  `createTime` datetime DEFAULT NULL,
  `createBy` varchar(32) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `updateBy` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`originId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='民奋实业用，源头信息（渔船）';

-- 2017-10-12 商品质检信息表
create table t_goods_qcmaterials
(
   id            		char(32),
   goodsId 				char(32) comment '商品或商品材料ID',
   qcmaterialsURL     	varchar(1024) comment '质检材料',
   createTime       	datetime,
   createBy             varchar(32),
   updateTime           datetime,
   updateBy             varchar(32),
   primary key (id)
)
COMMENT = '商品质检信息';
