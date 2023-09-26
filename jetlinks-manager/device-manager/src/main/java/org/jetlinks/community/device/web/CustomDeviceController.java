package org.jetlinks.community.device.web;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hswebframework.web.authorization.annotation.Authorize;
import org.hswebframework.web.authorization.annotation.Resource;
import org.hswebframework.web.crud.service.ReactiveCrudService;
import org.hswebframework.web.crud.web.reactive.ReactiveServiceCrudController;
import org.jetlinks.community.device.entity.CustomDeviceEntity;
import org.jetlinks.community.device.service.CustomDeviceService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @projectName: jetlinks-community
 * @package: org.jetlinks.community.device.web
 * @className: CustomDeviceController
 * @author: heqiu
 * @description: TODO
 * @date: 2023/9/26 10:27
 * @version: 1.0
 */
@Getter
@Setter
@RestController
@AllArgsConstructor
@Authorize(ignore = true)
@Tag(name = "自定义设备接口")
@RequestMapping("/custom/device")
@Resource(id = "customDevice", name = "自定义设备接口")
public class CustomDeviceController implements ReactiveServiceCrudController<CustomDeviceEntity, String> {

    private CustomDeviceService customDeviceService;

    @Override
    public ReactiveCrudService<CustomDeviceEntity, String> getService() {
        return customDeviceService;
    }
}