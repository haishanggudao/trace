package cn.rfidcer.util;

import java.sql.Timestamp;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.web.multipart.MultipartFile;

import cn.rfidcer.bean.BaseEntity;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.User;

public class CommonImportUtils {

	public static void setCreateAndUpdateTime(BaseEntity baseEntity,User user,Timestamp time){
		baseEntity.setCreateBy(user.getId());
		baseEntity.setCreateTime(time);
		baseEntity.setUpdateBy(user.getId());
		baseEntity.setUpdateTime(time);
	}
	public static void setCreateAndUpdateTime(BaseEntity baseEntity){
		baseEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));
		baseEntity.setUpdateTime(new Timestamp(System.currentTimeMillis()));
	}
	public static void setCreateAndUpdateTime(BaseEntity baseEntity,User user){
		Timestamp time = new Timestamp(System.currentTimeMillis());
		setCreateAndUpdateTime(baseEntity, user, time);
	}
	public static void setUpdateTime(BaseEntity baseEntity,User user){
		Timestamp time = new Timestamp(System.currentTimeMillis());
		baseEntity.setCreateBy(null);
		baseEntity.setCreateTime(null);
		baseEntity.setUpdateBy(user.getId());
		baseEntity.setUpdateTime(time);
	}
	
	public static ResultMsg checkImportFile(MultipartFile importFile){
		ResultMsg msg = new ResultMsg();
		if (importFile == null || importFile.getSize() == 0) {
			msg.setCode("-1");
			msg.setMsg("导入文件为空");
			return msg;
		}
		String name = importFile.getOriginalFilename();
		if (!name.endsWith(".xls")) {
			msg.setCode("-2");
			msg.setMsg("文件格式不为xls");
		}
		return msg;
	}
	
	
	public static void validateFirstRow(Row row,List<String> list) throws Exception {
		for(int j=0;j<list.size();j++){
			Cell cell = row.getCell(j);
			String cellVal = cell.getStringCellValue().trim();
			if(cell==null||!cellVal.equals(list.get(j))){
				throw new Exception("首行模板格式不正确，请下载正确的导入模板");
			}
		}
	}
	
	public static boolean isBlankRow(Row row,int cellnum){
		for (int i = 0; i < cellnum; i++) {
			Cell cell = row.getCell(i);
			if(cell!=null&&cell.toString()!=null&&cell.toString().trim().length()!=0){
				return false;
			}
		}
		return true;
	}
	public static boolean isBlankCell(Cell cell){
		if(cell!=null&&cell.toString()!=null&&cell.toString().trim().length()!=0){
			return false;
		}
		return true;
	}
}
