package org.jetlinks.community.device.service;

import lombok.AllArgsConstructor;
import org.hswebframework.web.crud.service.GenericReactiveCrudService;
import org.jetlinks.community.device.entity.CustomDeviceEntity;
import org.jetlinks.community.device.entity.CustomProductEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @projectName: jetlinks-community
 * @package: org.jetlinks.community.device.service
 * @className: CustomProductService
 * @author: heqiu
 * @description: TODO
 * @date: 2023/9/26 10:21
 * @version: 1.0
 */

@Service
@AllArgsConstructor
public class CustomProductService extends GenericReactiveCrudService<CustomProductEntity, String> {


    private final CustomDeviceService customDeviceService;

    public Flux<CustomProductEntity> getDeviceGroupByProductId() {
        return this
            .createQuery()
            .fetch()
            .collectList()
            .flatMapMany(productList -> {
                List<String> list = productList.stream()
                                               .map(CustomProductEntity::getProductId)
                                               .collect(Collectors.toList());

                return customDeviceService
                    .createQuery()
                    .in("product_id", list)
                    .fetch()
                    .collect(Collectors.toList())
                    .map(deviceList -> deviceList.stream()
                                                 .collect(Collectors.groupingBy(CustomDeviceEntity::getProductId)))
                    .flatMapMany(map -> {
                        productList.forEach(product -> product.setDevices(map.get(product.getProductId())));
                        return Flux.fromIterable(productList);
                    });

            });
    }

}
