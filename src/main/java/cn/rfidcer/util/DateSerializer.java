package cn.rfidcer.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**spring返回日期时指定格式
 * @author xzm
 *
 */
public class DateSerializer extends JsonSerializer<Date>{

	@Override
	public void serialize(Date value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 gen.writeString(sdf.format(value));
		
	}


}
