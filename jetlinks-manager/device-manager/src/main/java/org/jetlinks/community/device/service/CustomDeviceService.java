package org.jetlinks.community.device.service;

import lombok.AllArgsConstructor;
import org.hswebframework.web.crud.service.GenericReactiveCrudService;
import org.jetlinks.community.device.entity.CustomDeviceEntity;
import org.springframework.stereotype.Service;

/**
 * @projectName: jetlinks-community
 * @package: org.jetlinks.community.device.service
 * @className: CustomDeviceService
 * @author: heqiu
 * @description: TODO
 * @date: 2023/9/26 10:22
 * @version: 1.0
 */
@Service
@AllArgsConstructor
public class CustomDeviceService extends GenericReactiveCrudService<CustomDeviceEntity, String> {
}