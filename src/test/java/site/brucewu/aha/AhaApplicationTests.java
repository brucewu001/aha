package site.brucewu.aha;

import com.maxmind.db.CHMCache;
import com.maxmind.db.NodeCache;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.model.CityResponse;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


//@SpringBootTest
class AhaApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void geolite2() {

		NodeCache nodeCache = new CHMCache();

		// IP V4
		String ip = "113.87.18.136";
		// IP V6 也是可以的
		DatabaseReader reader = null;
		CityResponse response = null;
		try {
			ClassLoader classLoader = getClass().getClassLoader();
			URL url = classLoader.getResource("GeoLite2-City.mmdb");
			if(url == null) {
				throw new IOException();
			}
			File database = new File(url.getFile());

			List<String> list = new ArrayList<>();

			list.add("zh-CN");

			// 读取数据库内容
			reader = new DatabaseReader.Builder(database).withCache(nodeCache).locales(list).build();
			InetAddress ipAddress = InetAddress.getByName(ip);
			// 获取查询结果
			response = reader.city(ipAddress);
			System.out.println(response.getCity().getName());
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();

				}
			}
		}
	}

}
