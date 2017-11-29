-- 2016-8-4 删除重复tracecode
DELETE from t_goods_detail where goodsDetailId in (select goodsDetailId from (select code,count(*) c,MAX(goodsDetailId) goodsDetailId from t_goods_detail GROUP BY code HAVING c>1) a);
-- 插入已有商品追溯码数据
insert into t_trace (traceCode,type,createBy,createTime)
select code,0,createBy,now() from t_goods where code is not null and code!='';
-- 插入非团餐商品明细追溯码
insert into t_trace (traceCode,type,createBy,createTime) 
select d.code,1,d.createBy,NOW() from t_goods_detail d 
INNER JOIN t_goods g on d.goodsId=g.goodsId 
where g.deliverType=0;
-- 插入团餐商品明细追溯码
insert into t_trace (traceCode,type,createBy,createTime) 
select d.code,2,d.createBy,NOW() from t_goods_detail d 
INNER JOIN t_goods g on d.goodsId=g.goodsId 
where g.deliverType!=0;
-- 插入销售单商品追溯码
insert into t_trace (traceCode,type,createBy,createTime) 
select goodsTraceCode,3,createBy,NOW() c from t_storeSale_item
where  goodsTraceCode is not null and goodsTraceCode!='';

