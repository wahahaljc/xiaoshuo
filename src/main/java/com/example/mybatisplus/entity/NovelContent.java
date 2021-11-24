package com.example.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author ljc
 * @since 2021-04-03
 */
public class NovelContent implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 小说id
     */
    private Integer nId;

    /**
     * 小说内容
     */
    private String content;

    /**
     * 小说章节
     */
    private String chaptersandsections;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getnId() {
        return nId;
    }

    public void setnId(Integer nId) {
        this.nId = nId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getChaptersandsections() {
        return chaptersandsections;
    }

    public void setChaptersandsections(String chaptersandsections) {
        this.chaptersandsections = chaptersandsections;
    }

    @Override
    public String toString() {
        return "NovelContent{" +
        "id=" + id +
        ", nId=" + nId +
        ", content=" + content +
                ", chaptersandsections=" + chaptersandsections +
        "}";
    }
}
