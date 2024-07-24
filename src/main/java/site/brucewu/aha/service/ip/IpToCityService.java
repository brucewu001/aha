package site.brucewu.aha.service.ip;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.model.CityResponse;
import org.springframework.stereotype.Service;

import java.net.InetAddress;

@Service
public record IpToCityService(DatabaseReader databaseReader) {

    /**
     * ip转城市名
     * @param ip ipv4 还是 ipv6 你随便传
     * @return cityName 城市名
     */
    public String getCityByIp(String ip) {

        try{
            InetAddress address = InetAddress.getByName(ip);

            CityResponse city = databaseReader.city(address);

            if(city != null) {
                return city.getCity().getName();
            }

        }catch (Exception e) {
            return "未知";
        }
        return "未知";
    }
}
