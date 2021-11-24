package com.example.mybatisplus.entity;

import com.example.mybatisplus.utils.upload.ExcelExport;

public class zebxt {
    @ExcelExport(columnName = "序号",cloumnWidth = 15)
    private String id;

    @ExcelExport(columnName = "年份",cloumnWidth = 15)
    private String year;

    @ExcelExport(columnName = "省份",cloumnWidth = 15)
    private String sf;

    @ExcelExport(columnName = "科类",cloumnWidth = 15)
    private String kl;

    @ExcelExport(columnName = "考试类型",cloumnWidth = 15)
    private String kstype;

    @ExcelExport(columnName = "专业名称",cloumnWidth = 15)
    private String zyname;

    @ExcelExport(columnName = "招考方向",cloumnWidth = 15)
    private String zkfx;

    @ExcelExport(columnName = "学制",cloumnWidth = 15)
    private String xz;

    @ExcelExport(columnName = "批次名称",cloumnWidth = 15)
    private String pcname;

    @ExcelExport(columnName = "计划数",cloumnWidth = 15)
    private String jhnerber;

    @ExcelExport(columnName = "计划说明",cloumnWidth = 15)
    private String jhsm;

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getSf() {
        return sf;
    }

    public void setSf(String sf) {
        this.sf = sf;
    }

    public String getKl() {
        return kl;
    }

    public void setKl(String kl) {
        this.kl = kl;
    }

    public String getKstype() {
        return kstype;
    }

    public void setKstype(String kstype) {
        this.kstype = kstype;
    }

    public String getZyname() {
        return zyname;
    }

    public void setZyname(String zyname) {
        this.zyname = zyname;
    }

    public String getZkfx() {
        return zkfx;
    }

    public void setZkfx(String zkfx) {
        this.zkfx = zkfx;
    }

    public String getXz() {
        return xz;
    }

    public void setXz(String xz) {
        this.xz = xz;
    }

    public String getPcname() {
        return pcname;
    }

    public void setPcname(String pcname) {
        this.pcname = pcname;
    }

    public String getJhnerber() {
        return jhnerber;
    }

    public void setJhnerber(String jhnerber) {
        this.jhnerber = jhnerber;
    }

    public String getJhsm() {
        return jhsm;
    }

    public void setJhsm(String jhsm) {
        this.jhsm = jhsm;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "zebxt{" +
                "year='" + year + '\'' +
                ", sf='" + sf + '\'' +
                ", kl='" + kl + '\'' +
                ", kstype='" + kstype + '\'' +
                ", zyname='" + zyname + '\'' +
                ", zkfx='" + zkfx + '\'' +
                ", xz='" + xz + '\'' +
                ", pcname='" + pcname + '\'' +
                ", jhnerber='" + jhnerber + '\'' +
                ", jhsm='" + jhsm + '\'' +
                '}';
    }
}
