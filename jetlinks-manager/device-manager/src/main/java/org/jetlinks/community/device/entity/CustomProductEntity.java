package org.jetlinks.community.device.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.hswebframework.ezorm.rdb.mapping.annotation.Comment;
import org.hswebframework.ezorm.rdb.mapping.annotation.DefaultValue;
import org.hswebframework.web.api.crud.entity.GenericEntity;
import org.hswebframework.web.api.crud.entity.RecordCreationEntity;
import org.hswebframework.web.api.crud.entity.RecordModifierEntity;
import org.hswebframework.web.crud.generator.Generators;

import javax.persistence.Column;
import javax.persistence.Index;
import javax.persistence.Table;
import java.util.List;

/**
 * @projectName: jetlinks-community
 * @package: org.jetlinks.community.device.entity
 * @className: CustomProductEntity
 * @author: heqiu
 * @description: TODO
 * @date: 2023/9/26 10:14
 * @version: 1.0
 */
@Table(name = "dev_custom_product_entity", indexes = {
    @Index(name = "idx_product_id", columnList = "product_id")
})
@Comment("自定义产品信息表")
@Setter
@Getter
public class CustomProductEntity extends GenericEntity<String> implements
    RecordCreationEntity, RecordModifierEntity {


    @Column(name = "product_id")
    @Comment("产品ID")
    String productId;


    @Column(name = "product_name")
    @Comment("产品名称")
    String productName;

    @Column(name = "device_type")
    @Comment("设备类型")
    String deviceType;

    List<CustomDeviceEntity> devices;

    @Column(name = "creator_id", updatable = false)
    @Schema(
        description = "创建者ID(只读)"
        , accessMode = Schema.AccessMode.READ_ONLY
    )
    private String creatorId;

    @Column(name = "creator_name", updatable = false)
    @Schema(
        description = "创建者名称(只读)"
        , accessMode = Schema.AccessMode.READ_ONLY
    )
    private String creatorName;

    @Column(name = "create_time", updatable = false)
    @DefaultValue(generator = Generators.CURRENT_TIME)
    @Schema(
        description = "创建时间(只读)"
        , accessMode = Schema.AccessMode.READ_ONLY
    )
    private Long createTime;

    @Column
    @DefaultValue(generator = Generators.CURRENT_TIME)
    @Schema(
        description = "修改时间"
        , accessMode = Schema.AccessMode.READ_ONLY
    )
    private Long modifyTime;

    @Column(length = 64)
    @Schema(
        description = "修改人ID"
        , accessMode = Schema.AccessMode.READ_ONLY
    )
    private String modifierId;

    @Column(length = 64)
    @Schema(
        description = "修改人名称"
        , accessMode = Schema.AccessMode.READ_ONLY
    )
    private String modifierName;


}