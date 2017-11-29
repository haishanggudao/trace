-- 资源表
INSERT INTO sys_resource (id,name,type,url,parent_id,permission,available,icon,sort) VALUES (1,'系统设置','menu',null,0,'system:*',1,'icon-application-cascade',1);
INSERT INTO sys_resource (id,name,type,url,parent_id,permission,available,icon,sort) VALUES (2,'用户管理','menu','user',1,'system:user:*',1,'icon-users',1);
INSERT INTO sys_resource (id,name,type,url,parent_id,permission,available,icon,sort) VALUES (3,'角色管理','menu','role',1,'system:role:*',1,'icon-user-group',2);
INSERT INTO sys_resource (id,name,type,url,parent_id,permission,available,icon) VALUES (4,'新增角色','button',null,3,'system:role:add',1,'icon-add');
INSERT INTO sys_resource (id,name,type,url,parent_id,permission,available,icon) VALUES (5,'修改角色','button',null,3,'system:role:update',1,'icon-edit');
INSERT INTO sys_resource (id,name,type,url,parent_id,permission,available,icon) VALUES (6,'删除角色','button',null,3,'system:role:del',1,'icon-remove');
INSERT INTO sys_resource (id,name,type,url,parent_id,permission,available,icon) VALUES (7,'查看角色','button',null,3,'system:role:list',1,null);
INSERT INTO sys_resource (id,name,type,url,parent_id,permission,available,icon) VALUES (8,'设置权限','button',null,3,'system:role:permissions',1,null);
INSERT INTO sys_resource (id,name,type,url,parent_id,permission,available,icon) VALUES (9,'新增用户','button',null,2,'system:user:add',1,'icon-add');
INSERT INTO sys_resource (id,name,type,url,parent_id,permission,available,icon) VALUES (10,'修改用户','button',null,2,'system:user:update',1,'icon-edit');
INSERT INTO sys_resource (id,name,type,url,parent_id,permission,available,icon) VALUES (11,'锁定用户','button',null,2,'system:user:lockUser',1,'icon-lock');
INSERT INTO sys_resource (id,name,type,url,parent_id,permission,available,icon) VALUES (12,'修改密码','button',null,2,'system:user:updatePassword',1,'icon-lock');
INSERT INTO sys_resource (id,name,type,url,parent_id,permission,available,icon) VALUES (13,'查看用户','button',null,2,'system:user:list',1,null);


-- category => 系统设置
INSERT INTO sys_resource (id,name,type,url,parent_id,permission,available,icon, sort) VALUES (47, '企业管理','menu','company',1,'system:company:*',1,'icon-house', 3);
INSERT INTO sys_resource (id,name,type,url,parent_id,permission,available,icon, sort) VALUES (48, '企业领域管理','menu','companyfield',1,'system:companyfield:*',1,'icon-color-swatch', 4);
INSERT INTO sys_resource (id,name,type,url,parent_id,permission,available,icon, sort) VALUES (51, '设备账号管理','menu','client_user',1,'system:client_user:*',1,'icon-users', 5);
INSERT INTO sys_resource (id,name,type,url,parent_id,permission,available,icon, sort) VALUES (54, '设备权限管理','menu','client_user_resource',1,'system:client_user_resource:*',1,'icon-user-group', 6);

-- category => 基础资料
INSERT INTO sys_resource (id,name,type,url,parent_id,permission,available,icon,sort) VALUES (14,'基础资料','menu',NULL,0,'base:*',1,'icon-wrench',2);
INSERT INTO sys_resource (id,name,type,url,parent_id,permission,available,icon, sort) VALUES (15, '产品管理','menu','product',14,'base:product:*',1,'icon-box', 1);
INSERT INTO sys_resource (id,name,type,url,parent_id,permission,available,icon, sort) VALUES (98, '原料管理','menu','material',14,'base:material:*',1,'icon-box', 2);
INSERT INTO sys_resource (id,name,type,url,parent_id,permission,available,icon, sort) VALUES (16, '产品属性管理','menu','product_detail_map',14,'base:product_detail_map:*',1,'icon-text-signature', 3); 
INSERT INTO sys_resource (id,name,type,url,parent_id,permission,available,icon, sort) VALUES (17, '产品类别管理','menu','product_category',14,'base:product_category:*',1,'icon-organization', 4);
INSERT INTO sys_resource (id,name,type,url,parent_id,permission,available,icon, sort) VALUES (18, '产品规格管理','menu','product_standard',14,'base:product_standard:*',1,'icon-shape-ungroup', 5);
INSERT INTO sys_resource (id,name,type,url,parent_id,permission,available,icon, sort) VALUES (19, '产品规格明细管理','menu','product_standard_detail',14,'base:product_standard_detail:*',1,'icon-expand-all', 6);
INSERT INTO sys_resource (id,name,type,url,parent_id,permission,available,icon, sort) VALUES (20, '产品规格转化管理','menu','product_standard_rate',14,'base:product_standard_rate:*',1,'icon-arrow-refresh-small', 7);
INSERT INTO sys_resource (id,name,type,url,parent_id,permission,available,icon, sort) VALUES (21, '商品管理','menu','goods',14,'base:goods:*',1,'icon-ipod-nano', 8);
INSERT INTO sys_resource (id,name,type,url,parent_id,permission,available,icon, sort) VALUES (49, '供应商管理','menu','supplier',14,'base:supplier:*',1,'icon-vcard', 9);
INSERT INTO sys_resource (id,name,type,url,parent_id,permission,available,icon, sort) VALUES (50, '客户管理','menu','customers',14,'base:customers:*',1,'icon-user-group', 10);
INSERT INTO sys_resource (id,name,type,url,parent_id,permission,available,icon, sort) VALUES (52, '物流企业管理','menu','logistics',14,'base:logistics:*',1,'icon-house', 11);
INSERT INTO sys_resource (id,name,type,url,parent_id,permission,available,icon, sort) VALUES (55, '商品原料管理','menu','goodsmaterial',14,'base:goodsmaterial:*',1,'icon-ipod-nano', 12);
INSERT INTO sys_resource (id,name,type,url,parent_id,permission,available,icon, sort) VALUES (56, '商品价格管理','menu','goodsprice',14,'base:goodsprice:*',1,'icon-ipod-nano', 13);

