package cn.tedu.sp11.filter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import cn.tedu.web.util.JsonResult;
/**
 * 	设置过滤器
 * @author Administrator
 *
 */
@Component
public class AccessFilter extends ZuulFilter{
	/**
	 * 对指定的service进行过滤返回值为true则表示过滤
	 * 否则表示不对此进行过滤
	 */
	@Override
	public boolean shouldFilter() {
		RequestContext ctx = RequestContext.getCurrentContext();
		String serviceId = (String)ctx.get(FilterConstants.SERVICE_ID_KEY);//获取请求中的serviceid
		if(serviceId.equals("item-service")) {
			return true;
		}
		return false;
	}
	/**
	 * 设置权限通过   过滤的请求获取是否有权限进行该方法
	 */
	@Override
	public Object run() throws ZuulException {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		String token = request.getParameter("token");
		if(token==null||token.length()==0) {
			ctx.setSendZuulResponse(false);
			ctx.setResponseStatusCode(200);
			ctx.setResponseBody(JsonResult.err().code(JsonResult.NOT_LOGIN).toString());
		}
		
		return null;
	}

	@Override
	public String filterType() {
		// TODO Auto-generated method stub
		return FilterConstants.PRE_TYPE;
	}

	@Override
	public int filterOrder() {
		return 6;
	}
	
}
