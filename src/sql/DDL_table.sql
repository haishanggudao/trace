-- 2016-06-13 tables of about stocking
-- product stock
create table t_productStock
(
   productStockId       char(32) not null comment '产品库存ID',
   productId            char(32) not null comment '产品ID',
   productStandardDetailId char(32) not null comment '产品规格明细ID',
   storageLocationId    char(32) not null default '-' comment '库位号ID',
   stockNum             decimal(10,2) not null default 0.00 comment '库存数量',
   companyId            char(32) not null comment '所属公司ID',
   warningNum           decimal(10,2) not null default 0.00 comment '预警数量',
   createTime           datetime,
   createBy             varchar(32),
   updateTime           datetime,
   updateBy             varchar(32),
   isDeleted            int(1) default 0 comment '0-正常 1-已删除',
   primary key (productStockId)
);

-- goods stock 
create table t_goodsStock
(
   goodsStockId         char(32) not null comment '商品库存ID',
   goodsId             char(32) not null comment '商品ID',
   storageLocationId    char(32) not null default '-' comment '库位号ID',
   stockNum             decimal(10,2) not null default 0.00 comment '库存数量',
   companyId            char(32) not null comment '所属公司ID',
   warningNum           decimal(10,2) not null default 0.00 comment '预警数量',
   createTime           datetime,
   createBy             varchar(32),
   updateTime           datetime,
   updateBy             varchar(32),
   isDeleted            int(1) default 0 comment '0-正常 1-已删除',
   primary key (goodsStockId)
);

-- historied stock of product
create table t_productStock_history
(
   productStockHistoryId char(32) not null comment '产品库存历史ID',
   productId            char(32) not null comment '产品ID',
   productStandardDetailId char(32) not null comment '产品规格明细ID',
   currentNum           decimal(10,2) not null default 0.00 comment '当前库存数量',
   operationNum         decimal(10,2) not null default 0.00 comment '操作数量',
   doneNum              decimal(10,2) not null default 0.00 comment '操作后的库存量',
   operationType        char(32) not null default 'i' comment 's-salereturn,i-instock,o-outstock,c-complement,l-loss,u-update',
   changeType           int(2) not null default 1 comment '1-add, -1-reduce',
   companyId            char(32) not null comment '所属公司ID',
   createTime           datetime,
   createBy             varchar(32),
   isDeleted            int(1) default 0 comment '0-正常 1-已删除',
   primary key (productStockHistoryId)
);

-- historied stock of goods
create table t_goodsStock_history
(
   goodsStockHistoryId  char(32) not null comment '商品库存历史ID',
   goodsId              char(32) not null comment '商品ID',
   currentNum           decimal(10,2) not null default 0.00 comment '当前库存数量',
   operationNum         decimal(10,2) not null default 0.00 comment '操作数量',
   doneNum              decimal(10,2) not null default 0.00 comment '操作后的库存量',
   operationType        char(32) not null default 'i' comment 's-salereturn,i-instock,o-outstock,c-complement,l-loss,u-update',
   changeType           int(2) not null default 1 comment '1-add, -1-reduce',
   companyId            char(32) not null comment '所属公司ID',
   createTime           datetime,
   createBy             varchar(32),
   isDeleted            int(1) default 0 comment '0-正常 1-已删除',
   primary key (goodsStockHistoryId)
);

-- product items of out stock
create table t_outstock_product_items
(
   outstockItemId       char(32) not null comment '出库明细单ID',
   outstockMainId       char(32) not null comment '出库单ID',
   productId            char(32) not null comment '产品ID',
   productStandardDetailId char(32) not null comment '产品规格明细ID',
   outstockNum          decimal(10,2) comment '出库数量',
   traceCode            varchar(255) comment '追溯码',
   createTime           datetime,
   createBy             varchar(32),
   updateTime           datetime,
   updateBy             varchar(32),
   deliveryTime         char(10),
   deliveryBy           char(10),
   isDeleted            int(1) default 0 comment '0-正常 1-已删除',
   primary key (outstockItemId)
);

-- 2016-08-01 t_product_company   
create table t_product_company
(
   productId            char(32) not null comment '产品ID',
   companyId            char(32) not null comment '企业ID',
   primary key (productId, companyId)
);

-- 2016-08-03 t_trace
create table t_trace
(
   traceCode            varchar(32) not null comment '追溯码',
   type                 int not null default 0 comment '0-goods,1-goodsDetail,2-group,3-sell',
   createBy             varchar(32),
   createTime           datetime,
   primary key (traceCode)
);