-- category => 流通管理
INSERT INTO sys_resource (id,name,type,url,parent_id,permission,available,icon,sort) VALUES (43,'流通管理','menu',NULL,0,'trans:*',1,'icon-creditcards',3);
INSERT INTO sys_resource (id,name,type,url,parent_id,permission,available,icon, sort) VALUES (40, '采购单管理','menu','purchase_order',43,'trans:purchase_order:*',1,'icon-cart-add', 1);
INSERT INTO sys_resource (id,name,type,url,parent_id,permission,available,icon, sort) VALUES (41, '销售单管理','menu','sale_order',43,'trans:sale_order:*',1,'icon-page-paintbrush', 2);
INSERT INTO sys_resource (id,name,type,url,parent_id,permission,available,icon, sort) VALUES (42, '包装管理','menu','package',43,'trans:package:*',1,'icon-package-green', 3);
INSERT INTO sys_resource (id,name,type,url,parent_id,permission,available,icon, sort) VALUES (45, '商品入库管理','menu','instockmain',43,'trans:instockmain:*',1,'icon-door-in', 4);
INSERT INTO sys_resource (id,name,type,url,parent_id,permission,available,icon, sort) VALUES (46, '商品出库管理','menu','outstockmain',43,'trans:outstockmain:*',1,'icon-lorry-go', 5);
INSERT INTO sys_resource (id,name,type,url,parent_id,permission,available,icon, sort) VALUES (53, '销售出库管理','menu','saleorderoutstockmain',43,'trans:saleorderoutstockmain:*',1,'icon-page-paintbrush', 6);
INSERT INTO sys_resource (id,name,type,url,parent_id,permission,available,icon, sort) VALUES (93, '采购入库管理','menu','purchaseInstockOrder',43,'trans:purchaseInstockOrder:*',1,'icon-table', 7);
INSERT INTO sys_resource (id,name,type,url,parent_id,permission,available,icon, sort) VALUES (94, '产品采购入库','menu','productPurchaseInstockOrder',43,'trans:productPurchaseInstockOrder:*',1,'icon-plugin', 8);


-- category => 加工质检管理
INSERT INTO sys_resource (id,name,type,url,parent_id,permission,available,icon,sort) VALUES (44,'加工质检','menu',NULL,0,'operation:*',1,'icon-wrench',4);
INSERT INTO sys_resource (id,name,type,url,parent_id,permission,available,icon, sort) VALUES (38, '加工管理','menu','process',44,'operation:process:*',1,'icon-cog', 1);
INSERT INTO sys_resource (id,name,type,url,parent_id,permission,available,icon, sort) VALUES (39, '质检管理','menu','qc',44,'operation:qc:*',1,'icon-folder-wrench', 2);
INSERT INTO sys_resource (id,name,type,url,parent_id,permission,available,icon, sort) VALUES (100, '加工模板','menu','processTemplate',44,'operation:processTemplate:*',1,'icon-folder-wrench', 3);

-- 系统参数
INSERT INTO sys_resource (id,name,type,url,parent_id,permission,available,icon,sort) VALUES (110,'系统参数管理','menu','variable',1,'system:variable:*',1,'icon-advancedsettings',7);

-- 产品类别权限
INSERT INTO sys_resource (id,name,type,url,parent_id,permission,available,icon,sort) VALUES (22, '新增产品类别','button',null,17,'base:product_category:create',1,'icon-add',1);
INSERT INTO sys_resource (id,name,type,url,parent_id,permission,available,icon,sort) VALUES (23, '修改产品类别','button',null,17,'base:product_category:update',1,'icon-edit',2);
INSERT INTO sys_resource (id,name,type,url,parent_id,permission,available,icon,sort) VALUES (24, '删除产品类别','button',null,17,'base:product_category:del',1,'icon-remove',3);
INSERT INTO sys_resource (id,name,type,url,parent_id,permission,available,icon,sort) VALUES (25, '查看产品类别','button',null,17,'base:product_category:list',1,null,4);

-- 产品规格权限
INSERT INTO sys_resource (id,name,type,url,parent_id,permission,available,icon,sort) VALUES (26, '新增产品规格','button',null,18,'base:product_standard:create',1,'icon-add',1);
INSERT INTO sys_resource (id,name,type,url,parent_id,permission,available,icon,sort) VALUES (27, '修改产品规格','button',null,18,'base:product_standard:update',1,'icon-edit',2);
INSERT INTO sys_resource (id,name,type,url,parent_id,permission,available,icon,sort) VALUES (28, '删除产品规格','button',null,18,'base:product_standard:del',1,'icon-remove',3);
INSERT INTO sys_resource (id,name,type,url,parent_id,permission,available,icon,sort) VALUES (29, '查看产品规格','button',null,18,'base:product_standard:list',1,null,4);

-- 产品规格明细权限
INSERT INTO sys_resource (id,name,type,url,parent_id,permission,available,icon,sort) VALUES (30, '新增产品规格明细','button',null,19,'base:product_standard_detail:create',1,'icon-add',1);
INSERT INTO sys_resource (id,name,type,url,parent_id,permission,available,icon,sort) VALUES (31, '修改产品规格明细','button',null,19,'base:product_standard_detail:update',1,'icon-edit',2);
INSERT INTO sys_resource (id,name,type,url,parent_id,permission,available,icon,sort) VALUES (32, '删除产品规格明细','button',null,19,'base:product_standard_detail:del',1,'icon-remove',3);
INSERT INTO sys_resource (id,name,type,url,parent_id,permission,available,icon,sort) VALUES (33, '查看产品规格明细','button',null,19,'base:product_standard_detail:list',1,null,4);

