-- 创建产品明细视图 CREATE OR REPLACE VIEW   GROUP BY p.productId;  
CREATE OR REPLACE VIEW v_productDetail  AS 
SELECT p.productId,d.productStandardDetailId,p.productName,s.productStandardName,d.productStandardNum ,p.companyId as supplierCompanyId
from t_product_standard_detail d
INNER JOIN t_product p  on p.productId = d.productId
INNER JOIN t_product_standard s  on s.productStandardId = d.productStandardId;