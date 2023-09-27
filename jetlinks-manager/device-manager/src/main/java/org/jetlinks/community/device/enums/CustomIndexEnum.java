package org.jetlinks.community.device.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @projectName: jetlinks-community
 * @package: org.jetlinks.community.device.enums
 * @className: CustomIndexEnum
 * @author: heqiu
 * @description: TODO
 * @date: 2023/9/27 10:08
 * @version: 1.0
 */
@Getter
@AllArgsConstructor
public enum CustomIndexEnum {

    //枚举值，包含一个index属性，该属性表示索引名称。
    custom("custom_index");

    private String index;
}