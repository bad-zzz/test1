package cn.tedu.sp11.fallback;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import cn.tedu.web.util.JsonResult;

@Component
public class OrderServiceFallback implements FallbackProvider {

	@Override
	public String getRoute() {
		return "order-service";
	}

	@Override
	public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
		return response();
	}

	private ClientHttpResponse response() {
		return new ClientHttpResponse() {
			
			@Override
			public HttpHeaders getHeaders() {
				HttpHeaders http = new HttpHeaders();
				http.setContentType(MediaType.APPLICATION_JSON);
				return http;
			}
			
			@Override
			public InputStream getBody() throws IOException {
				String string = JsonResult.err("请求购物车失败").toString();
				
				return new ByteArrayInputStream(string.getBytes("utf-8"));
			}
			
			@Override
			public String getStatusText() throws IOException {
				return HttpStatus.OK.getReasonPhrase();
			}
			
			@Override
			public HttpStatus getStatusCode() throws IOException {
				return HttpStatus.OK;
			}
			
			@Override
			public int getRawStatusCode() throws IOException {
				return HttpStatus.OK.value();
			}
			
			@Override
			public void close() {
				
			}
		};
	}

}
