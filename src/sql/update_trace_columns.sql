-- 2016-06-22 add column-s.mode
update t_trace_column
set traceColumns = concat(traceColumns, ', s.mode')
where companyCode = '310000446'
and traceType = 'slaughterhouseInfo1';