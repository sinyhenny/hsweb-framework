/*
 *  Copyright 2019 http://www.hswebframework.org
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 *
 */

package org.hswebframework.web.entity.authorization;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hswebframework.web.commons.entity.SimpleTreeSortSupportEntity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 菜单实体
 *
 * @author zhouhao
 * @since 3.0
 */
@Getter
@Setter
@NoArgsConstructor
@Table(name = "s_menu")
public class SimpleMenuEntity extends SimpleTreeSortSupportEntity<String>
        implements MenuEntity {

    private static final long serialVersionUID = 6942822850955576468L;
    //菜单名称
    @Column
    private String name;

    //备注
    @Column
    private String describe;

    //权限ID
    @Column(name = "permission_id")
    private String permissionId;

    //菜单对应的url
    @Column(length = 1024)
    private String url;

    //图标
    @Column(length = 32)
    private String icon;

    //状态
    @Column
    private Byte status;

    //子菜单
    private List<MenuEntity> children;

    @Override
    public SimpleMenuEntity clone() {
        SimpleMenuEntity target = (SimpleMenuEntity) super.clone();
        target.setProperties(cloneProperties());
        if (null != getChildren()) {
            target.setChildren(getChildren().stream()
                    .map(MenuEntity::clone)
                    .collect(Collectors.toList()));
        }
        return target;
    }

    @Override
    @Id
    @Column(name = "u_id")
    public String getId() {
        return super.getId();
    }
}