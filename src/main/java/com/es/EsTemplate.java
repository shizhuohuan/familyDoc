package com.es;

import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.WildcardQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.ScoreSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;

public class EsTemplate<T> {
    Logger logger = Logger.getLogger(EsTemplate.class);

    private String indexName = "familydoc";
    Client client = null;

    public static EsTemplate esTemplate() {
        return new EsTemplate();
    }

    // 关键字搜索 指定匹配类型
    public List<Map<String, Object>> search(String type, String keyword) {

        try {
            long start = System.currentTimeMillis();
            if (client == null) {
                client = EsUtil.getTransportClient();
            }

            SearchRequestBuilder responsebuilder = client.prepareSearch(indexName).setTypes(type);
            List<Map<String, Object>> hitsList = new ArrayList<>();
            HighlightBuilder highlightBuilder = new HighlightBuilder().field("*").requireFieldMatch(false);
            highlightBuilder.preTags("<span style=\"color:red\">");
            highlightBuilder.postTags("</span>");
            SearchRequestBuilder searchRequestBuilder = responsebuilder
                    .addSort(new ScoreSortBuilder().order(SortOrder.DESC))
                    .setQuery(QueryBuilders.queryStringQuery(keyword))
                    .setFrom(0)//from 从哪条开始 ，可用于分页操作
                    .setSize(10)
                    .highlighter(highlightBuilder)
                    .setExplain(true);//number为查询的条数
            SearchResponse myresponse = searchRequestBuilder.get();
            SearchHits hits = myresponse.getHits();
            Map<String, Object> result = new HashMap<>();
            for (int i = 0; i < hits.getTotalHits(); i++) // getHits()当前查询页的结果
            {
                SearchHit hit = hits.getHits()[i];
                result = hit.getSourceAsMap();
                //创建map 存放高亮字段 并返回到对象中
                Map<String, HighlightField> highlightFields = hit.getHighlightFields();
                for(String hlKey:highlightFields.keySet()){
                    result.remove(hlKey);
                    String content = "";
                    for(Text t:highlightFields.get(hlKey).fragments()){
                        content += t;
                    }
                    result.put(hlKey,content);
                }
                hitsList.add(result);
            }
            long end = System.currentTimeMillis();
            logger.debug("为您找到相关结果约" + hits.getTotalHits() + "个----" + "耗时" + (end - start) / 1000 + "秒");
            return hitsList;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            EsUtil.returnClient(client);
            client = null;
        }
        return null;
    }

    /* 根据id的值删除文档
     * @param id
     * @param INDEX_TYPE
     * @throws UnknownHostException
     */
    public void removeOneById(String id, String INDEX_TYPE) {
        try {
            if (client == null) {
                client = EsUtil.getTransportClient();
            }
            DeleteResponse response = client.prepareDelete(indexName, INDEX_TYPE, String.valueOf(id)).get();
            String index = response.getIndex();
            String type = response.getType();
            String typeId = response.getId();
            long version = response.getVersion();
            logger.debug("删除一条文档" + index + " : " + type + ": " + typeId + ": " + version);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            EsUtil.returnClient(client);
            client = null;
        }


    }


    /**
     * 创建一条或者更新索引
     */
    public void upsertDocument(T pojo, String INDEX_TYPE) {
        if (client == null) {
            client = EsUtil.getTransportClient();
        }
        Gson gson = new Gson();
        Field FieldId;
        try {
            Class clazz = pojo.getClass();
            try {
                FieldId = clazz.getDeclaredField("id");
            } catch (Exception e) {
                FieldId = clazz.getSuperclass().getDeclaredField("id");
            }
            FieldId.setAccessible(true);
            Object id = FieldId.get(pojo);
            IndexResponse indexRes = client.prepareIndex(indexName, INDEX_TYPE).setId(String.valueOf(id))
                    .setSource(gson.toJson(pojo), XContentType.JSON).get();
            String index = indexRes.getIndex();
            String type = indexRes.getType();
            String typeId = indexRes.getId();
            System.out.println("插入成功" + index + " : " + type + ": " + typeId + ": ");
        } catch (NoSuchFieldException | SecurityException e) {
            // TODO Auto-generated catch block
            logger.warn("插入失败");
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            logger.warn("插入失败");
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            logger.warn("插入失败");
            e.printStackTrace();
        } finally {
            EsUtil.returnClient(client);
            client = null;
        }
    }


    public void HightLight(SearchRequestBuilder searchRequestBuilder) {
        Map<String, Object> msgMap = new HashMap<String, Object>();
        HighlightBuilder highlightBuilder = new HighlightBuilder().field("*").requireFieldMatch(false);
        highlightBuilder.preTags("<span style=\"color:red\">");
        highlightBuilder.postTags("</span>");
        searchRequestBuilder.highlighter(highlightBuilder);
        SearchResponse response = searchRequestBuilder.get();
        List<Map<String, Object>> result = new ArrayList<>();

        for (SearchHit hit : response.getHits()) // getHits()当前查询页的结果
        {
            Map<String, Object> source = hit.getSource();
            //创建map 存放高亮字段 并返回到对象中
            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            //处理高亮字段
            HighlightField nameField = highlightFields.get("title");
            if (nameField != null) {

                Text[] fragments = nameField.fragments();
                String nameTmp = "";
                for (Text text : fragments) {
                    nameTmp += text;
                    source.put("title", nameTmp);
                    source.put("content", nameTmp);
                }
                source.put("title", nameTmp);

            }
            result.add(source);
        }
        //封装数据返回
        msgMap.put("itemsList", result);     //搜索结果
        //msgMap.put("page","page");          //分页
        msgMap.put("took", response.getTook().getSecondsFrac()); //获取响应需要的时间
    }
}
