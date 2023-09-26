package org.jetlinks.community.device.web;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hswebframework.web.authorization.annotation.Authorize;
import org.hswebframework.web.authorization.annotation.Resource;
import org.hswebframework.web.crud.service.ReactiveCrudService;
import org.hswebframework.web.crud.web.reactive.ReactiveServiceCrudController;
import org.jetlinks.community.device.entity.CustomProductEntity;
import org.jetlinks.community.device.service.CustomProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * @projectName: jetlinks-community
 * @package: org.jetlinks.community.device.web
 * @className: CustomProductController
 * @author: heqiu
 * @description: TODO
 * @date: 2023/9/26 10:24
 * @version: 1.0
 */

@Getter
@Setter
@RestController
@AllArgsConstructor
@Authorize(ignore = true)
@Tag(name = "自定义产品接口")
@RequestMapping("/custom/product")
@Resource(id = "customProduct", name = "自定义产品接口")
public class CustomProductController implements ReactiveServiceCrudController<CustomProductEntity, String> {

    private CustomProductService customProductService;

    @Override
    public ReactiveCrudService<CustomProductEntity, String> getService() {
        return customProductService;
    }

    /**
     * 查询设备信息，并按照产品productId分组
     *
     * @return
     */
    @GetMapping("/getDevice")
    public Flux<CustomProductEntity> getDeviceGroupByProductId() {
        return customProductService.getDeviceGroupByProductId();
    }
}