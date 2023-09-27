package org.jetlinks.community.device.configuration;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;
import org.jetlinks.community.device.enums.CustomIndexEnum;
import org.jetlinks.community.elastic.search.index.DefaultElasticSearchIndexMetadata;
import org.jetlinks.community.elastic.search.index.ElasticSearchIndexManager;
import org.jetlinks.core.metadata.types.DateTimeType;
import org.jetlinks.core.metadata.types.StringType;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @projectName: jetlinks-community
 * @package: org.jetlinks.community.device.configuration
 * @className: Configurations
 * @author: heqiu
 * @description: TODO
 * @date: 2023/9/27 10:09
 * @version: 1.0
 */
@Setter
@Component
@AllArgsConstructor
public class Configurations implements CommandLineRunner {
    //引入indexManager
    private final ElasticSearchIndexManager indexManager;

    @SneakyThrows
    @Override
    public void run(String... args) {
        indexManager.putIndex(
                //设置es自定义模板名称
                new DefaultElasticSearchIndexMetadata(CustomIndexEnum.custom.getIndex())
                        //添加自定义模板属性
                        .addProperty("device_id", new StringType())
                        .addProperty("property", new StringType())
                        .addProperty("timestamp", new DateTimeType())
        ).subscribe();
    }

}