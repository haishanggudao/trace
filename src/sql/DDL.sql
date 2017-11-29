DROP TABLE IF EXISTS `jiaoda`.`sys_user` ;

CREATE TABLE IF NOT EXISTS `jiaoda`.`sys_user` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` VARCHAR(45) NOT NULL COMMENT '用户名',
  `password` VARCHAR(45) NOT NULL COMMENT '密码',
  `salt` VARCHAR(45) NOT NULL COMMENT '盐',
  `locked` TINYINT(1) NOT NULL DEFAULT 0 COMMENT '锁定标志',
  PRIMARY KEY (`id`)  ,
  UNIQUE INDEX `username_UNIQUE` (`username` ASC)  )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin
COMMENT = '用户表';


-- -----------------------------------------------------
-- Table `jiaoda`.`t_supplier`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jiaoda`.`t_supplier` ;

CREATE TABLE IF NOT EXISTS `jiaoda`.`t_supplier` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '供应商ID',
  `supplier_name` VARCHAR(45) NOT NULL COMMENT '供应商名称',
  `address` VARCHAR(200) NULL COMMENT '地址',
  `phone` VARCHAR(20) NULL COMMENT '电话',
  `contacts` VARCHAR(45) NULL COMMENT '联系人',
  PRIMARY KEY (`id`)  ,
  UNIQUE INDEX `supplier_name_UNIQUE` (`supplier_name` ASC)  )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin
COMMENT = '供应商表';


-- -----------------------------------------------------
-- Table `jiaoda`.`t_user_supplier_relation`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jiaoda`.`t_user_supplier_relation` ;

CREATE TABLE IF NOT EXISTS `jiaoda`.`t_user_supplier_relation` (
  `user_id` INT UNSIGNED NOT NULL COMMENT '用户ID',
  `supplier_id` INT UNSIGNED NOT NULL COMMENT '供应商ID',
  PRIMARY KEY (`user_id`, `supplier_id`)  ,
  INDEX `fk_t_user_supplier_relation_t_supplier1_idx` (`supplier_id` ASC)  ,
  CONSTRAINT `fk_t_user_supplier_relation_sys_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `jiaoda`.`sys_user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_user_supplier_relation_t_supplier1`
    FOREIGN KEY (`supplier_id`)
    REFERENCES `jiaoda`.`t_supplier` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin
COMMENT = '用户供应商关系表';


-- -----------------------------------------------------
-- Table `jiaoda`.`sys_resource`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jiaoda`.`sys_resource` ;

CREATE TABLE IF NOT EXISTS `jiaoda`.`sys_resource` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NOT NULL COMMENT '权限描述',
  `type` VARCHAR(45) NOT NULL COMMENT '资源类型,menu或button',
  `url` VARCHAR(45) NULL COMMENT '资源的URL链接',
  `parent_id` INT NOT NULL COMMENT '父权限ID',
  `permission` VARCHAR(200) NULL COMMENT '资源的权限表达式',
  `available` TINYINT(1) NOT NULL DEFAULT 1 COMMENT '是否可见,默认可见',
  `icon` VARCHAR(45) NULL COMMENT '图标',
  PRIMARY KEY (`id`)  ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC)  ,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC)  )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin
COMMENT = '资源表';


-- -----------------------------------------------------
-- Table `jiaoda`.`sys_role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jiaoda`.`sys_role` ;

CREATE TABLE IF NOT EXISTS `jiaoda`.`sys_role` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` VARCHAR(45) NOT NULL COMMENT '角色名称',
  `description` VARCHAR(45) NULL COMMENT '角色描述',
  `available` TINYINT(1) NULL DEFAULT 1 COMMENT '是否可见,默认可见',
  PRIMARY KEY (`id`)  ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC)  ,
  UNIQUE INDEX `role_name_UNIQUE` (`role_name` ASC)  )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin
COMMENT = '角色表';


-- -----------------------------------------------------
-- Table `jiaoda`.`sys_role_resource_relation`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jiaoda`.`sys_role_resource_relation` ;

CREATE TABLE IF NOT EXISTS `jiaoda`.`sys_role_resource_relation` (
  `role_id` INT UNSIGNED NOT NULL COMMENT '角色ID',
  `resource_id` INT UNSIGNED NOT NULL COMMENT '资源ID',
  PRIMARY KEY (`role_id`, `resource_id`)  ,
  INDEX `fk_sys_role_resource_relation_sys_resource1_idx` (`resource_id` ASC)  ,
  CONSTRAINT `fk_sys_role_resource_relation_sys_role1`
    FOREIGN KEY (`role_id`)
    REFERENCES `jiaoda`.`sys_role` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_sys_role_resource_relation_sys_resource1`
    FOREIGN KEY (`resource_id`)
    REFERENCES `jiaoda`.`sys_resource` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin
