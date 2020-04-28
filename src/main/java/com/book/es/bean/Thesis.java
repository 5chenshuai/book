package com.book.es.bean;

import org.elasticsearch.client.license.LicensesStatus;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Document(indexName = "thesismanger",type = "thesis")
public class Thesis {
    @Id
    private String id;

    @NotEmpty(message = "论文标题不能为空")
    private String thesisTitle;

    @NotEmpty(message = "作者不能为空")
    private List<String> author;

    @NotEmpty(message = "关键字不能为空")
    private List<String> thesisKeyword;

    @NotEmpty(message = "论文pdf文件不能为空")
    private String pdf;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getThesisTitle() {
        return thesisTitle;
    }

    public void setThesisTitle(String thesisTitle) {
        this.thesisTitle = thesisTitle;
    }

    public List<String> getAuthor() {
        return author;
    }

    public void setAuthor(List<String> author) {
        this.author = author;
    }

    public List<String> getThesisKeyword() {
        return thesisKeyword;
    }

    public void setThesisKeyword(List<String> thesisKeyword) {
        this.thesisKeyword = thesisKeyword;
    }

    public String getPdf() {
        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }

    @Override
    public String toString() {
        return "Thesis{" +
                "id=" + id +
                ", thesisTitle='" + thesisTitle + '\'' +
                ", author=" + author +
                ", thesisKeyword=" + thesisKeyword +
                ", pdf='" + pdf + '\'' +
                '}';
    }
}
