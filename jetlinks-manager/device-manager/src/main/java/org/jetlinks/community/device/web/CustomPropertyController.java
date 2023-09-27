package org.jetlinks.community.device.web;

import com.alibaba.fastjson.JSONObject;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.hswebframework.web.api.crud.entity.PagerResult;
import org.hswebframework.web.api.crud.entity.QueryParamEntity;
import org.hswebframework.web.authorization.annotation.QueryAction;
import org.hswebframework.web.authorization.annotation.Resource;
import org.hswebframework.web.crud.service.ReactiveCrudService;
import org.hswebframework.web.crud.web.reactive.ReactiveServiceCrudController;
import org.jetlinks.community.device.entity.CustomDeviceEntity;
import org.jetlinks.community.device.entity.CustomPropertyEntity;
import org.jetlinks.community.device.enums.CustomIndexEnum;
import org.jetlinks.community.device.service.CustomPropertyService;
import org.jetlinks.community.elastic.search.service.AggregationService;
import org.jetlinks.community.elastic.search.service.ElasticSearchService;
import org.jetlinks.community.timeseries.query.AggregationQueryParam;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @projectName: jetlinks-community
 * @package: org.jetlinks.community.device.web
 * @className: CustomPropertyController
 * @author: heqiu
 * @description: TODO
 * @date: 2023/9/27 10:05
 * @version: 1.0
 */
@RestController
@RequestMapping("/demo")
@AllArgsConstructor
@Resource(id = "demo", name = "自定义属性数据存取接口")
@Tag(name = "自定义属性数据存取接口")
public class CustomPropertyController implements ReactiveServiceCrudController<CustomPropertyEntity, String> {

    //引入service
    private final CustomPropertyService customPropertyService;
    //引入es
    private final ElasticSearchService elasticSearchService;
    //引入es

    //引入聚合查询AggregationService
    private final AggregationService aggregationService;


    /**
     * 分页查询
     * @param _index 索引
     * @param queryParam  查询参数
     * @return
     */
    @PostMapping("/es/query/{_index}")
    @QueryAction
    public Mono<PagerResult<CustomPropertyEntity>> queryDataByIndex(@PathVariable String _index, QueryParamEntity queryParam) {
        return elasticSearchService.queryPager(_index, queryParam, map -> JSONObject.parseObject(JSONObject.toJSONString(map), CustomPropertyEntity.class));

    }

    /**
     * 聚合查询es数据
     * @param _index  索引
     * @param aggregationQueryParam  聚合查询条件
     * @return
     */
    @PostMapping("/es/agg/query/{_index}")
    @QueryAction
    public Flux<CustomPropertyEntity> aggQueryDataByIndex(@PathVariable @org.springframework.lang.NonNull String _index, @RequestBody AggregationQueryParam aggregationQueryParam) {

        return aggregationService.aggregation(_index, aggregationQueryParam)
                .map(map -> {
                    return JSONObject.parseObject(JSONObject.toJSONString(map), CustomPropertyEntity.class);
                });

    }

    /**
     * 查询ES记录数
     * @param _index  索引
     * @param queryParam  查询条件
     * @return
     */
    @PostMapping("/count/{_index}")
    @QueryAction
    public Mono<Long> countData(@PathVariable String _index, @Parameter QueryParamEntity queryParam) {
        return elasticSearchService.count(_index, queryParam);
    }
    /**
     * 将参数实体内容存储到es中
     * @param entity 存储参数实体
     * @return
     */
    @PostMapping("/es/save")
    @QueryAction
    public Mono<CustomPropertyEntity> saveData(@RequestBody @NonNull CustomPropertyEntity entity) {
        //设置保存时时间戳信息
        entity.setTimestamp(System.currentTimeMillis());
        return elasticSearchService.save(CustomIndexEnum.custom.getIndex(), entity)
                .thenReturn(entity);

    }
    @Override
    public ReactiveCrudService<CustomPropertyEntity, String> getService() {
        return customPropertyService;
    }
}