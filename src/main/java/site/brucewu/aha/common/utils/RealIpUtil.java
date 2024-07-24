package site.brucewu.aha.common.utils;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
public class RealIpUtil {

    /**
     *  x-forwarded-for：一个 HTTP 扩展头部，主要是为了让 Web 服务器获取访问用户的真实 IP 地址。每个 IP 地址，每个值通过逗号+空格分开，最左边是最原始客户端的 IP 地址，中间如果有多层代理，每⼀层代理会将连接它的客户端 IP 追加在 X-Forwarded-For 右边。
     *  Proxy-Client-IP：这个一般是经过 Apache http 服务器的请求才会有，用 Apache http 做代理时一般会加上 Proxy-Client-IP 请求头
     *  WL-Proxy-Client-IP：也是通过 Apache http 服务器，在 weblogic 插件加上的头
     * @param request 请求头拉来解析
     * @return 真实ip地址
     */
    public static String getRealIp(HttpServletRequest request) {
        String ipAddress ;
        try {
            ipAddress = request.getHeader("x-forwarded-for");
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getRemoteAddr();
                if (ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")) {
                    // 根据网卡取本机配置的IP
                    InetAddress inet = null;
                    try {
                        inet = InetAddress.getLocalHost();
                    } catch (UnknownHostException e) {
                        log.error(e.getMessage());
                    }
                    assert inet != null;
                    ipAddress = inet.getHostAddress();
                }
            }
            // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
            if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
                // = 15
                if (ipAddress.indexOf(",") > 0) {
                    ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return ipAddress="";
        }
        // ipAddress = this.getRequest().getRemoteAddr();
        return ipAddress;
    }
}
