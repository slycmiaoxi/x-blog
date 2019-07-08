package cn.slycmiaoxi.elasticsearch;

import java.util.Set;

import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import cn.slycmiaoxi.pagehelper.Pages;

// es工具类
public class EsUtil {

	// 初始化一个池子实例
	private static GenericObjectPool<Es> pool;

	static {
		// 池子配置文件
		GenericObjectPoolConfig config = new GenericObjectPoolConfig();
		config.setMaxTotal(10);// 整个池最大值
		config.setMaxIdle(10);// 最大空闲
		config.setMinIdle(2);// 最小空闲
		config.setMaxWaitMillis(-1);// 最大等待时间，-1表示一直等
		config.setBlockWhenExhausted(true);// 当对象池没有空闲对象时，新的获取对象的请求是否阻塞。true阻塞。默认值是true
		config.setTestOnBorrow(false);// 在从对象池获取对象时是否检测对象有效，true是；默认值是false
		config.setTestOnReturn(false);// 在向对象池中归还对象时是否检测对象有效，true是，默认值是false
		config.setTestWhileIdle(true);// 在检测空闲对象线程检测到对象不需要移除时，是否检测对象的有效性。true是，默认值是false
		config.setMinEvictableIdleTimeMillis(10 * 60000L); // 可发呆的时间,10mins
		config.setTestWhileIdle(true); // 发呆过长移除的时候是否test一下先

		pool = new GenericObjectPool<Es>(new EsFactory(), config);
	}

	/**
	 *
	 * @Description:添加文档
	 */
	public static void addDoc(String type, Object id, Object object) {
		Es es = null;
		try {
			es = pool.borrowObject();
			es.addDoc(type, id, object);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.returnObject(es);
		}
	}

	/**
	 *
	 * @Description:更新文档
	 */
	public static void updateDoc(String type, Object id, Object object) {
		Es es = null;
		try {
			es = pool.borrowObject();
			es.updateDoc(type, id, object);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.returnObject(es);
		}
	}

	/**
	 *
	 * @Description:删除文档
	 */
	public static void delDoc(String type, Object id) {
		Es es = null;
		try {
			es = pool.borrowObject();
			es.delDoc(type, id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.returnObject(es);
		}
	}

	/**
	 *
	 * @Description: 分页高亮查询
	 */
	public static Pages getDocHighLight(String keywords, String type, Set<String> fields, int currentPage, int pageSize, boolean isHighlight) {
		Es es = null;
		try {
			es = pool.borrowObject();
			return es.getDocHighLight(keywords, type, fields, currentPage, pageSize, isHighlight);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.returnObject(es);
		}
		return null;
	}

	/**
	 *
	 * @Description:重构索引
	 */
	public static void reindex() {
		Es es = null;
		try {
			es = pool.borrowObject();
			es.reindex();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.returnObject(es);
		}
	}

}