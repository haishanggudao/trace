-- 删除质检报告
delete from t_yz_instockqc where instockMainId in (select instockMainId from t_instock_main where companyId in ('bf5d65b0cd7d49f2a8e9a92f45164273','4f215af6f59b42e9b2e3a1df4af26b7f'));

-- 删除销售单主表和明细表
delete from t_sale_item where saleOrderId in (select saleOrderId from t_sale_order where companyId in ('bf5d65b0cd7d49f2a8e9a92f45164273','4f215af6f59b42e9b2e3a1df4af26b7f'));
delete from t_sale_order where companyId in ('bf5d65b0cd7d49f2a8e9a92f45164273','4f215af6f59b42e9b2e3a1df4af26b7f');

-- 删除采购单主表和明细表
delete from t_purchase_item where purchaseOrderId in (select purchaseOrderId from t_purchase_order where companyId in ('bf5d65b0cd7d49f2a8e9a92f45164273','4f215af6f59b42e9b2e3a1df4af26b7f'));
delete from t_purchase_order where companyId in ('bf5d65b0cd7d49f2a8e9a92f45164273','4f215af6f59b42e9b2e3a1df4af26b7f');

-- 删除入库主表和明细表
delete from t_instock_item where instockMainId in (select instockMainId from t_instock_main where companyId in ('bf5d65b0cd7d49f2a8e9a92f45164273','4f215af6f59b42e9b2e3a1df4af26b7f'));
delete from t_instock_main where companyId in ('bf5d65b0cd7d49f2a8e9a92f45164273','4f215af6f59b42e9b2e3a1df4af26b7f');

-- 删除出库单主表和明细表
delete from t_outstock_item where outstockMainId in (select outstockMainId from t_outstock_main where companyId in ('bf5d65b0cd7d49f2a8e9a92f45164273','4f215af6f59b42e9b2e3a1df4af26b7f'));
delete from t_outstock_main where companyId in ('bf5d65b0cd7d49f2a8e9a92f45164273','4f215af6f59b42e9b2e3a1df4af26b7f');

-- 删除库存
delete from t_goodsStock where companyId  in ('bf5d65b0cd7d49f2a8e9a92f45164273','4f215af6f59b42e9b2e3a1df4af26b7f');
delete from t_goodsStock_history where companyId in ('bf5d65b0cd7d49f2a8e9a92f45164273','4f215af6f59b42e9b2e3a1df4af26b7f');
delete from t_productStock where companyId in ('bf5d65b0cd7d49f2a8e9a92f45164273','4f215af6f59b42e9b2e3a1df4af26b7f');
delete from t_productStock_history where companyId in ('bf5d65b0cd7d49f2a8e9a92f45164273','4f215af6f59b42e9b2e3a1df4af26b7f');

-- 删除门店销售单主表和明细表
delete from t_storeSale_item where storeSaleOrderId in (select storeSaleOrderId from t_storeSale_order where companyId in ('bf5d65b0cd7d49f2a8e9a92f45164273','4f215af6f59b42e9b2e3a1df4af26b7f'));
delete from t_storeSale_order where companyId in ('bf5d65b0cd7d49f2a8e9a92f45164273','4f215af6f59b42e9b2e3a1df4af26b7f');

-- 删除商品和商品明细表
delete from t_goods_detail where companyId in ('bf5d65b0cd7d49f2a8e9a92f45164273','4f215af6f59b42e9b2e3a1df4af26b7f') ;
delete from t_goods where companyId in ('bf5d65b0cd7d49f2a8e9a92f45164273','4f215af6f59b42e9b2e3a1df4af26b7f') ;

