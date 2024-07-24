package site.brucewu.aha.config.geoip2;

import com.maxmind.db.CHMCache;
import com.maxmind.db.NodeCache;
import com.maxmind.geoip2.DatabaseReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Configuration
public class DatabaseDatabaseReaderConfig {

    @Bean
    public DatabaseReader databaseReader() {
        NodeCache nodeCache = new CHMCache();

        // IP V4
        String ip = "113.87.18.136";
        // IP V6 也是可以的
        DatabaseReader reader = null;

        List<String> list = new ArrayList<>();
        list.add("zh-CN");

        try {
            ClassPathResource resource = new ClassPathResource("GeoLite2-City.mmdb");
//            URL url = classLoader.getResource();
//            if (url == null) {
//                throw new IOException();
//            }
//            File database = new File(url.getFile());
            // 读取数据库内容
            reader = new DatabaseReader.Builder(resource.getInputStream()).withCache(nodeCache).locales(list).build();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return reader;
    }

}