-- 产品规格转化权限
INSERT INTO sys_resource (id,name,type,url,parent_id,permission,available,icon,sort) VALUES (34, '新增产品规格转化','button',null,20,'base:product_standard_rate:create',1,'icon-add',1);
INSERT INTO sys_resource (id,name,type,url,parent_id,permission,available,icon,sort) VALUES (35, '修改产品规格转化','button',null,20,'base:product_standard_rate:update',1,'icon-edit',2);
INSERT INTO sys_resource (id,name,type,url,parent_id,permission,available,icon,sort) VALUES (36, '删除产品规格转化','button',null,20,'base:product_standard_rate:del',1,'icon-remove',3);
INSERT INTO sys_resource (id,name,type,url,parent_id,permission,available,icon,sort) VALUES (37, '查看产品规格转化','button',null,20,'base:product_standard_rate:list',1,null,4);

-- 屠宰场权限
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (121, '屠宰场管理', 'menu', 'slaughterhouse', 14, 'base:slaughterhouse:*', '1', 'icon-vcard', 13);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (122, '新增屠宰场', 'button', null, 121, 'base:slaughterhouse:add', '1', 'icon-add', 1);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (123, '编辑屠宰场', 'button', null, 121, 'base:slaughterhouse:edit', '1', 'icon-edit', 2);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (124, '删除屠宰场', 'button', null, 121, 'base:slaughterhouse:delete', '1', 'icon-remove', 3);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (125, '屠宰场信息导入', 'button', null, 121, 'base:slaughterhouse:import', '1', 'icon-add', 4);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (126, '查看屠宰场信息', 'button', null, 121, 'base:slaughterhouse:list', '1', null, 5);

-- 商品权限
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (131, '查看商品', 'button', null, 21, 'base:goods:list', '1', null, 1);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (132, '新增商品', 'button', null, 21, 'base:goods:add', '1', 'icon-add', 2);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (133, '修改商品', 'button', null, 21, 'base:goods:edit', '1', 'icon-edit', 3);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (134, '删除商品', 'button', null, 21, 'base:goods:delete', '1', 'icon-remove', 4);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (135, '生成二维码', 'button', null, 21, 'base:goods:createQRCode', '1', 'icon-remove', 5);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (136, '下载二维码', 'button', null, 21, 'base:goods:downQRCode', '1', 'icon-remove', 6);

-- 供应商权限
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (141, '查看供应商', 'button', null, 49, 'base:supplier:list', '1', null, 1);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (142, '新增供应商', 'button', null, 49, 'base:supplier:add', '1', 'icon-add', 2);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (143, '修改供应商', 'button', null, 49, 'base:supplier:edit', '1', 'icon-edit', 3);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (144, '删除供应商', 'button', null, 49, 'base:supplier:delete', '1', 'icon-remove', 4);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (145, '供应商信息导入', 'button', null, 49, 'base:supplier:import', '1', 'icon-remove', 5);

-- 客户权限
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (151, '查看客户', 'button', null, 50, 'base:customers:list', '1', null, 1);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (152, '新增客户', 'button', null, 50, 'base:customers:add', '1', 'icon-add',2);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (153, '修改客户', 'button', null, 50, 'base:customers:edit', '1', 'icon-edit', 3);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (154, '删除客户', 'button', null, 50, 'base:customers:delete', '1', 'icon-remove', 4);

-- 加工管理
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (161, '查看加工信息', 'button', null, 38, 'operation:process:list', '1', null, 1);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (162, '新增加工信息', 'button', null, 38, 'operation:process:add', '1', 'icon-add', 2);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (163, '修改加工信息', 'button', null, 38, 'operation:process:edit', '1', 'icon-edit', 3);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (164, '删除加工信息', 'button', null, 38, 'operation:process:delete', '1', 'icon-remove', 4);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (165, '补录加工信息', 'button', null, 38, 'operation:process:record', '1', 'icon-edit', 5);

-- 质检管理
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (171, '查看质检信息', 'button', null, 39, 'operation:qc:list', '1', null, 1);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (172, '新增质检信息', 'button', null, 39, 'operation:qc:add', '1', 'icon-add',2);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (173, '修改质检信息', 'button', null, 39, 'operation:qc:edit', '1', 'icon-edit', 3);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (174, '删除质检信息', 'button', null, 39, 'operation:qc:delete', '1', 'icon-remove', 4);

-- 加工模板
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (181, '查看加工模板', 'button', null, 100, 'operation:processTemplate:list', '1', null, 1);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (182, '新增加工模板', 'button', null, 100, 'operation:processTemplate:add', '1', 'icon-add', 2);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (183, '修改加工模板', 'button', null, 100, 'operation:processTemplate:edit', '1', 'icon-edit', 3);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (184, '删除加工模板', 'button', null, 100, 'operation:processTemplate:delete', '1', 'icon-remove', 4);

-- 产品管理
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (191, '查看产品', 'button', null, 15, 'base:product:list', '1', null, 1);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (192, '新增产品', 'button', null, 15, 'base:product:add', '1', 'icon-add', 2);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (193, '修改产品', 'button', null, 15, 'base:product:edit', '1', 'icon-edit', 3);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (194, '删除产品', 'button', null, 15, 'base:product:delete', '1', 'icon-remove', 4);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (195, '产品信息导入', 'button', null, 15, 'base:product:import', '1', 'icon-remove', 5);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (196, '类型转换', 'button', null, 15, 'base:product:convert', '1', 'icon-edit', 6);

