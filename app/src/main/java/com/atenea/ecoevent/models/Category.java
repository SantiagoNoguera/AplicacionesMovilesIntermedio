package com.atenea.ecoevent.models;

public class Category {

    private String name;
    private Integer total;
    private Float media;
    private Integer meta;
    private Integer missing;

    public Category(String name, Integer total, Float media, Integer meta, Integer missing) {
        this.name = name;
        this.total = total;
        this.media = media;
        this.meta = meta;
        this.missing = missing;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Float getMedia() {
        return media;
    }

    public void setMedia(Float media) {
        this.media = media;
    }

    public Integer getMeta() {
        return meta;
    }

    public void setMeta(Integer meta) {
        this.meta = meta;
    }

    public Integer getMissing() {
        return missing;
    }

    public void setMissing(Integer missing) {
        this.missing = missing;
    }

}