COMMENT = '角色权限关系表';


-- -----------------------------------------------------
-- Table `jiaoda`.`sys_user_role_relation`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jiaoda`.`sys_user_role_relation` ;

CREATE TABLE IF NOT EXISTS `jiaoda`.`sys_user_role_relation` (
  `user_id` INT UNSIGNED NOT NULL COMMENT '用户ID',
  `role_id` INT UNSIGNED NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`, `role_id`)  ,
  INDEX `fk_sys_user_role_relation_sys_role1_idx` (`role_id` ASC)  ,
  CONSTRAINT `fk_sys_user_role_relation_sys_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `jiaoda`.`sys_user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_sys_user_role_relation_sys_role1`
    FOREIGN KEY (`role_id`)
    REFERENCES `jiaoda`.`sys_role` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin
COMMENT = '用户角色关系表';


-- -----------------------------------------------------
-- Table `jiaoda`.`t_project_site`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jiaoda`.`t_project_site` ;

CREATE TABLE IF NOT EXISTS `jiaoda`.`t_project_site` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '项目点ID',
  `site_name` VARCHAR(45) NOT NULL COMMENT '项目点名称',
  `contacts` VARCHAR(45) NULL COMMENT '联系人',
  `address` VARCHAR(200) NULL COMMENT '地址',
  `phone` VARCHAR(20) NULL COMMENT '电话',
  `type` TINYINT(1) UNSIGNED NULL COMMENT '项目点类型，校内或校外',
  `check_date` TINYINT(2) UNSIGNED NOT NULL COMMENT '月末结账日期',
  PRIMARY KEY (`id`)  ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC)  )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin
COMMENT = '项目点表';


-- -----------------------------------------------------
-- Table `jiaoda`.`t_project_supplier_relation`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jiaoda`.`t_project_supplier_relation` ;

CREATE TABLE IF NOT EXISTS `jiaoda`.`t_project_supplier_relation` (
  `site_id` INT UNSIGNED NOT NULL COMMENT '项目点ID',
  `supplier_id` INT UNSIGNED NOT NULL COMMENT '供应商ID',
  PRIMARY KEY (`site_id`, `supplier_id`)  ,
  INDEX `fk_t_project_supplier_relation_t_supplier1_idx` (`supplier_id` ASC)  ,
  CONSTRAINT `fk_t_project_supplier_relation_t_project_site1`
    FOREIGN KEY (`site_id`)
    REFERENCES `jiaoda`.`t_project_site` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_project_supplier_relation_t_supplier1`
    FOREIGN KEY (`supplier_id`)
    REFERENCES `jiaoda`.`t_supplier` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin
COMMENT = '项目点供应商关系表';


-- -----------------------------------------------------
-- Table `jiaoda`.`t_good_type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jiaoda`.`t_good_type` ;

CREATE TABLE IF NOT EXISTS `jiaoda`.`t_good_type` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '商品分类ID',
  `type_name` VARCHAR(45) NOT NULL COMMENT '商品分类名称',
  `parent_id` INT NOT NULL COMMENT '分类父ID',
  UNIQUE INDEX `type_name_UNIQUE` (`type_name` ASC)  ,
  PRIMARY KEY (`id`)  )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin
COMMENT = '商品分类表';


-- -----------------------------------------------------
-- Table `jiaoda`.`t_supplier_goodtype_relation`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jiaoda`.`t_supplier_goodtype_relation` ;

CREATE TABLE IF NOT EXISTS `jiaoda`.`t_supplier_goodtype_relation` (
  `supplier_id` INT UNSIGNED NOT NULL COMMENT '供应商ID',
  `good_type_id` INT UNSIGNED NOT NULL COMMENT '商品分类ID',
  PRIMARY KEY (`supplier_id`, `good_type_id`)  ,
  INDEX `fk_t_supplier_goodtype_relation_t_good_type1_idx` (`good_type_id` ASC)  ,
  CONSTRAINT `fk_t_supplier_goodtype_relation_t_supplier1`
    FOREIGN KEY (`supplier_id`)
    REFERENCES `jiaoda`.`t_supplier` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_supplier_goodtype_relation_t_good_type1`
    FOREIGN KEY (`good_type_id`)
    REFERENCES `jiaoda`.`t_good_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin
COMMENT = '供应商商品分类表';


-- -----------------------------------------------------
-- Table `jiaoda`.`t_goods`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jiaoda`.`t_goods` ;