-- 产品属性管理
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (201, '查看产品属性', 'button', null, 16, 'base:product_detail_map:list', '1', null, 1);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (202, '新增产品属性', 'button', null, 16, 'base:product_detail_map:add', '1', 'icon-add', 2);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (203, '修改产品属性', 'button', null, 16, 'base:product_detail_map:edit', '1', 'icon-edit', 3);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (204, '删除产品属性', 'button', null, 16, 'base:product_detail_map:delete', '1', 'icon-remove', 4);

-- 原料管理
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (211, '查看原料', 'button', null, 98, 'base:material:list', '1', null, 1);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (212, '新增原料', 'button', null, 98, 'base:material:add', '1', 'icon-add', 2);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (213, '修改原料', 'button', null, 98, 'base:material:edit', '1', 'icon-edit', 3);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (214, '删除原料', 'button', null, 98, 'base:material:delete', '1', 'icon-remove', 4);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (215, '原料信息导入', 'button', null, 98, 'base:material:import', '1', 'icon-remove', 5);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (216, '原料类型转换', 'button', null, 98, 'base:material:convert', '1', 'icon-edit', 6);

-- 商品原料管理
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (221, '查看商品原料', 'button', null, 55, 'base:goodsmaterial:list', '1', null, 1);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (222, '新增商品原料', 'button', null, 55, 'base:goodsmaterial:add', '1', 'icon-add', 2);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (223, '修改商品原料', 'button', null, 55, 'base:goodsmaterial:edit', '1', 'icon-edit', 3);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (224, '删除商品原料', 'button', null, 55, 'base:goodsmaterial:delete', '1', 'icon-remove', 4);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (225, '设置为已用完', 'button', null, 55, 'base:goodsmaterial:usable', '1', 'icon-remove', 5);

-- 采购单管理
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (231, '查看采购单', 'button', null, 40, 'trans:purchase_order:list', '1', null, 1);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (232, '新增采购单', 'button', null, 40, 'trans:purchase_order:add', '1', 'icon-add', 2);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (233, '修改采购单', 'button', null, 40, 'trans:purchase_order:edit', '1', 'icon-edit', 3);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (234, '删除采购单', 'button', null, 40, 'trans:purchase_order:delete', '1', 'icon-remove', 4);

-- 销售单管理
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (241, '查看销售单', 'button', null, 41, 'trans:sale_order:list', '1', null, 1);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (242, '新增销售单', 'button', null, 41, 'trans:sale_order:add', '1', 'icon-add', 2);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (243, '修改销售单', 'button', null, 41, 'trans:sale_order:edit', '1', 'icon-edit', 3);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (244, '删除销售单', 'button', null, 41, 'trans:sale_order:delete', '1', 'icon-remove', 4);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (245, '销售单二维码', 'button', null, 41, 'trans:sale_order:downQRCode', '1', 'icon-edit', 5);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (246, '销售价格', 'button', null, 41, 'trans:sale_order:salePrice', '1', 'icon-money-euro', 6);

-- 包装管理
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (251, '查看包装', 'button', null, 42, 'trans:package:list', '1', null, 1);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (252, '新增包装', 'button', null, 42, 'trans:package:add', '1', 'icon-add', 2);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (253, '修改包装', 'button', null, 42, 'trans:package:edit', '1', 'icon-edit', 3);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (254, '删除包装', 'button', null, 42, 'trans:package:delete', '1', 'icon-remove', 4);

-- 商品入库管理
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (261, '查看商品入库', 'button', null, 45, 'trans:instockmain:list', '1', null, 1);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (262, '新增商品入库', 'button', null, 45, 'trans:instockmain:add', '1', 'icon-add', 2);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (263, '修改商品入库', 'button', null, 45, 'trans:instockmain:edit', '1', 'icon-edit', 3);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (264, '删除商品入库', 'button', null, 45, 'trans:instockmain:delete', '1', 'icon-remove', 4);

-- 商品出库管理
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (271, '查看商品出库', 'button', null, 46, 'trans:outstockmain:list', '1', null, 1);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (272, '新增商品出库', 'button', null, 46, 'trans:outstockmain:add', '1', 'icon-add', 2);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (273, '修改商品出库', 'button', null, 46, 'trans:outstockmain:edit', '1', 'icon-edit', 3);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (274, '删除商品出库', 'button', null, 46, 'trans:outstockmain:delete', '1', 'icon-remove', 4);




-- 产品出库管理
INSERT INTO sys_resource (id,name,type,url,parent_id,permission,available,icon, sort) VALUES (385, '产品出库管理','menu','outstockmainProduct',43,'trans:outstockmainProduct:*',1,'icon-outstockmainProduct', 5);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (386, '查看产品出库', 'button', null, 385, 'trans:outstockmainProduct:list', '1', null, 1);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (387, '新增产品出库', 'button', null, 385, 'trans:outstockmainProduct:add', '1', 'icon-add', 2);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (388, '修改产品出库', 'button', null, 385, 'trans:outstockmainProduct:edit', '1', 'icon-edit', 3);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (389, '删除产品出库', 'button', null, 385, 'trans:outstockmainProduct:delete', '1', 'icon-remove', 4);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (390, '产品出库价格', 'button', null, 385, 'trans:outstockmainProduct:outStockPrice', '1', 'icon-money-euro', 5);


-- 团餐出库管理
INSERT INTO sys_resource (id,name,type,url,parent_id,permission,available,icon, sort) VALUES (375, '团餐出库管理','menu','groupDinnerOutStock',43,'trans:groupDinnerOutStock:*',1,'icon-tag-green', 4);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (376, '查看团餐出库', 'button', null, 375, 'trans:groupDinnerOutStock:list', '1', null, 1);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (377, '新增团餐出库', 'button', null, 375, 'trans:groupDinnerOutStock:add', '1', 'icon-add', 2);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (378, '修改团餐出库', 'button', null, 375, 'trans:groupDinnerOutStock:edit', '1', 'icon-edit', 3);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (379, '删除团餐出库', 'button', null, 375, 'trans:groupDinnerOutStock:delete', '1', 'icon-remove', 4);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (380, '生成团餐二维码', 'button', null, 375, 'trans:groupDinnerOutStock:qrCode', '1', 'icon-remove', 5);



