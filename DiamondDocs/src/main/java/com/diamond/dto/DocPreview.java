package com.diamond.dto;

import com.diamond.pojo.Doc;
import com.diamond.utils.FormatHandler;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/*
 * 文档类的简略信息
 * 用于在获取各种文档列表时预览文档信息
 */

@Data
@NoArgsConstructor
public class DocPreview {
    String docID;
    String docTitle;
    String creatorID;
    String lastEditTime;

    public DocPreview(Doc doc){
        this.docID = doc.getDocID();
        this.docTitle = doc.getDocTitle();
        this.creatorID = doc.getCreatorID();
        this.lastEditTime = doc.getLastEditTime();
    }

    /*
     * 笨方法转化
     * 转化要求：10字节内容+两字节省略号
     */
    public void setTitleToPreview(int bytes) {
        String string = this.docTitle;
        int length = 0;
        StringBuilder title = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            if (c >= 19968 && c <= 40869 && length <= bytes-2) {
                length = length + 2;
            } else if (!(c >= 19968 && c <= 40869) && length <= bytes-1) {
                length++;
            } else
                break;
            if (length < bytes) {
                title.append(c);
            } else if (length == bytes) {
                title.append(c);
                break;
            }
        }
        if (!string.equals(title.toString()))
            title.append("…");

        this.docTitle = title.toString();
    }

    public static List<DocPreview> getPreviewList(List<Doc> docList, int type){
        List<DocPreview> list = new ArrayList<>();
        for (Doc doc : docList) {
            DocPreview docPreview = new DocPreview(doc);
            docPreview.setTitleToPreview(type);
            list.add(docPreview);
        }
        return list;
    }
}
