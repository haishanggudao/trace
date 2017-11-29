-- 系统参数表
insert into sys_variable (variableId,varkey,varValue,status) values (upper(replace(uuid(),'-','')),'QRCodePrev','http://192.168.8.39:8080/trace/ws/getTrace/',0);
insert into sys_variable (variableId,varkey,varValue,status) values (upper(replace(uuid(),'-','')),'WebPath','http://192.168.8.39:8080/img/',0);
insert into sys_variable (variableId,varkey,varValue,status) values (upper(replace(uuid(),'-','')),'strogePath','/usr/local/apache-tomcat-8.0.24/webapps/img/',0);
insert into sys_variable (variableId,varkey,varValue,status) values (upper(replace(uuid(),'-','')),'tmp','/tmp/',0);
insert into sys_variable (variableId,varkey,varValue,status) values (upper(replace(uuid(),'-','')),'upload','http://192.168.8.39:8080/upload/',0);
insert into sys_variable (variableId,varkey,varValue,status) values (upper(replace(uuid(),'-','')),'DefaultCompanyFieldId','01A3ADB3076B11E690B100505697467E',0);

insert into sys_variable (variableId,varkey,varValue,status) values (upper(replace(uuid(),'-','')),'QRCodeWidth','125',0);
insert into sys_variable (variableId,varkey,varValue,status) values (upper(replace(uuid(),'-','')),'QRCodeHeigth','125',0);