-- 销售出库管理
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (281, '查看销售出库', 'button', null, 53, 'trans:saleorderoutstockmain:list', '1', null, 1);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (282, '新增销售出库', 'button', null, 53, 'trans:saleorderoutstockmain:add', '1', 'icon-add', 2);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (283, '修改销售出库', 'button', null, 53, 'trans:saleorderoutstockmain:edit', '1', 'icon-edit', 3);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (284, '删除销售出库', 'button', null, 53, 'trans:saleorderoutstockmain:delete', '1', 'icon-remove', 4);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (285, '销售出库二维码', 'button', null, 53, 'trans:saleorderoutstockmain:qrCode', '1', 'icon-remove', 5);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (286, '统计', 'button', null, 53, 'trans:saleorderoutstockmain:count', '1', 'icon-remove', 6);

-- 采购入库管理
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (291, '查看采购入库', 'button', null, 93, 'trans:purchaseInstockOrder:list', '1', null, 1);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (292, '新增采购入库', 'button', null, 93, 'trans:purchaseInstockOrder:add', '1', 'icon-add', 2);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (293, '修改采购入库', 'button', null, 93, 'trans:purchaseInstockOrder:edit', '1', 'icon-edit', 3);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (294, '删除采购入库', 'button', null, 93, 'trans:purchaseInstockOrder:delete', '1', 'icon-remove', 4);

-- 企业管理
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (301, '查看企业', 'button', null, 47, 'system:company:list', '1', null, 1);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (302, '新增企业', 'button', null, 47, 'system:company:add', '1', 'icon-add', 2);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (303, '修改企业', 'button', null, 47, 'system:company:edit', '1', 'icon-edit', 3);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (304, '删除企业', 'button', null, 47, 'system:company:delete', '1', 'icon-remove', 4);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (305, '企业信息导入', 'button', null, 47, 'system:company:import', '1', 'icon-remove', 5);

-- 企业领域管理
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (311, '查看企业领域', 'button', null, 48, 'system:companyfield:list', '1', null, 1);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (312, '新增企业领域', 'button', null, 48, 'system:companyfield:add', '1', 'icon-add', 2);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (313, '修改企业领域', 'button', null, 48, 'system:companyfield:edit', '1', 'icon-edit', 3);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (314, '删除企业领域', 'button', null, 48, 'system:companyfield:delete', '1', 'icon-remove', 4);

-- 设备账号管理
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (321, '查看设备账号', 'button', null, 51, 'system:client_user:list', '1', null, 1);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (322, '新增设备账号', 'button', null, 51, 'system:client_user:add', '1', 'icon-add', 2);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (323, '修改设备账号', 'button', null, 51, 'system:client_user:edit', '1', 'icon-edit', 3);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (324, '删除设备账号', 'button', null, 51, 'system:client_user:delete', '1', 'icon-remove', 4);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (325, '设置密码', 'button', null, 51, 'system:client_user:setPassword', '1', 'icon-remove', 5);

-- 设备权限管理
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (331, '查看设备权限', 'button', null, 54, 'system:client_user_resource:list', '1', null, 1);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (332, '新增设备权限', 'button', null, 54, 'system:client_user_resource:add', '1', 'icon-add', 2);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (333, '修改设备权限', 'button', null, 54, 'system:client_user_resource:edit', '1', 'icon-edit', 3);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (334, '删除设备权限', 'button', null, 54, 'system:client_user_resource:delete', '1', 'icon-remove', 4);

-- 设备权限管理
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (341, '查看系统参数', 'button', null, 110, 'system:variable:list', '1', null, 1);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (342, '修改系统参数', 'button', null, 110, 'system:variable:edit', '1', 'icon-edit', 2);

-- 物流企业管理
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (351, '查看物流企业', 'button', null, 52, 'base:logistics:list', '1', null, 1);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (352, '新增物流企业', 'button', null, 52, 'base:logistics:add', '1', 'icon-add', 2);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (353, '修改物流企业', 'button', null, 52, 'base:logistics:edit', '1', 'icon-edit', 3);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (354, '删除物流企业', 'button', null, 52, 'base:logistics:delete', '1', 'icon-remove', 4);

-- 产品采购入库管理
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (361, '查看产品采购入库', 'button', null, 94, 'trans:productPurchaseInstockOrder:list', '1', null, 1);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (362, '新增产品采购入库', 'button', null, 94, 'trans:productPurchaseInstockOrder:add', '1', 'icon-add', 2);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (363, '修改产品采购入库', 'button', null, 94, 'trans:productPurchaseInstockOrder:edit', '1', 'icon-edit', 3);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (364, '删除产品采购入库', 'button', null, 94, 'trans:productPurchaseInstockOrder:delete', '1', 'icon-remove', 4);

