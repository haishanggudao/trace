--创建产品信息视图
CREATE VIEW v_productInfoAreDetailed AS
SELECT  DISTINCT p.productId,p.productName,p.companyId
FROM t_product  p
INNER JOIN  t_product_standard_detail d on p.productId=d.productId
INNER JOIN t_product_standard sd on sd.productStandardId=d.productStandardId 
ORDER BY p.createTime DESC


