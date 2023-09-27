package org.jetlinks.community.device.entity;

import lombok.Getter;
import lombok.Setter;
import org.hswebframework.ezorm.rdb.mapping.annotation.Comment;
import org.hswebframework.web.api.crud.entity.GenericEntity;
import org.hswebframework.web.api.crud.entity.RecordCreationEntity;
import org.hswebframework.web.api.crud.entity.RecordModifierEntity;

import javax.persistence.Column;
import javax.persistence.Index;
import javax.persistence.Table;

/**
 * @projectName: jetlinks-community
 * @package: org.jetlinks.community.device.entity
 * @className: CustomPropertyEntity
 * @author: heqiu
 * @description: TODO
 * @date: 2023/9/27 10:03
 * @version: 1.0
 */
@Table(name = "custom_property_entity", indexes = {
        @Index(name = "idx_property", columnList = "property")
})
@Comment("自定义属性信息表")
@Setter
@Getter
public class CustomPropertyEntity extends GenericEntity<String> implements
        RecordCreationEntity, RecordModifierEntity {

    @Column(name = "device_id")
    private String deviceId;

    @Column(name = "property")
    private String property;

    @Column(name = "value")
    private String value;

    private long timestamp;

    @Override
    public String getCreatorId() {
        return null;
    }

    @Override
    public void setCreatorId(String creatorId) {

    }

    @Override
    public Long getCreateTime() {
        return null;
    }

    @Override
    public void setCreateTime(Long createTime) {

    }

    @Override
    public String getModifierId() {
        return null;
    }

    @Override
    public void setModifierId(String modifierId) {

    }

    @Override
    public Long getModifyTime() {
        return null;
    }

    @Override
    public void setModifyTime(Long modifyTime) {

    }
}