-- 拆分管理
INSERT INTO sys_resource (id,name,type,url,parent_id,permission,available,icon, sort) VALUES (365, '拆分管理','menu','splitProduct',44,'operation:splitProduct:*',1,'icon-folder-wrench', 4);
INSERT INTO sys_resource (id,name,type,url,parent_id,permission,available,icon, sort) VALUES (370, '拆分模板','menu','splitTemplate',44,'operation:splitTemplate:*',1,'icon-folder-wrench', 5);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (366, '查看拆分信息', 'button', null, 365, 'operation:splitProduct:list', '1', null, 1);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (367, '新增拆分信息', 'button', null, 365, 'operation:splitProduct:add', '1', 'icon-add', 2);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (368, '修改拆分信息', 'button', null, 365, 'operation:splitProduct:edit', '1', 'icon-edit', 3);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (369, '删除拆分信息', 'button', null, 365, 'operation:splitProduct:delete', '1', 'icon-remove', 4);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (371, '查看拆分模板', 'button', null, 370, 'operation:splitTemplate:list', '1', null, 1);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (372, '新增拆分模板', 'button', null, 370, 'operation:splitTemplate:add', '1', 'icon-add', 2);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (373, '修改拆分模板', 'button', null, 370, 'operation:splitTemplate:edit', '1', 'icon-edit', 3);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (374, '删除拆分模板', 'button', null, 370, 'operation:splitTemplate:delete', '1', 'icon-remove', 4);

-- 基础资料 -- 商品价格管理 2016-06-12
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (381, '查看商品价格', 'button', null, 56, 'base:goodsprice:list', '1', null, 1);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (382, '新增商品价格', 'button', null, 56, 'base:goodsprice:add', '1', 'icon-add', 2);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (383, '修改商品价格', 'button', null, 56, 'base:goodsprice:edit', '1', 'icon-edit', 3);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (384, '删除商品价格', 'button', null, 56, 'base:goodsprice:delete', '1', 'icon-remove', 4);

-- 流通管理 -- 进货管理 2016.6.14 
INSERT INTO sys_resource (id,name,type,url,parent_id,permission,available,icon, sort,remark) VALUES (57, '进货管理','menu','purchase_manager',43,'trans:purchase_manager:*',1,'icon-cart-add', 9,'羽众');
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (391, '查看进货管理', 'button', null, 57, 'trans:purchase_manager:list', '1', null, 1);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (392, '新增进货管理', 'button', null, 57, 'trans:purchase_manager:add', '1', 'icon-add', 2);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (393, '显示采购价格', 'button', null, 57, 'trans:purchase_manager:purchasePrice', '1', 'icon-money-euro', 3);


-- 流通管理 -- 产品库存管理 2016-06-15
INSERT INTO sys_resource (id,name,type,url,parent_id,permission,available,icon, sort, remark) VALUES (95, '产品库存管理','menu','productStock',43,'trans:productStock:*',1,'icon-plugin', 10, '羽众');
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (401, '查看产品库存', 'button', null, 95, 'trans:productStock:list', '1', null, 1);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (402, '新增产品库存', 'button', null, 95, 'trans:productStock:add', '1', 'icon-add', 2);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (403, '修改产品库存', 'button', null, 95, 'trans:productStock:edit', '1', 'icon-edit', 3);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (404, '删除产品库存', 'button', null, 95, 'trans:productStock:delete', '1', 'icon-remove', 4);
-- 流通管理 -- 门店销售管理 2016年7月11日
INSERT INTO sys_resource (id,name,type,url,parent_id,permission,available,icon, sort,remark) VALUES (31300, '门店销售管理','menu','storeSaleOrder',43,'trans:storeSaleOrder:*',1,'icon-shape-align-left', 13,'羽众');
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (31301, '查看门店销售', 'button', null, 31300, 'trans:storeSaleOrder:list', '1', null, 1);


-- 商品库存管理 2016-06-16
INSERT INTO sys_resource (id,name,type,url,parent_id,permission,available,icon, sort, remark) VALUES (96, '商品库存管理','menu','goodsStock',43,'trans:goodsStock:*',1,'icon-plugin', 11, '羽众');
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (411, '查看商品库存', 'button', null, 96, 'trans:goodsStock:list', '1', null, 1);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (412, '修改商品库存', 'button', null, 96, 'trans:goodsStock:edit', '1', null, 2);

-- 分割栏 报表模块 2016-06-21
INSERT INTO sys_resource (id,name,type,url,parent_id,permission,available,icon,sort) VALUES (5000,'报表模块','menu',NULL,0,'report:*',1,'icon-application-cascade',5);
INSERT INTO sys_resource (id,name,type,url,parent_id,permission,available,icon, sort, remark) VALUES (5001, '入库报表','menu','instockReport',5000,'report:instockReport:*',1,'icon-plugin', 1, '羽众');
INSERT INTO sys_resource (id,name,type,url,parent_id,permission,available,icon, sort, remark) VALUES (5011, '出库报表','menu','outstockReport',5000,'report:outstockReport:*',1,'icon-plugin', 2, '羽众');
INSERT INTO sys_resource (id,name,type,url,parent_id,permission,available,icon, sort, remark) VALUES (5021, '采购报表','menu','purchaseReport',5000,'report:purchaseReport:*',1,'icon-plugin', 3, '羽众');
INSERT INTO sys_resource (id,name,type,url,parent_id,permission,available,icon, sort, remark) VALUES (5031, '销售报表','menu','sellReport',5000,'report:sellReport:*',1,'icon-plugin', 4, '羽众');
INSERT INTO `sys_resource` (`id`, `name`, `type`, `url`, `parent_id`, `permission`, `available`, `icon`, `sort`, `remark`) VALUES ('5041', '价格表报', 'menu', 'pricereport', '5000', 'report:pricereport:*', '1', 'icon-plugin', '5', '羽众');
-- 用户表
INSERT INTO sys_user (id,username,password,salt,locked) VALUES (1,'admin','b5d4a1d2114da52dddb98b0ca5af2a14','aa17b481d554a36442fe67f7da5d5bdf',0);

-- 角色表
INSERT INTO sys_role(id,role_name, description,available) VALUES (1,'admin','超级管理员',1);

-- 用户角色关系表
INSERT INTO sys_user_role_relation (user_id,role_id) VALUES (1,1);

-- 角色资源关系表
INSERT INTO sys_role_resource_relation (role_id,resource_id) VALUES (1,1);
INSERT INTO sys_role_resource_relation (role_id,resource_id) VALUES (1,14);


