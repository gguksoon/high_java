package basic.util;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class BuildedSqlMapClient {
	
	private static SqlMapClient client;
	
	static {
		try {
			Charset charset = Charset.forName("utf-8");
			Resources.setCharset(charset);
			
			Reader rd = Resources.getResourceAsReader("sqlMapConfig.xml");
			client = SqlMapClientBuilder.buildSqlMapClient(rd);
			rd.close();
		} catch (IOException e) {
			System.out.println("SqlMapClient객체 생성 실패");
			e.printStackTrace();
		}
	}
	
	public static SqlMapClient getSqlMapClient() { 
		return client;
	}
}
