package cn.slycmiaoxi.elasticsearch;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.slycmiaoxi.pagehelper.Pages;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import com.alibaba.fastjson.JSON;



public class Es {

	// 连接
	private static TransportClient client;
	// 索引库名称，一般都是一个库，只是里边的type不同
	private static final String index = "";

	private static String host=""; // 服务器地址
	private static int port=9300; // 端口

	public Es() {
		try {
			client = new PreBuiltTransportClient(Settings.EMPTY)
					.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(host),port));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	/**
	 *
	 * @Description: 关闭连接
	 */
	public void close() {
		client.close();
	}

	/**
	 *
	 * @Description: 验证链接是否正常
	 */
	public boolean validate() {
		return client.connectedNodes().size() == 0 ? false : true;
	}

	/**
	 *
	 * @Description:添加文档
	 */
	public void addDoc(String type, Object id, Object object) {
		client.prepareIndex(index, type, id.toString()).setSource(JSON.toJSONString(object)).get();
	}

	/**
	 *
	 * @Description:更新文档
	 */
	public void updateDoc(String type, Object id, Object object) {
		client.prepareUpdate(index, type, id.toString()).setDoc(JSON.toJSONString(object)).get();
	}

	/**
	 *
	 * @Description:删除文档
	 */
	public void delDoc(String type, Object id) {
		client.prepareDelete(index, type, id.toString()).get();
	}

	/**
	 *
	 * @Description: 分页高亮查询
	 * @param fields 查询的字段集合
	 */
	public Pages getDocHighLight(String keywords, String type, Set<String> fields, int currentPage, int pageSize, boolean isHighlight) throws Exception {
		// 搜索数据
		SearchResponse response = client.prepareSearch(index).setTypes(type)//
				.setSearchType(SearchType.DFS_QUERY_THEN_FETCH)//
				.setQuery(QueryBuilders.multiMatchQuery(keywords, fields.toArray(new String[fields.size()]))// 查询所有字段
						.analyzer("standard"))// 分词器
				.highlighter(new HighlightBuilder().preTags("<span style=\"color:red\">").postTags("</span>").field("*"))// 高亮标签
				.setFrom((currentPage - 1) * pageSize).setSize(pageSize)// 分页
				.setExplain(true)// 评分排序
				.execute().actionGet();

		// 获取查询结果集
		SearchHits searchHits = response.getHits();
		List<Object> result = new ArrayList<Object>();
		// 反射填充高亮
		for (SearchHit hit : searchHits) {
			Map<String, Object> source = hit.getSource();
			if (isHighlight) {
				// 获取对应的高亮域
				Map<String, HighlightField> highlight = hit.getHighlightFields();
				for (String field : fields) {
					// 从设定的高亮域中取得指定域
					HighlightField titleField = highlight.get(field);
					if (titleField == null) continue;
					// 取得定义的高亮标签
					String texts = StringUtils.join(titleField.fragments());
					source.put(field, texts);
				}
				source.put("item",hit.getId());
			}
			result.add(source);
		}
		return new Pages(currentPage, pageSize, (int) searchHits.totalHits(), result);
	}

	/**
	 *
	 * @Description: 重构索引(更新词库之后)
	 */
	public void reindex() {
		SearchResponse scrollResp = client.prepareSearch(index)//
				.setScroll(new TimeValue(60000))//
				.setQuery(QueryBuilders.matchAllQuery())//
				.setSize(100).get(); // max of 100 hits will be returned for
		// Scroll until no hits are returned
		do {
			for (SearchHit hit : scrollResp.getHits().getHits()) {
				client.prepareIndex(index, hit.getType(), hit.getId()).setSource(hit.getSourceAsString()).execute().actionGet();
			}
			scrollResp = client.prepareSearchScroll(scrollResp.getScrollId()).setScroll(new TimeValue(60000)).execute().actionGet();
		} while (scrollResp.getHits().getHits().length != 0);
	}

}