-- 默认企业领域
INSERT INTO t_company_field (companyFieldId, companyFieldName,companyFieldDesc, level,parentFieldId, status, createTime,createBy, updateTime, updateBy)
	VALUES ('01A3ADB3076B11E690B100505697467E','其它','其它',1,null,0,SYSDATE(),1,SYSDATE(),1);
	
INSERT INTO t_common_variable (varId,companyId,varGroup,varName,varValue,sort,createTime,updateTime) VALUES (replace(uuid(),'-',''),'ebf2803bc67844efaae5a383769031c2','deliverType','1','团餐',1,SYSDATE(),SYSDATE());
INSERT INTO t_common_variable (varId,companyId,varGroup,varName,varValue,sort,createTime,updateTime) VALUES ('b0169fd410884fd4a02ec5e3e9fc1f14','ebf2803bc67844efaae5a383769031c2','clientType','1','手持机',1,SYSDATE(),SYSDATE());


-- 商品价格管理 -- 采购价格权限 2016年7月18日
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (21205, '查看采购价格', 'button', null, 21200, 'base:productStock:purchasePrice', '1', 'icon-money-euro', 5);

-- 2016-7-19 销售单流水号
INSERT INTO `t_common_variable` VALUES (lower(replace(uuid(),'-','')), '-', 'saleOrder', 'serialNum', '1', '1', SYSDATE(), null, SYSDATE(), null, '销售单流水号');

-- 查询分析 -- 二维码追溯信息统计2016-07-21
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES(60000, '查询分析','menu', null, 0, 'stats:*', 1, 'icon-search', 6);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES(60100, '追溯信息统计','menu','codequery/huntCodeTrace', 60000, 'stats:huntcodetrace:*', 1, 'icon-search', 1);

-- 原料管理 按企业ID跳转页面 2016-07-26
-- 需要修改companyId的UUID 金机餐饮
insert into t_company_page (companyId, url, page, remark, createTime)
values('ebf2803bc67844efaae5a383769031c2', '/trace/material', 'jinji/material', '原料管理', SYSDATE());

-- 2016-7-27 商品批次号
INSERT INTO t_common_variable (varId,companyId,varGroup,varName,varValue,sort,createTime,updateTime,remark) VALUES (lower(replace(uuid(),'-','')), '-', 'goods', 'goodsBatch', '1', '1', SYSDATE(), SYSDATE(), '商品批次号');

-- 零售门店备案信息 按企业ID跳转页面 2016-08-02
-- 需要修改companyId的UUID 羽众酒业
insert into t_company_page (companyId, url, page, remark, createTime)
values('28c5c65bf46f4281aeac7d45cfd234f3', '/trace/customers', 'yz/sellStore', '零售门店备案信息', SYSDATE());

-- 产品管理  按企业ID跳转页面 2016-08-03
-- 需要修改companyId的UUID 羽众酒业
insert into t_company_page (companyId, url, page, remark, createTime)
values('28c5c65bf46f4281aeac7d45cfd234f3', '/trace/product', 'yz/product', '羽众产品管理', SYSDATE());

-- 2016年8月4日 14:46:49  创建客户表的企业ID和客户企业ID设置为唯一约束
ALTER TABLE t_customers  ADD UNIQUE KEY(companyId, custCompanyId); 

-- 2016.08.08 添加我的企业
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort, remark) VALUES ('10310', '我的企业', 'menu', 'mycompany', '10000', 'system:mycompany:*', '1', 'icon-house', '4', NULL);

-- 2016-08-23 企业管理 按企业ID跳转页面 
-- 需要修改companyId的UUID 水果协会
insert into t_company_page (companyId, url, page, remark, createTime)
values('59019e61b7554cc082510394694c23a3', '/trace/company', 'fruit/company', '企业管理', SYSDATE());

-- 2016-08-23 产品管理 按企业ID跳转页面 
-- 需要修改companyId的UUID 水果协会
insert into t_company_page (companyId, url, page, remark, createTime)
values('59019e61b7554cc082510394694c23a3', '/trace/product', 'fruit/product', '水果协会产品管理', SYSDATE());

-- 2016-08-25 水果协会企业性质
INSERT INTO t_common_variable (varId,companyId,varGroup,varName,
varValue,sort,createTime,updateTime,remark) 
VALUES (lower(replace(uuid(),'-','')), '-', 'fruitCompanyNature', '1', 
'水果供应商', '1', SYSDATE(), SYSDATE(), '水果协会企业性质');

-- 2016-09-01 打印二维码和条形码权限
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (20707, '打印二维码', 'button', null, 20700, 'base:goods:printQRCode', '1', 'icon-remove', 7);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (20708, '打印条形码', 'button', null, 20700, 'base:goods:printBarCode', '1', 'icon-remove', 8);

-- 2016-09-28 商品管理 按企业ID跳转页面 
-- 需要修改companyId的UUID 水果协会
insert into t_company_page (companyId, url, page, remark, createTime)
values('59019e61b7554cc082510394694c23a3', '/trace/goods', 'fruit/goods', '水果协会商品管理', SYSDATE());

-- 2016-10-26 加工者信息管理
INSERT INTO sys_resource (id,name,type,url,parent_id,permission,available,icon, sort) VALUES (21500, '加工者信息管理','menu','processor',20000,'base:processor:*',1,'icon-ipod-nano', 15);
INSERT INTO sys_resource (id,name,type,url,parent_id,permission,available,icon, sort) VALUES (21501, '新增加工者','button',null,21500,'base:processor:add',1,'icon-add-s1', 1);
INSERT INTO sys_resource (id,name,type,url,parent_id,permission,available,icon, sort) VALUES (21502, '修改加工者','button',null,21500,'base:processor:edit',1,'icon-edit-s1', 2);
INSERT INTO sys_resource (id,name,type,url,parent_id,permission,available,icon, sort) VALUES (21503, '查看加工者','button',null,21500,'base:processor:list',1,null, 3);

