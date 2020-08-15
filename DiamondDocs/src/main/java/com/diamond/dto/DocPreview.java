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

    public DocPreview(Doc doc){
        this.docID = doc.getDocID();
        this.docTitle = FormatHandler.getPreviewTitle(doc.getDocTitle());
        this.creatorID = doc.getCreatorID();
    }

    public static List<DocPreview> getPreviewList(List<Doc> docList){
        List<DocPreview> list = new ArrayList<>();
        for (Doc doc : docList)
            list.add(new DocPreview(doc));
        return list;
    }
}
