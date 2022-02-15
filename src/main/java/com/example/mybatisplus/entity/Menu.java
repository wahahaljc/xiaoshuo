package com.example.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author ljc
 * @since 2021-04-03
 */
public class Menu extends Role implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;


    /**
     * 父ID
     */
    private Integer patentId;

    /**
     * 标题
     */
    private String title;

    /**
     * 图标路径
     */
    private String icon;

    /**
     * 网页路由
     */
    private String path;

    /**
     * 权限标识
     */
    private String authority;

    /**
     * 菜单类型（0菜单，1按钮)
     */
    @TableField("menu_Type")
    private Integer menuType;

    /**
     * 排序号
     */
    @TableField("sortNumber")
    private Integer sortNumber;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPatentId() {
        return patentId;
    }

    public void setPatentId(Integer patentId) {
        this.patentId = patentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public Integer getMenuType() {
        return menuType;
    }

    public void setMenuType(Integer menuType) {
        this.menuType = menuType;
    }

    public Integer getSortNumber() {
        return sortNumber;
    }

    public void setSortNumber(Integer sortNumber) {
        this.sortNumber = sortNumber;
    }

    @Override
    public String toString() {
        return "Menu{" +
        "id=" + id +
        ", patentId=" + patentId +
        ", title=" + title +
        ", icon=" + icon +
        ", path=" + path +
        ", authority=" + authority +
        ", menuType=" + menuType +
        ", sortNumber=" + sortNumber +
        "}";
    }
}