-- 2016-11-2 APK上传
INSERT INTO sys_resource (id,name,type,url,parent_id,permission,available,icon, sort) VALUES (10800, '应用管理','menu','application',10000,'system:application:*',1,'icon-ipod-nano', 8);

-- 2016-11-3 APK上传目录和下载地址
insert into sys_variable (variableId,varkey,varValue,status) values (upper(replace(uuid(),'-','')),'appPath','http://192.168.8.39:8080/app/',0);
insert into sys_variable (variableId,varkey,varValue,status) values (upper(replace(uuid(),'-','')),'appStrogePath','/usr/local/apache-tomcat-8.0.24/webapps/app/',0);

-- 2016-11-14 门店注册管理
INSERT INTO sys_resource (id,name,type,url,parent_id,permission,available,icon, sort) VALUES (10900, '门店注册管理','menu','storeRegister',10000,'system:storeRegister:*',1,'icon-ipod-nano', 9);
INSERT INTO sys_resource (id,name,type,url,parent_id,permission,available,icon, sort) VALUES (10901, '新增门店注册','button',null,10900,'system:storeRegister:add',1,'icon-add-s1', 1);
INSERT INTO sys_resource (id,name,type,url,parent_id,permission,available,icon, sort) VALUES (10902, '修改门店注册','button',null,10900,'system:storeRegister:edit',1,'icon-edit-s1', 2);
INSERT INTO sys_resource (id,name,type,url,parent_id,permission,available,icon, sort) VALUES (10903, '查看门店注册','button',null,10900,'system:storeRegister:list',1,null, 3);
-- 2016-11-17 门店账号管理
INSERT INTO sys_resource (id,name,type,url,parent_id,permission,available,icon, sort) VALUES (11000, '门店账号管理','menu','storeAccount',10000,'system:storeAccount:*',1,'icon-ipod-nano', 10);
INSERT INTO sys_resource (id,name,type,url,parent_id,permission,available,icon, sort) VALUES (11001, '新增门店账号','button',null,11000,'system:storeAccount:add',1,'icon-add-s1', 1);
INSERT INTO sys_resource (id,name,type,url,parent_id,permission,available,icon, sort) VALUES (11002, '修改门店账号','button',null,11000,'system:storeAccount:edit',1,'icon-edit-s1', 2);
INSERT INTO sys_resource (id,name,type,url,parent_id,permission,available,icon, sort) VALUES (11003, '删除门店账号','button',null,11000,'system:storeAccount:del',1,'icon-icon-remove', 3);
INSERT INTO sys_resource (id,name,type,url,parent_id,permission,available,icon, sort) VALUES (11004, '查看门店账号','button',null,11000,'system:storeAccount:list',1,null, 4);

-- 2016-11-22 门店产品管理
INSERT INTO sys_resource (id,name,type,url,parent_id,permission,available,icon, sort) VALUES (21600, '门店产品管理','menu','storeProduct',20000,'base:storeProduct:*',1,'icon-ipod-nano', 16);

-- 2016-11-23 删除多余规格明细
update t_product_standard_detail d set d.companyId=(select p.companyId from t_product p where p.productId=d.productId)  where companyId='-';
DELETE from t_product_standard_detail where companyId='';

insert into t_company_page (companyId, url, page, remark, createTime)
values('ebf2803bc67844efaae5a383769031c2', '/', 'yz/index', '羽众首页', SYSDATE());
insert into t_company_page (companyId, url, page, remark, createTime)
values('784de59ed8644a049b90b45b28590b7e', '/', 'fruit/index', '水果协会首页', SYSDATE());
-- 2017-3-29 菜场规格明细,供应商管理
insert into t_company_page (companyId,url,page,remark) values ('2354d370f24d47d2a59a5cef6d34af21','/trace/product_standard_detail','market/productStandardDetail','菜场规格明细管理');
insert into t_company_page (companyId,url,page,remark) values ('2354d370f24d47d2a59a5cef6d34af21','/trace/supplier','market/supplier','菜场供应商管理');

-- 2017-09-29 源头信息权限
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (21700, '源头信息管理', 'menu', 'origin', 20000, 'base:origin:*', '1', 'icon-vcard', 17);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (21701, '新增源头信息', 'button', null, 21700, 'base:origin:add', '1', 'icon-add', 1);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (21702, '编辑源头信息', 'button', null, 21700, 'base:origin:edit', '1', 'icon-edit', 2);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (21703, '删除源头信息', 'button', null, 21700, 'base:origin:delete', '1', 'icon-remove', 3);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (21704, '查看源头信息', 'button', null, 21700, 'base:origin:list', '1', null, 4);

-- 2017-10-12 商品质检
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (40600, '产品质检管理', 'menu', 'goodsqc', 40000, 'operation:goodsQC:*', '1', 'icon-vcard', 6);
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (40700, '原料质检管理', 'menu', 'materialsQC', 40000, 'operation:materialsQC:*', '1', 'icon-vcard',7);

-- 2017-10-16 是否需要二维码前缀
INSERT INTO t_common_variable (varId,companyId,varGroup,varName,varValue,sort,createTime,updateTime,remark) VALUES (replace(uuid(),'-',''),'92321c25678c4ed49ac45ea3ab5d2e41','qrcodePrev','qrcodePrevFlag','1',1,SYSDATE(),SYSDATE(),'下载二维码时是否需要前缀');
-- 2017-10-24 打印二维码（插件）
INSERT INTO sys_resource (id, name, type, url, parent_id, permission, available, icon, sort) VALUES (20709, '打印追溯码(插件)', 'button', null, 20700, 'base:goods:printQRCodeByPlugin', '1', 'icon-remove', 9);