CREATE TABLE IF NOT EXISTS `jiaoda`.`t_goods` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '商品ID',
  `good_name` VARCHAR(45) NOT NULL COMMENT '商品名称',
  `good_fullname` VARCHAR(45) NULL COMMENT '商品全名',
  `mnemonic_code` VARCHAR(45) NULL COMMENT '助记码',
  `unit` VARCHAR(45) NOT NULL COMMENT '单位',
  `standard` VARCHAR(45) NULL COMMENT '规格',
  `guide_price` INT NOT NULL COMMENT '指导价',
  `cost_price` INT NOT NULL COMMENT '核价',
  `good_type_id` INT UNSIGNED NOT NULL COMMENT '商品分类ID',
  PRIMARY KEY (`id`)  ,
  UNIQUE INDEX `good_name_UNIQUE` (`good_name` ASC)  ,
  INDEX `fk_t_goods_t_good_type1_idx` (`good_type_id` ASC)  ,
  CONSTRAINT `fk_t_goods_t_good_type1`
    FOREIGN KEY (`good_type_id`)
    REFERENCES `jiaoda`.`t_good_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin
COMMENT = '商品表';


-- -----------------------------------------------------
-- Table `jiaoda`.`t_menu_type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jiaoda`.`t_menu_type` ;

CREATE TABLE IF NOT EXISTS `jiaoda`.`t_menu_type` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '菜谱分类ID',
  `type_name` VARCHAR(45) NOT NULL COMMENT '类型名称',
  `parent_id` INT NOT NULL COMMENT '所属父分类ID',
  PRIMARY KEY (`id`)  ,
  UNIQUE INDEX `type_name_UNIQUE` (`type_name` ASC)  )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin
COMMENT = '菜谱分类表';


-- -----------------------------------------------------
-- Table `jiaoda`.`t_menu`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jiaoda`.`t_menu` ;

CREATE TABLE IF NOT EXISTS `jiaoda`.`t_menu` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '菜谱ID',
  `menu_name` VARCHAR(45) NOT NULL COMMENT '菜谱名称',
  `menu_type_id` INT UNSIGNED NOT NULL COMMENT '菜谱分类ID',
  PRIMARY KEY (`id`)  ,
  INDEX `fk_t_menu_t_menu_type1_idx` (`menu_type_id` ASC)  ,
  CONSTRAINT `fk_t_menu_t_menu_type1`
    FOREIGN KEY (`menu_type_id`)
    REFERENCES `jiaoda`.`t_menu_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin
COMMENT = '菜谱表';


-- -----------------------------------------------------
-- Table `jiaoda`.`t_menu_ingredient`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jiaoda`.`t_menu_ingredient` ;

