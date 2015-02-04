package cn.edu.buaa.g305.qpm.spc.domain;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
public class SpcXR extends SpcXRLinks{
	
	//本类继承SpcXRLinks的所有field(除了links)
	//为了持久化时不持久化随时可变的links
	
	@Override
	
	public String toString()
	{
		String string="{id:"+id+","+
	"organization:"+organization+","+
				"project:"+project+","+
                "name:"+ name+","+
                "type:"+ type+"spcXR"+","+
                "stauts:"+       stauts+","+
                "in:" +   in+","+
                "out:" +   out+"}";
		return string;
		
	}

}