CREATE TABLE IF NOT EXISTS `jiaoda`.`t_menu_ingredient` (
  `menu_id` INT UNSIGNED NOT NULL COMMENT '菜谱ID',
  `good_id` INT UNSIGNED NOT NULL COMMENT '商品ID',
  `ingredient_type` VARCHAR(10) NOT NULL COMMENT '原料类型，主料或辅料',
  `weight` INT NOT NULL COMMENT '分量',
  PRIMARY KEY (`menu_id`, `good_id`)  ,
  INDEX `fk_t_menu_ingredient_t_goods1_idx` (`good_id` ASC)  ,
  CONSTRAINT `fk_t_menu_ingredient_t_menu1`
    FOREIGN KEY (`menu_id`)
    REFERENCES `jiaoda`.`t_menu` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_menu_ingredient_t_goods1`
    FOREIGN KEY (`good_id`)
    REFERENCES `jiaoda`.`t_goods` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin
COMMENT = '菜谱成分表';


-- -----------------------------------------------------
-- Table `jiaoda`.`t_order`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jiaoda`.`t_order` ;

CREATE TABLE IF NOT EXISTS `jiaoda`.`t_order` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '订单编号',
  `create_time` timestamp default CURRENT_TIMESTAMP  NOT NULL COMMENT '订单创建时间',
  `order_time` timestamp  NULL COMMENT '订单生成时间',
  `storage_time` timestamp  NULL COMMENT '入库时间',
  `good_id` INT UNSIGNED NOT NULL COMMENT '商品编号',
  `supplier_id` INT UNSIGNED NOT NULL COMMENT '供应商ID',
  `order_number` INT NOT NULL COMMENT '订单数量',
  `user_id` INT UNSIGNED NOT NULL COMMENT '订货用户ID',
  `site_id` INT UNSIGNED NOT NULL COMMENT '项目点ID',
  `send_number` INT NULL COMMENT '发货数量',
  `received_number` INT NULL COMMENT '实收数量',
  `product_place` VARCHAR(200) NULL COMMENT '产地',
  `status` INT NULL COMMENT '订单状态',
  `send_time` timestamp  NULL COMMENT '订单发货时间',
  `confirm_time` timestamp  NULL COMMENT '订单确认收货时间',
  `good_price` INT NOT NULL COMMENT '当前商品价格',
  PRIMARY KEY (`id`)  ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC)  ,
  INDEX `fk_t_order_t_goods1_idx` (`good_id` ASC)  ,
  INDEX `fk_t_order_t_supplier1_idx` (`supplier_id` ASC)  ,
  INDEX `fk_t_order_sys_user1_idx` (`user_id` ASC)  ,
  INDEX `fk_t_order_t_project_site1_idx` (`site_id` ASC)  ,
  CONSTRAINT `fk_t_order_t_goods1`
    FOREIGN KEY (`good_id`)
    REFERENCES `jiaoda`.`t_goods` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_order_t_supplier1`
    FOREIGN KEY (`supplier_id`)
    REFERENCES `jiaoda`.`t_supplier` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_order_sys_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `jiaoda`.`sys_user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_order_t_project_site1`
    FOREIGN KEY (`site_id`)
    REFERENCES `jiaoda`.`t_project_site` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin
COMMENT = '订单表';


-- -----------------------------------------------------
-- Table `jiaoda`.`t_stock`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jiaoda`.`t_stock` ;

CREATE TABLE IF NOT EXISTS `jiaoda`.`t_stock` (
  `good_id` INT UNSIGNED NOT NULL COMMENT '商品编号',
  `store_number` INT UNSIGNED NOT NULL COMMENT '库存数量',
  `average_price` INT NOT NULL COMMENT '移动平均价',
  PRIMARY KEY (`good_id`)  ,
  UNIQUE INDEX `good_id_UNIQUE` (`good_id` ASC)  ,
  CONSTRAINT `fk_t_stock_t_goods1`
    FOREIGN KEY (`good_id`)
    REFERENCES `jiaoda`.`t_goods` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin
COMMENT = '库存表';


-- -----------------------------------------------------
-- Table `jiaoda`.`t_out_order`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jiaoda`.`t_out_order` ;

CREATE TABLE IF NOT EXISTS `jiaoda`.`t_out_order` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '出库编号',
  `good_id` INT UNSIGNED NOT NULL COMMENT '出库商品编号',
  `out_number` VARCHAR(45) NOT NULL COMMENT '出库数量',
  `create_time` DATETIME NOT NULL COMMENT '出库时间',
  `user_id` INT UNSIGNED NOT NULL COMMENT '出库用户编号',
  `average_price` INT NULL COMMENT '平均价',
  PRIMARY KEY (`id`)  ,
  INDEX `fk_t_out_order_sys_user1_idx` (`user_id` ASC)  ,
  INDEX `fk_t_out_order_t_goods1_idx` (`good_id` ASC)  ,
  CONSTRAINT `fk_t_out_order_sys_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `jiaoda`.`sys_user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_out_order_t_goods1`
    FOREIGN KEY (`good_id`)
    REFERENCES `jiaoda`.`t_goods` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin
COMMENT = '出库记录表';


-- -----------------------------------------------------
-- Table `jiaoda`.`t_user_site_relation`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jiaoda`.`t_user_site_relation` ;

CREATE TABLE IF NOT EXISTS `jiaoda`.`t_user_site_relation` (
  `user_id` INT UNSIGNED NOT NULL COMMENT '用户ID',
  `site_id` INT UNSIGNED NOT NULL COMMENT '项目点ID',
  PRIMARY KEY (`user_id`, `site_id`)  ,
  INDEX `fk_t_user_site_relation_t_project_site1_idx` (`site_id` ASC)  ,
  CONSTRAINT `fk_t_user_site_relation_sys_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `jiaoda`.`sys_user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_user_site_relation_t_project_site1`
    FOREIGN KEY (`site_id`)
    REFERENCES `jiaoda`.`t_project_site` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin
COMMENT = '用户项目点关系表';